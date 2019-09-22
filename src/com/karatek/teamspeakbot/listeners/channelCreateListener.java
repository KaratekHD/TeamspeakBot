package com.karatek.teamspeakbot.listeners;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.karatek.teamspeakbot.utils.Logger;

public class channelCreateListener {

    public static void onChannelCreate(ChannelCreateEvent e, TS3Api api) {
        Logger.log("Channel " + api.getChannelInfo(e.getChannelId()).getName() + " was created by " + e.getInvokerName() + " [" + e.getInvokerId() + "]");
    }
}
