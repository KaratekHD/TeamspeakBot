package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
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
                "'help' - See this list");
    }
}
