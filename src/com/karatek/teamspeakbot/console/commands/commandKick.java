package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.utils.prefixhelper;

import java.util.concurrent.TimeUnit;

public class commandKick {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected, String[] strings) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            String message_kick = "";
            try {

                int kick_counted = 1;
                while (kick_counted < strings.length) {
                    message_kick = message_kick + " " + strings[kick_counted];
                    kick_counted++;
                }
                if(message_kick.startsWith(" ")) {
                    message_kick = message_kick.substring(1);
                }
                api.kickClientFromServer(message_kick, selected);
            } catch (TS3CommandFailedException e) {
                System.out.println("Internal Error. (Error Code 401)");
            }
            if (message_kick != null) {
                System.out.println("\b\b" + prefixhelper.getPrefix() + "Client " + selected.getNickname() + " was kicked");
            } else {
                System.out.println("\b\b" + prefixhelper.getPrefix() + "Client " + selected.getNickname() + " was kicked for " + message_kick);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(prefixhelper.getPrefix() + "Interrupted. (Error 302)");
            }

        }
    }
}
