package com.karatek.teamspeakbot.listeners;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.ChannelEditedEvent;
import com.karatek.teamspeakbot.utils.Logger;

public class channelEditListener {

    public static void onChannelEdit(ChannelEditedEvent e, TS3Api api, TS3Query query) {
        Logger.log("The channel " + api.getChannelInfo(e.getChannelId()).getName() + " was edited by " + e.getInvokerName() + " [" + e.getInvokerId() + "]");
    }
}
