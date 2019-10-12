package com.karatek.teamspeakbot.listeners;

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
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.karatek.teamspeakbot.utils.prefixhelper;

import java.util.List;

public class clientMoveListener {

    public static void onClientMove(ClientMovedEvent e, TS3Api api, TS3Query query) {
        System.out.print((char)13);
        System.out.println(prefixhelper.getPrefix() + "Client " + api.getClientInfo(e.getClientId()).getNickname() + " was moved to " + api.getChannelInfo(e.getTargetChannelId()).getName());
        if(api.getChannelInfo(e.getTargetChannelId()).getName().startsWith("╔ Support - Warteschlange")) {
            if(api.getClientInfo(e.getClientId()).isInServerGroup(20345)) {

            } else {
                System.out.println(prefixhelper.getPrefix() + "Client " + api.getClientInfo(e.getClientId()).getNickname() + " is waiting for support!");
                List<Client> clients = api.getClients();
                for (Client client : clients) {
                    if (client.isInServerGroup(20345)) {
                        String pushmsg = "Client '" + api.getClientInfo(e.getClientId()).getNickname() + "' is waiting for support!";
                        api.sendPrivateMessage(client.getId(), pushmsg);
                    }
                }
            }
        }
        System.out.print("> ");
    }
}
