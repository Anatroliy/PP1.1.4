package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);
    private static Connection dbConnection;
    private static String DRIVER = "org.postgresql.Driver";
    private static String URL = "jdbc:postgresql://localhost:5432/";
    private static String DATABASE = "dataBaseTest";

    private static String USERNAME = scanner.nextLine();/**/

    private static String PASS = scanner.nextLine(); /*;*/

    public static Connection getJdbcConnection() {

        while (dbConnection == null) {

            try {
                Class.forName(DRIVER);
                System.out.println("Драйвер успешно загружен");
            } catch (ClassNotFoundException e) {
                System.out.println("Не удалось загрузить драйвер");
                break;
            }
            try {
                dbConnection = DriverManager.getConnection(
                        URL + DATABASE, USERNAME, PASS);
                System.out.println("Успешное подлючение к БД");
            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к БД");
                break;
            }
        }
        return dbConnection;
    }

}
