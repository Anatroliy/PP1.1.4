package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory ;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "root";
    private static final String password = "root";
    public static Session getSession(){

        if (sessionFactory == null){
            Configuration configuration = new Configuration();

            Properties settings = new Properties();

            settings.put(Environment.DRIVER, driver);
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, user);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
     return sessionFactory.openSession();
    }

//    private static Connection dbConnection;
//    private static final String DRIVER = "org.postgresql.Driver";
//    private static final String URL = "jdbc:postgresql://localhost:5432/";
//    private static final String DATABASE = "dataBaseTest";
//
//    private static String USERNAME = "root";/**/
//
//    private static String PASS = "root"; /**/
//
//    public static Connection getJdbcConnection() {
//
//        while (dbConnection == null) {
//
//            try {
//                Class.forName(DRIVER);
//                System.out.println("Драйвер успешно загружен");
//            } catch (ClassNotFoundException e) {
//                System.out.println("Не удалось загрузить драйвер");
//                break;
//            }
//            try {
//                dbConnection = DriverManager.getConnection(
//                        URL + DATABASE, USERNAME, PASS);
//                System.out.println("Успешное подлючение к БД");
//            } catch (SQLException e) {
//                System.out.println("Не удалось подключиться к БД");
//                break;
//            }
//        }
//        return dbConnection;
//    }
}
