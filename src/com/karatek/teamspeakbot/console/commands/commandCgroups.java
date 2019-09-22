package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelGroup;

import java.util.List;

public class commandCgroups {

    public static void execute(TS3Api api, TS3Query query) {
        List<ChannelGroup> channelGroupList = api.getChannelGroups();
        ChannelGroup[] item_channel_group_list = channelGroupList.toArray(new ChannelGroup[channelGroupList.size()]);
        System.out.println("ServerGroups:\n");
        System.out.println("ID		Name");
        for(ChannelGroup s : item_channel_group_list){
            System.out.println(s.getId() + "		" + s.getName());
        }
    }
}
