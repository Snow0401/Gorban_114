package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("create table if not exists Users (" +
                "id bigint auto_increment," +
                "name varchar(30) not null," +
                "lastName varchar(30) not null," +
                "age tinyint null," +
                "constraint Users_pk" +
                " primary key (id))").executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("drop table if exists Users").executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new User (name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    @Override
    public void removeUserById(Long id) {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        if (user != null) {
            session.delete(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userSet = new ArrayList<>();
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        userSet = session.createQuery("from User").list();
        session.getTransaction().commit();
        return userSet;

    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();

    }
}
