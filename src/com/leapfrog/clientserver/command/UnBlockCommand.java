/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.handler.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shreeson Shrestha
 */
public class UnBlockCommand extends ChatCommand {
List<Client> blockedlist=new ArrayList<>();

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
        Client c=handler.getByUserName(tokens[1]);
        blockedlist=client.getblockedlist();
        blockedlist.remove(c);
    }
    
}
