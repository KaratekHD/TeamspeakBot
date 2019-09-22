package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.List;

public class commandSgroups {

    public static void execute(TS3Api api, TS3Query query) {
        List<ServerGroup> serverGroupList = api.getServerGroups();
        ServerGroup[] item = serverGroupList.toArray(new ServerGroup[serverGroupList.size()]);
        System.out.println("ServerGroups:\n");
        System.out.println("ID		Name");
        for(ServerGroup s : item){
            System.out.println(s.getId() + "		" + s.getName());
        }
    }
}
