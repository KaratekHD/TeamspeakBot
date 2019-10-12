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
