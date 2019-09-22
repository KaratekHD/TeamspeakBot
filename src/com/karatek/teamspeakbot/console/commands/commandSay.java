package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class commandSay {

    public static void execute(TS3Api api, TS3Query query, String[] strings) {
        String tosay = "";
        if(strings.length < 2) {
                        System.out.println(com.karatek.teamspeakbot.utils.prefixhelper.getPrefix() + "Usage: say <text>");
                    }
                    int i = 1;
                    while (i < strings.length) {
                        tosay = tosay + " " + strings[i];
                        i++;
                    }
                    api.sendServerMessage(tosay);
                    System.out.println(com.karatek.teamspeakbot.utils.prefixhelper.getPrefix() + "Broadcasted '" + tosay + "'");
    }
}
