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
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

public class commandList {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected, String[] strings) {
        int list_counted = 0;
        Object[] array = api.getClients().toArray();
        int clients = array.length;
        Client client;
        System.out.println("The following Users are online: \n");
        System.out.println("ID	Nickname");
        while (list_counted < clients) {
            Object object = array[list_counted];
            String str = object.toString();
            str = str.replace("{", "");
            str = str.replace("}", "");
            String[] clientInfo = str.split(",");
            String ls_id = clientInfo[15].replace(" clid=", "");

            if(!ls_id.equals(String.valueOf(api.whoAmI().getId()))) {
                System.out.println(ls_id + "	" + clientInfo[2].replace(" client_nickname=", ""));
                Client ls_user = api.getClientByNameExact(clientInfo[2].replace(" client_nickname=", ""), false);
            }
            list_counted ++;
        }
        list_counted = list_counted -1;
        System.out.println("\nTotal: " + list_counted);
    }
}
