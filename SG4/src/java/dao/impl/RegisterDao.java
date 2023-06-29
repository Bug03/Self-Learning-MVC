package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class RegisterDao {

    Connection con;

    public RegisterDao(Connection con) {
        this.con = con;
    }

    public boolean RegisterUser(User user) {
        boolean set = false;
        try {
            String sql = "INSERT INTO users (username,password,email,phone,name,created,address,avatar) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getCreated());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getAvatar());
            preparedStatement.executeUpdate();
            set = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}
