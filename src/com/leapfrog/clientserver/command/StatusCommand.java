/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.handler.Client;
import com.leapfrog.clientserver.handler.Client.clientstatus;
import java.io.IOException;

/**
 *
 * @author Shreeson Shrestha
 */
public class StatusCommand extends ChatCommand{
public Enum status;
    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
        if(tokens[1].equalsIgnoreCase("active")){
            status=clientstatus.ACTIVE;
        }else if(tokens[1].equalsIgnoreCase("active")){
            status=clientstatus.INACTIVE;
        }
        client.setStatus(status);
    }
    
}
