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

public class PublicMessageCommand extends ChatCommand {
    List<Client> blockedlist = new ArrayList<>();

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {
        blockedlist=client.getblockedlist();
        for (Client c : handler.getClients()) {
            if (!c.equals(client)) {
                // System.out.println(blockedlist);
                if (!blockedlist.contains(c)) {
                    PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                    ps.println(client.getUsername() + " Says >" + msg);
                } else {
                    PrintStream ps = new PrintStream(client.getSocket().getOutputStream());
                    ps.println(c.getUsername() + " is blocked");
                }
            }
        }

    }
}
