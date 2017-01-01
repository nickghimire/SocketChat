/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.handler.Client;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Shreeson Shrestha
 */
public class Autoreply extends ChatCommand{

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
       Client c=handler.getByUserName(tokens[1]);
       if(client.checkstatus(c)){
           PrintStream ps= new PrintStream(client.getSocket().getOutputStream());
           ps.println("");
       }
    }
    
}
