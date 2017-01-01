/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.handler.Client;
import com.leapfrog.clientserver.handler.ClientHandler;
import java.io.IOException;

/**
 *
 * @author apple
 */
public abstract class ChatCommand {
    protected ClientHandler handler;
    public abstract void execute (Client client,String[] tokens,String msg) throws IOException;

    public ClientHandler getHandler() {
        return handler;
    }

    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }
}
