package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
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
