package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;
import com.karatek.teamspeakbot.utils.Logger;

import java.util.List;

public class commandInfo {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            boolean worked = true;
            try {
                selected = api.getClientInfo(selected.getId());
            } catch (TS3CommandFailedException e) {
                Logger.log("A TS3 Command failed (Error 401)");
                worked = false;
            }

            if(worked) {
                int groups_list_counted = 0;
                List<ServerGroup> serverGroupList = api.getServerGroupsByClient(selected);
                ServerGroup[] item = serverGroupList.toArray(new ServerGroup[serverGroupList.size()]);
                String output_info = "General information\n" +
                        "Nickname: " + selected.getNickname() + "\n" +
                        "Description: " + selected.getDescription() + "\n" +
                        "Location: " + selected.getCountry() + "\n" +
                        "Platform: " + selected.getPlatform() + "\n\n" +
                        "Channel information\n" +
                        "Channel name: " + api.getChannelInfo(selected.getChannelId()).getName() + "\n" +
                        "Channel description: " + api.getChannelInfo(selected.getChannelId()).getDescription() + "\n\n" +
                        "Internal information\n" +
                        "ID: " + selected.getId() + "\n" +
                        "UUID: " + selected.getUniqueIdentifier() + "\n" +
                        "Database ID: " + selected.getDatabaseId() + "\n" +
                        "IP: " + selected.getIp()+ "\n\nServerGroups:\n" +
                        "ID		Name\n";
                for(ServerGroup s : item){
                    //System.out.println();
                    output_info = output_info + s.getId() + "		" + s.getName() + "\n";
                }
                System.out.println(output_info);




            }
        }
    }
}
