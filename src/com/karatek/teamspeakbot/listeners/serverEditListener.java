package com.karatek.teamspeakbot.listeners;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.api.event.ServerEditedEvent;
import com.karatek.teamspeakbot.utils.Logger;

public class serverEditListener {

    public static void onServerEdit(ServerEditedEvent e) {
        Logger.log("The Server was edited by " + e.getInvokerName() + " [" + e.getInvokerId() + "]");
    }
}
