-- MySQL Script generated by MySQL Workbench
-- Mon Jan 10 13:28:14 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bookStore
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bookStore` ;

-- -----------------------------------------------------
-- Schema bookStore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookStore` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `bookStore` ;

-- -----------------------------------------------------
-- Table `bookStore`.`Publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Publisher` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Publisher` (
  `publisher_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`publisher_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Category` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Category` (
  `category_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`category_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Book` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Book` (
  `ISBN` INT UNSIGNED NOT NULL,
  `title` VARCHAR(150) NOT NULL,
  `publisher` VARCHAR(150) NOT NULL,
  `publication_year` VARCHAR(4) NULL,
  `price` DOUBLE UNSIGNED NULL DEFAULT 20,
  `category` VARCHAR(45) NOT NULL,
  `copies` INT UNSIGNED NULL,
  `threshold` INT UNSIGNED NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `copies_UNIQUE` (`copies` ASC) VISIBLE,
  INDEX `book_publisher_fk_idx` (`publisher` ASC) VISIBLE,
  INDEX `book_category_fk_idx` (`category` ASC) VISIBLE,
  CONSTRAINT `book_publisher_fk`
    FOREIGN KEY (`publisher`)
    REFERENCES `bookStore`.`Publisher` (`publisher_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `book_category_fk`
    FOREIGN KEY (`category`)
    REFERENCES `bookStore`.`Category` (`category_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`PublisherPhone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`PublisherPhone` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`PublisherPhone` (
  `publisher_name` VARCHAR(45) NOT NULL,
  `publisher_phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`publisher_name`, `publisher_phone`),
  CONSTRAINT `publisher_phone`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `bookStore`.`Publisher` (`publisher_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`publisherAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`publisherAddress` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`publisherAddress` (
  `publisher_name` VARCHAR(45) NOT NULL,
  `publisher_address` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`publisher_name`, `publisher_address`),
  CONSTRAINT `publisher_address_fk`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `bookStore`.`Publisher` (`publisher_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`AuthorName`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`AuthorName` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`AuthorName` (
  `author_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`author_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`Author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Author` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Author` (
  `author_name` VARCHAR(45) NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`author_name`, `ISBN`),
  INDEX `author_book_isbn_fk_idx` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `author_book_isbn_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookStore`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `author_name_fk`
    FOREIGN KEY (`author_name`)
    REFERENCES `bookStore`.`AuthorName` (`author_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Order` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `ISBN` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `ISBN`),
  INDEX `order_book_isbn_fk_idx` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `order_book_isbn_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookStore`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`User` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`User` (
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `is_manager` TINYINT(1) NULL DEFAULT 0,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(100) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStore`.`Sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookStore`.`Sale` ;

CREATE TABLE IF NOT EXISTS `bookStore`.`Sale` (
  `sale_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NOT NULL,
  `ISBN` INT UNSIGNED NULL,
  `copies` INT UNSIGNED NULL,
  `date` DATE NULL,
  PRIMARY KEY (`sale_id`),
  INDEX `sale_book_isbn_fk_idx` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `sale_book_isbn_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `bookStore`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `sale_user_email_fk`
    FOREIGN KEY (`user_email`)
    REFERENCES `bookStore`.`User` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- place order after update
DELIMITER $$
CREATE TRIGGER Place_orders_on_books
 AFTER UPDATE
ON bookstore.book FOR EACH ROW
BEGIN
IF NEW.copies < NEW.threshold AND OLD.copies >= NEW.threshold then
	CAll place_order(NEW.ISBN);
END IF;
END$$
DELIMITER ;


-- place order
USE `bookstore`;
DROP procedure IF EXISTS `place_order`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE `place_order` (new_ISBN INT)
BEGIN
Insert into bookStore.Order (ISBN,quantity) values (new_ISBN,(Select
threshold from Book where Book.ISBN = new_ISBN));
END$$

DELIMITER ; 


-- confirm order after update
DELIMITER $$
CREATE TRIGGER Confirm_orders
BEFORE DELETE
ON bookstore.order FOR EACH ROW
BEGIN
UPDATE Book As B set B.copies = B.copies + OLD.quantity where
OLD.ISBN = B.ISBN;
END$$
DELIMITER ;


-- modify books
DELIMITER $$
CREATE TRIGGER Modify_existing_books
BEFORE UPDATE
ON bookstore.book FOR EACH ROW
BEGIN
IF(NEW.copies < 0) THEN
	SET @message_text = concat("can't update with negative");
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
END IF;
END$$
DELIMITER ;



-- add books
DELIMITER $$
CREATE TRIGGER Before_add_books
BEFORE INSERT
ON bookstore.book FOR EACH ROW
BEGIN
IF(NEW.copies < NEW.threshold) THEN
	SET @message_text = ("enter a value more than the threshold");
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
END IF;
END$$
DELIMITER ;
