package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//public class UserDaoJDBCImpl implements UserDao {
////    private final Connection connection;
//
//    public UserDaoJDBCImpl() {
////        connection = Util.getJdbcConnection();
//
//    }
//
//    public void createUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("""
//                    create table if not exists "users"(
//                    id        serial ,
//                    name      varchar(100) not null,
//                    lastName  varchar(100) not null,
//                    age smallint not null
//                    ); """);
//
//        } catch (SQLException e) {
//            System.out.println("Не удалось создать таблицу..SQLex");
//        }
//
//    }
//
//    public void dropUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("DROP TABLE users");
//
//
//        } catch (SQLException e) {
//            System.out.println("Сбой при удалении таблицы");
//        }
//
//
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        PreparedStatement statement ;
//        try {
//            statement = connection.prepareStatement(
//                    "insert into users (name, lastname, age)" + "VALUES (?,?,?)");
//            connection.setAutoCommit(false);
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setByte(3, age);
//            statement.addBatch();
//            statement.executeUpdate();
//            connection.commit();
//
//        } catch (SQLException e) {
//            System.out.println("Не удалось создать пользователя");
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.out.println("Не удалось откатить изменения при создании пользователя");
//            }
//        }
//
//
//    }
//
//    public void removeUserById(long id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "DELETE FROM users where id = ?");
//            connection.setAutoCommit(false);
//            statement.setLong(1, id);
//            statement.executeUpdate();
//            connection.commit();
//            connection.setAutoCommit(true);
//        } catch (SQLException e) {
//            System.out.println("не удалось удалить пользователя");
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.out.println("Не удалось отменить изменения при удалении пользователя");
//            }
//        }
//
//
//    }
//
//    public List<User> getAllUsers() {
//        List<User> listUsers = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "SELECT* FROM users;");
//            while (resultSet.next()) {
//                listUsers.add(new User(
//                        resultSet.getLong(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getByte(4)
//                ));
//            }
//        } catch (SQLException e) {
//            System.out.println("Ошибка при считывании всех пользователей");
//
//        }
//        return listUsers;
//    }
//
//    public void cleanUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("TRUNCATE TABLE users");
//        } catch (SQLException e) {
//            System.out.println("Не удалось отчитить таблицу пользователей");
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                System.out.println("не удалось отменить изменения при удалении содержимого Таблицы");
//            }
//        }
//
//    }
//}
