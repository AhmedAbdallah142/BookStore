package database.bookstore.viewController;

import database.bookstore.entites.User;

public class ControllerRepo {
    private static User CurrentUser;
//    private ControllerRepo instance;
    public static User getUser() {
        return CurrentUser;
    }

    public static void setUser(User user) {
        CurrentUser = user;
    }
}
