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
public class KickCommand extends ChatCommand {

    List<Client> userlist = new ArrayList<>();

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
        userlist = handler.getClients();
        Client c = handler.getByUserName(tokens[1]);
        for (Client cl : userlist) {
            if(!cl.equals(c)){
            PrintStream ps = new PrintStream(cl.getSocket().getOutputStream());
            ps.println(client.getUsername() + " kicked " + c.getUsername());
            }
        }
        PrintStream psc = new PrintStream(c.getSocket().getOutputStream());
        psc.println(client.getUsername() + " kicked you from this group");
        userlist.remove(c);
        c.getSocket().close();
    }
}
