/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.scrapper;

import com.leapfrog.clientserver.command.ChatCommand;
import com.leapfrog.clientserver.handler.Client;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author apple
 */
public class Downloader extends ChatCommand {

    @Override
    public void execute(Client client, String[] tokens, String msg) throws IOException {

        while (true) {
            String link = tokens[1];
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            String[] token = link.split("/");
            FileOutputStream os = new FileOutputStream(token[token.length - 1]);
            byte[] data = new byte[1024 * 5];
            int i = 0;
            while ((i = is.read(data)) != -1) {
                os.write(data, 0, i);
            }
            is.close();
            os.close();
            System.out.println("Downloaded successfully");
        }

    }

}
