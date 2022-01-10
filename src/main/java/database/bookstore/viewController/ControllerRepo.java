package database.bookstore.viewController;

import database.bookstore.entites.User;

public class ControllerRepo {
    private static User CurrentUser;
    public static User getUser() {
        return CurrentUser;
    }
    public static void setUser(User user) {
        CurrentUser = user;
    }
//    private User CurrentUser;
//    private static ControllerRepo instance = null;
//    private ControllerRepo(){
//
//    }
//    public static ControllerRepo getInstance() {
//        if (instance == null)
//            instance = new ControllerRepo();
//        return instance;
//    }
//
//    public User getUser() {
//        return CurrentUser;
//    }
//
//    public void setUser(User user) {
//        CurrentUser = user;
//    }
}
