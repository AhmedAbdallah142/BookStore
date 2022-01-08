module database.bookstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
	requires java.sql;

    opens database.bookstore to javafx.fxml;
    exports database.bookstore;
    exports database.bookstore.viewController;
    opens database.bookstore.viewController to javafx.fxml;
}