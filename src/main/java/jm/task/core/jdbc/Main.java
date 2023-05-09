package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите логин и пароль от БД");
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Джон", "Сноу", (byte) 33);
        userService.saveUser("Эйгон", "Таргариен", (byte) 33);
        userService.saveUser("Арья", "Старк", (byte) 25);
        userService.saveUser("Серсея", "Ланистер", (byte) 42);
        userService.saveUser("Джейми", "Ланистер", (byte) 42);
        userService.saveUser("Джорах", "Мармонт", (byte) 53);
        userService.saveUser("Петир", "Бейлиш", (byte) 51);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
