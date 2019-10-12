package com.karatek.teamspeakbot.console.commands;

/*
 * Karatek TeamspeakBot
 * Copyright (C) 2019  Karatek_HD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
