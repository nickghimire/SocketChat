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
 * @author Shreeson Shrestha
 */
public class BlockCommand extends ChatCommand {

    List<Client> blocklist = new ArrayList<>();

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
        Client c = handler.getByUserName(tokens[1]);
        if(c!=null){
            client.block(c);
            for(Client cl:handler.getClients()){
                if (!c.equals(cl)) {
                    PrintStream ps = new PrintStream(cl.getSocket().getOutputStream());
                    ps.println(client.getUsername() + " blocked " + c.getUsername());
                }
            }
            PrintStream psc = new PrintStream(c.getSocket().getOutputStream());
            psc.println(client.getUsername() + " blocked you");
        }
    }

    public boolean checkblock(Client client) {
        for (Client c : blocklist) {
            if (c.equals(client)) {
                return true;
            }
        }
        return false;
    }
    public List<Client> getblocklist(){
        return blocklist;
    }

    @Override
    public String toString() {
        return "BlockCommand{" + "blocklist=" + blocklist + '}';
    }
    
}
