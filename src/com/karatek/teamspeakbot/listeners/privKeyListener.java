package com.karatek.teamspeakbot.listeners;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.PrivilegeKeyUsedEvent;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.karatek.teamspeakbot.utils.Logger;

public class privKeyListener {

    public static void onPrivKeyUsage(PrivilegeKeyUsedEvent e, TS3Api api) {
        Logger.log("Client " + api.getClientInfo(e.getClientId()).getNickname() + " used a privilege key :" + e.getPrivilegeKey());
				try {
					api.sendPrivateMessage(e.getClientId(), "Your server group was added. The privilege key " + e.getPrivilegeKey() + " is now used." );
				} catch (TS3CommandFailedException ex) {
					Logger.log("TS3CommandFailed (Error 401)");
				}
    }
}
