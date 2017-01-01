/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.dao;

import com.leapfrog.clientserver.entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface UserDAO {
    int insert(User c) throws ClassNotFoundException,SQLException;
    int update(User c) throws ClassNotFoundException,SQLException;
    int delete(User id) throws ClassNotFoundException,SQLException;
    List<User> getAll() throws ClassNotFoundException,SQLException;
    User getById(int id)throws ClassNotFoundException,SQLException;
  //  boolean insert(User user);
    User Login(String userName , String password);
    
    
    
}
