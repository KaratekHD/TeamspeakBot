package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;
import com.karatek.teamspeakbot.utils.prefixhelper;

public class commandExit {

    public static void execute(TS3Api api, TS3Query query) {
        api.sendChannelMessage("I am offline!");
                    try{ api.setNickname("KTS_Internal"); }catch (Exception e){
                        System.out.println(prefixhelper.getPrefix() + "Internal Error. (Error 300)");
                    }
                    try {
                        query.exit();
                    } catch (TS3QueryShutDownException ex) {
                        System.err.println(prefixhelper.getPrefix() + "FATAL: TS3QuerySHutdownException (Error 402)");
                        System.exit(402);
                    }
                    System.out.print((char)13);
                    System.out.println(com.karatek.teamspeakbot.utils.prefixhelper.getPrefix() + "Shutting down.");
                    System.exit(0);
    }
}
