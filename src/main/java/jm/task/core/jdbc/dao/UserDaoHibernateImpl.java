package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@SuppressWarnings("rawtypes")
public class UserDaoHibernateImpl implements UserDao {
//    private Session session = Util.getSession();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users_table(\n" +
                    "                    id        SERIAL  ,\n" +
                    "                    name      VARCHAR(100) NOT NULL,\n" +
                    "                    lastName  VARCHAR(100) NOT NULL,\n" +
                    "                    age       SMALLINT NOT NULL \n" +
                    "                    )").executeUpdate();
            session.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("не удалось создать таблицу пользователей");
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.createSQLQuery("DROP TABLE IF EXISTS users_table").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Не удалось удалить таблицу пользователей");

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        User user = new User(name, lastName, age);
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Не удалось добавить пользователя в таблицу");
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.createQuery("DELETE FROM User u WHERE u.id = :id ")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
//            Query queue = session.createQuery("DELETE FROM User u WHERE u.id = :userId ");
//            queue.setParameter("userId", id);
//            queue.executeUpdate();
//            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя");
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("SELECT u FROM User u");
            List<User> users = query.getResultList();
            return users;
        } catch (Exception e) {
            System.out.println("Не удалось составить список всех пользователей");
        }

        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User u");
            query.executeUpdate();
            session.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("Не удалось удалить всех пользователей из таблицы");
        }

    }
}
