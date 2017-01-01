/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver;

import com.leapfrog.clientserver.command.BlockCommand;
import com.leapfrog.clientserver.handler.ClientHandler;
import com.leapfrog.clientserver.handler.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author apple
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 9000;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is running at " + port);

            ClientHandler handler = new ClientHandler();
            while (true) {
                Socket socket = server.accept();
                ClientListener listner = new ClientListener(socket, handler);
                listner.start();
                System.out.println("CONNECTION REQUEST FROM " + socket.getInetAddress().getHostAddress());

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
