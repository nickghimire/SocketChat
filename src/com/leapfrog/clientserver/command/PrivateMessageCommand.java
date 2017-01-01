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
 * @author apple
 */
public class PrivateMessageCommand extends ChatCommand {

    @Override
    public void execute(Client client, String[] tokens,String msg) throws IOException {

        
            if (tokens.length >= 3) {
                
                Client c = handler.getByUserName(tokens[1]);
                if (c != null) {

                    msg = " PM from " + client.getUsername() + tokens[2];

                }
                PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
                ps.println(client.getUsername() + " Says > " + msg);
            }
        }
    }

