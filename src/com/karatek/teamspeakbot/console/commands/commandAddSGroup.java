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
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.utils.Logger;

public class commandAddSGroup {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected, String[] strings) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            if(strings.length != 2) {
                System.out.println("Usage: addsgroup <group id>");
            } else {

                try {
                    api.addClientToServerGroup(Integer.parseInt(strings[1]), selected.getDatabaseId());
                    System.out.println("Done.");
                } catch (TS3CommandFailedException e) {
                    Logger.log("TS3CommandFailed, Maybe that ServerGroup doesn't exist? (Error 401)");
                }


            }
        }
    }
}
