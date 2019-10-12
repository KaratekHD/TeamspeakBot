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
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.karatek.teamspeakbot.utils.Logger;

public class channelCreateListener {

    public static void onChannelCreate(ChannelCreateEvent e, TS3Api api) {
        Logger.log("Channel " + api.getChannelInfo(e.getChannelId()).getName() + " was created by " + e.getInvokerName() + " [" + e.getInvokerId() + "]");
    }
}
