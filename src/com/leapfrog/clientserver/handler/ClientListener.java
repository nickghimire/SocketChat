/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.handler;

import com.leapfrog.clientserver.command.ChatCommand;
import com.leapfrog.clientserver.command.ChatCommandFactory;
import com.leapfrog.clientserver.command.ListCommand;
import com.leapfrog.clientserver.command.PrivateMessageCommand;
import com.leapfrog.clientserver.command.PublicMessageCommand;
import com.leapfrog.clientserver.dao.UserDAO;
import com.leapfrog.clientserver.dao.impl.UserDAOImpl;
import com.leapfrog.clientserver.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                String[] tokens = line.split(";;");
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
        ps.println("Login or SignUp");
        if (reader.readLine().equalsIgnoreCase("login")) {
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
                handler.addClient(client);

                ps.println(" Thank you " + username);
                return true;
            }
        } else if (reader.readLine().equalsIgnoreCase("signup")) {
            //List<User> user = new ArrayList<>();
            User userdetails= new User();
            ps.println("Enter new name:");
            String newName = reader.readLine();
            userdetails.setUserName(newName);
            ps.println("Enter new password:");
            String newPasswrod = reader.readLine();
            userdetails.setPassword(newPasswrod);
            ps.println("Enter new Email:");
            String newEmail = reader.readLine();
            userdetails.setEmail(newEmail);
            userdetails.setGroup_id(1);
            userdetails.setStatus(true);
            //user.add(userdetails);
           try {
               userDAO.insert(userdetails);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
           }
            ps.println("New account created successfully!");
            return true;
        } else {
            return false;
        
        }

    }
}

