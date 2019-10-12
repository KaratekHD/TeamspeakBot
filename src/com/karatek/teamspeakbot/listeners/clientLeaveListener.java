package com.karatek.teamspeakbot.listeners;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.ClientLeaveEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.main.Main;
import com.karatek.teamspeakbot.utils.Logger;

public class clientLeaveListener {

    public static void onLeave(ClientLeaveEvent e, TS3Query query, TS3Api api) {
        Logger.log("Client " + e.getClientId() + " logged out!");

    }
}
