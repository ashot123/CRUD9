package net.roseindia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.roseindia.bean.UserBean;
import net.roseindia.dbconnection.ConnectionProvider;

public class UserDao {

    private Connection conn;
    private int noOfRecords;

    public UserDao() {
        conn = ConnectionProvider.getConnection();
    }

    public int addUser(UserBean userBean) {
        ResultSet rs = null;
        int generatedId = 0;
        try {
            String sql = "INSERT INTO users(firstname,lastname) VALUES ( ?, ? )";
            PreparedStatement ps = conn.prepareStatement(sql);

            //ps.setInt(1, userBean.getId());
            ps.setString(1, userBean.getFirstName());
            ps.setString(2, userBean.getLastName());
            ps.executeUpdate();


/*             conn.prepareStatement(sql, int[]);
           rs = ps.getGeneratedKeys();
              java.sql.SQLException: Generated keys not requested.
              You need to specify Statement.RETURN_GENERATED_KEYS to Statement.executeUpdate(), Statement.executeLargeUpdate()
              or Connection.prepareStatement().

            if (rs.next()) {
                generatedId = rs.getInt(1);
                //System.out.println("Auto Generated Primary Key " + rs.getInt(1));
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public void removeUser(int userId) {
        try {
            String sql = "DELETE FROM users WHERE userid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(UserBean userBean) {
        try {
            String sql = "UPDATE users SET firstname = ?, lastname = ? WHERE userid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(3, userBean.getId());
            ps.setString(1, userBean.getFirstName());
            ps.setString(2, userBean.getLastName());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getAllUsers(int offset, int noOfRecords) {
        //int offset = 0;
        //int noOfRecord = 5;
        List users = new ArrayList();
        try {
            String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM users limit " + offset + ", " + noOfRecords;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setId(rs.getInt("userid"));
                userBean.setFirstName(rs.getString("firstname"));
                userBean.setLastName(rs.getString("lastname"));
                users.add(userBean);
            }
            rs.close();
            rs = ps.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public UserBean getUserById(int userId) {
        UserBean userBean = new UserBean();
        try {
            String sql = "SELECT * FROM users WHERE userid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userBean.setId(rs.getInt("userid"));
                userBean.setFirstName(rs.getString("firstname"));
                userBean.setLastName(rs.getString("lastname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }
}