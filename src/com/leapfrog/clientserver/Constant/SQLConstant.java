/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.Constant;

/**
 *
 * @author apple
 */
public class SQLConstant {

    public static final String INSERT_USERS = " INSERT INTO " + TableConstant.USER_TABLE + "(name,email,password,group_id,status ) VALUES (?,?,?,?,?)";
    public static final String UPDATE_USERS = " UPDATE " + TableConstant.USER_TABLE + "( name=?,email=?,password=?,group_id=?,status=? WHERE client_id=?)" ;
    public static final String DELETE_USERS = "DELETE FROM" + TableConstant.USER_TABLE + "WHERE client_id = ? " ;
    public static final String GETALL_USERS = " SELECT * FROM " + TableConstant.USER_TABLE;
    public static final String GETBYID_USERS = " SELECT * FROM " + TableConstant.USER_TABLE + " WHERE client_id = ? ";

}
