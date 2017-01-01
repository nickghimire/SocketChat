/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.dao.impl;

import com.leapfrog.clientserver.Constant.SQLConstant;
import com.leapfrog.clientserver.dao.UserDAO;
import com.leapfrog.clientserver.dbUtil.DbConnection;
import com.leapfrog.clientserver.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apple
 */
public class UserDAOImpl implements UserDAO {

    private DbConnection db = new DbConnection();
    private List<User> userList = new ArrayList<>();

    @Override
    public int insert(User c) throws ClassNotFoundException, SQLException {
        db.open();
        PreparedStatement stmt = db.initComponent(SQLConstant.INSERT_USERS);
        stmt.setString(1, c.getUserName());
        stmt.setString(3, c.getPassword());
        stmt.setString(2, c.getEmail());
        stmt.setInt(4, c.getGroup_id());

        stmt.setBoolean(5, c.isStatus());
        int result = stmt.executeUpdate();
        return result;
    }

    @Override
    public int update(User c) throws ClassNotFoundException, SQLException {
        db.open();
        PreparedStatement stmt = db.initComponent(SQLConstant.UPDATE_USERS);
        stmt.setString(1, c.getUserName());
        stmt.setString(2, c.getPassword());
        stmt.setString(3, c.getEmail());
        stmt.setBoolean(4, c.isStatus());
        stmt.setInt(5, c.getId());
        int result = stmt.executeUpdate();
        return result;
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException {
        db.open();
        PreparedStatement stmt = db.initComponent(SQLConstant.DELETE_USERS);
        stmt.setInt(1, id);
        int result = stmt.executeUpdate();
        return result;

    }
    
     @Override
    public User getById(int id) throws ClassNotFoundException, SQLException {
        User user = null;
        db.open();
        PreparedStatement stmt = db.initComponent(SQLConstant.GETBYID_USERS);
        stmt.setInt(1, id);
        ResultSet rs = db.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt("client_id"));
            user.setUserName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGroup_id(rs.getInt("group_id"));

            user.setStatus(rs.getBoolean("status"));

        }
        db.close();
        return user;
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
       List<User> userList = new ArrayList<>();
        db.open();
        PreparedStatement stmt = db.initComponent(SQLConstant.GETALL_USERS);
       
        ResultSet rs = db.executeQuery();
        while (rs.next()) {
           User user = new User();
            user.setId(rs.getInt("client_id"));
            user.setUserName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGroup_id(rs.getInt("group_id"));

            user.setStatus(rs.getBoolean("status"));
            userList.add(user);

        }
        db.close();
        return userList;
    }

   

    @Override
    public User Login(String userName, String password) {
      
        try {
            for (User u : getAll()) {
                if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                    return u;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}


/*

    @Override
    public List<User> getAll() {
userList.add(new User(1, "nikunj", "nikunj", "nik@gmail.com", true));
userList.add(new User(2, "hari", "hari", "hari@gmail.com", true));
userList.add(new User(1, "sita", "sita", "gita@gmail.com", true));

userList.add(new User(1, "gita", "gita", "sita@gmail.com", true));

return userList;
    }

    @Override
    public boolean insert(User user) {

return userList.add(user);
    }

    @Override
    public User Login(String userName, String password) {
    for(User u:getAll()){
        if(u.getUserName().equalsIgnoreCase(userName) && u.getPassword().equalsIgnoreCase(password)){
            return u;
        }
    }
            return null;

    }

 */
