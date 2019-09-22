package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.List;

public class commandInfo {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            selected = api.getClientInfo(selected.getId());
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
                    "IP: " + selected.getIp()+ "\n";

            System.out.println(output_info);
            int groups_list_counted = 0;
            List<ServerGroup> serverGroupList = api.getServerGroupsByClient(selected);
            ServerGroup[] item = serverGroupList.toArray(new ServerGroup[serverGroupList.size()]);
            System.out.println("ServerGroups:\n");
            System.out.println("ID		Name");
            for(ServerGroup s : item){
                System.out.println(s.getId() + "		" + s.getName());
            }
        }
    }
}
