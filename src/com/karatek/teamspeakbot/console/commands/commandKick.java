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
import com.karatek.teamspeakbot.utils.prefixhelper;

import java.util.concurrent.TimeUnit;

public class commandKick {

    public static void execute(TS3Api api, TS3Query query, ClientInfo selected, String[] strings) {
        if(selected == null) {
            System.out.println("Please make a selection.");
        } else {
            String message_kick = "";
            try {

                int kick_counted = 1;
                while (kick_counted < strings.length) {
                    message_kick = message_kick + " " + strings[kick_counted];
                    kick_counted++;
                }
                if(message_kick.startsWith(" ")) {
                    message_kick = message_kick.substring(1);
                }
                api.kickClientFromServer(message_kick, selected);
            } catch (TS3CommandFailedException e) {
                System.out.println("Internal Error. (Error Code 401)");
            }
            if (message_kick != null) {
                System.out.println("\b\b" + prefixhelper.getPrefix() + "Client " + selected.getNickname() + " was kicked");
            } else {
                System.out.println("\b\b" + prefixhelper.getPrefix() + "Client " + selected.getNickname() + " was kicked for " + message_kick);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(prefixhelper.getPrefix() + "Interrupted. (Error 302)");
            }

        }
    }
}
