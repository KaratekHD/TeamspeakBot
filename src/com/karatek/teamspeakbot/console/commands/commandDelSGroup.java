package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.utils.Logger;

public class commandDelSGroup {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected, String[] strings) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            if(strings.length != 2) {
                System.out.println("Usage: addsgroup <group id>");
            } else {

                try {
                    api.removeClientFromServerGroup(Integer.parseInt(strings[1]), selected.getDatabaseId());
                    System.out.println("Done.");
                } catch (TS3CommandFailedException e) {
                    Logger.log("TS3CommandFailed, Maybe that ServerGroup doesn't exist? (Error 401)");
                }


            }
        }
    }
}
