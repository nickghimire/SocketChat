/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.clientserver.command;

import com.leapfrog.clientserver.scrapper.Downloader;

/**
 *
 * @author apple
 */
public class ChatCommandFactory {
    public static ChatCommand get(String param){
        if(param.equalsIgnoreCase("list")){
            return new ListCommand();
        }else if (param.equalsIgnoreCase("pm")){
            return new PrivateMessageCommand();
        }else if (param.equalsIgnoreCase("download")){
            return new Downloader();
        }else if (param.equalsIgnoreCase("kick")){
            return new KickCommand();
        }else if (param.equalsIgnoreCase("block")){
            return new BlockCommand();
        }else if (param.equalsIgnoreCase("unblock")){
            return new UnBlockCommand();
        }else if (param.equalsIgnoreCase("status")){
            return new StatusCommand();
        }else 
            return new PublicMessageCommand();
        }
    }
    

