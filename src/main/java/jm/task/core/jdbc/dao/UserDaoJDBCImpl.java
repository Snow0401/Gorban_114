package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    private static ResultSet resultSet;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement st = util.getConnection().createStatement()) {

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

        try (Statement st = util.getConnection().createStatement()) {
            st.executeUpdate("drop table if exists Users");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement ps = util.getConnection().prepareStatement("insert into Users (name, lastName, age)" +
                "value (?,?,?)")) {
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setByte(3,age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void removeUserById(Long id) {
        try (PreparedStatement ps = util.getConnection().prepareStatement("delete from Users \n" +
                "where id=" + id)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> userSet = new ArrayList<>();
        try (Statement st = util.getConnection().createStatement()) {
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
        try (Statement st = util.getConnection().createStatement()) {
            st.executeUpdate("delete from Users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
