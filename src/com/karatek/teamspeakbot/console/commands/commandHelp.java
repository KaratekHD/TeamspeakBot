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

public class commandHelp {

    public static void execute() {
        System.out.println("List of commands:\n" +
                "'exit' - End this bot\n" +
                "'say <text>' - Send a message to the server.\n" +
                "'ls' - list all clients\n" +
                "'select <id> - select a client\n" +
                "'info' - get information about a client, use 'select' first!\n" +
                "'kick' (<message>) - kicks a client with an optional message, use 'select' first!\n" +
                "'sgroups' - get a list of all server groups\n" +
                "'cgroups' - get a list of all channel groups\n" +
                "'addsgroup <group id>' - add a client to a server group, use 'select' first!\n" +
                "'delsgroup <group id>' - remove a client from a server group, use 'select' first!\n" +
                "'about' - get version informations\n" +
                "'license' - get license informations" +
                "'help' - See this list");
    }
}
