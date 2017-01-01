/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.handler;

import com.leapfrog.clientserver.command.BlockCommand;
import com.leapfrog.clientserver.command.ChatCommand;
import com.leapfrog.clientserver.command.ChatCommandFactory;
import com.leapfrog.clientserver.dao.UserDAO;
import com.leapfrog.clientserver.dao.impl.UserDAOImpl;
import com.leapfrog.clientserver.entity.User;
import com.leapfrog.clientserver.handler.Client.clientstatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author apple
 */
public class ClientListener extends Thread {

    private UserDAO userDAO = new UserDAOImpl();
    private Socket socket;
    private PrintStream ps;
    private BufferedReader reader;
    private Client client;
    private ClientHandler handler;

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        this.handler = handler;
        ps = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (!doLogin()) {

            }
            String line = " ";
            while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {

                String[] tokens = line.split("::");
                ChatCommand cmd = ChatCommandFactory.get(tokens[0]);

                if (cmd != null) {
                    cmd.setHandler(handler);
                    cmd.execute(client, tokens, line);

                }
            }
            handler.removeClient(client);
            
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    private boolean doLogin() throws IOException {
        ps.println("Welcome to server");
        ps.println("Enter your user name");

        String username = reader.readLine();

        ps.println("Enter your Password");
        String password = reader.readLine();

        User user = userDAO.Login(username, password);
        if (user == null) {
            ps.println("User Name Invalid");
            return false;
        } else if (!user.isStatus()) {
            ps.println("Account Not Activated");
            return false;
        } else {
            System.out.println(username);
            
            client = new Client(username, socket);
            client.setStatus(clientstatus.ACTIVE);
            handler.addClient(client);

            ps.println(" Welcome to group: " + username);
            return true;
        }

    }
}

