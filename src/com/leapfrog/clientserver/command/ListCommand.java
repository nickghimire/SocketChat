/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.handler.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apple
 */
public class ListCommand extends ChatCommand {
List<Client> userlist= new ArrayList<>();

    @Override
    public void execute(Client client,String[] tokens, String msg) throws IOException {
        
        
         PrintStream ps = new PrintStream(client.getSocket().getOutputStream());
        StringBuilder content = new StringBuilder();
        for (Client c : handler.getClients()) {
            userlist.add(c);
            if(client.checkstatus(c)){
              content.append(c.getUsername()).append("(").append(c.getStatus()).append(")").append("\r\n");
            }
            else{
                content.append(c.getUsername()).append("(").append(c.getStatus()).append(")").append("\r\n");
            }
        }
        ps.println(content.toString());   
        }
    }


