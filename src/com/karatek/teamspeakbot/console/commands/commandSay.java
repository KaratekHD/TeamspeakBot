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
