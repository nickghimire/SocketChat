/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.handler;

import static com.leapfrog.clientserver.handler.Client.clientstatus.ACTIVE;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apple
 */
public class Client {
    private String username;
    private Socket socket;
    public enum clientstatus{
        ACTIVE,INACTIVE
    }
    public Enum status;
List<Client> blockedlist=new ArrayList<>();
    public Client() {
    }

    public Client(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }


    public List<Client> getBlockedlist() {
        return blockedlist;
    }

    public void setBlockedlist(List<Client> blockedlist) {
        this.blockedlist = blockedlist;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void block(Client client){
        blockedlist.add(client);
    }
    
    public void unblock(Client client){
        blockedlist.remove(client);
    }
    public List<Client> getblockedlist(){
        return blockedlist;
    }
    public boolean checkstatus(Client client){
        if(client.getStatus().equals(clientstatus.ACTIVE)){
            return true;
        }
        return false;
    }
}
