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
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;
import com.karatek.teamspeakbot.main.Main;
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
        Main.end = true;
        System.exit(0);
    }
}
