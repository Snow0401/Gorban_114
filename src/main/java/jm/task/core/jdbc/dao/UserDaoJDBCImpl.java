package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private final Connection connection = getConnection();
    private static ResultSet resultSet;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement st = connection.createStatement()) {

            st.execute("create table if not exists Users (" +
                    "id bigint auto_increment," +
                    "name varchar(30) not null," +
                    "lastName varchar(30) not null," +
                    "age tinyint null," +
                    "constraint Users_pk" +
                    " primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("drop table if exists Users");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void saveUser(String name, String lastName, Byte age) {

        try (Statement st = connection.createStatement()) {

            st.executeUpdate("insert into Users (name, lastName, age) \n" +
                    " values ('" + name + "', '" + lastName + "'," + age + ");");
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void removeUserById(Long id) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("delete from Users \n" +
                    "where id=" + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> userSet = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery("select * from Users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getNString("name"));
                user.setLastName(resultSet.getNString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userSet.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userSet;
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("delete from Users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
