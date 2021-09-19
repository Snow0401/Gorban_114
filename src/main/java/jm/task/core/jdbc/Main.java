package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Artem", "Gorban", (byte) 31);
        userService.saveUser("Alexey", "Ryabov", (byte) 31);
        userService.saveUser("Igor", "Lunev", (byte) 31);
        userService.saveUser("Konstantin", "Korotkov", (byte) 31);
        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();




    }

}
