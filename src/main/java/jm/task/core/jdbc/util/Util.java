package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    ///////////////////////////////////////////HIBERNATE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static SessionFactory sessionFactory;

    public static Session getSession() {

        if (sessionFactory == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
            sessionFactory.getCurrentSession();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory.openSession();
    }

    ///////////////////////////////////////////HIBERNATE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /////////////////////////////////////////////JDBC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static Connection dbConnection;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DATABASE = "dataBaseTest";

    private static String USERNAME = "root";/**/

    private static String PASS = "root"; /**/

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
    /////////////////////////////////////////////JDBC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
}
