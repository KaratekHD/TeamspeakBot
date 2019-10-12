package com.karatek.teamspeakbot.console;

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
import com.karatek.teamspeakbot.console.commands.*;
import com.karatek.teamspeakbot.main.Main;
import com.karatek.teamspeakbot.resources.colors;
import com.karatek.teamspeakbot.utils.prefixhelper;

import java.io.BufferedReader;
import java.io.IOException;


public class Console {

    public static ClientInfo selected;

    public static void startConsole(Main main, TS3Api api, TS3Query query, BufferedReader br, ClientInfo selectedInternal, int clientId) throws IOException {
        String id;
        selected = selectedInternal;
        while(true) {
            if(selected == null) {
                id = "~";
            } else {
                id = Integer.toString(selected.getId());
            }
            System.out.print("\r" + colors.ABSI_BOLD + colors.ANSI_GREEN +  Main.user + "@" + colors.ANSI_GREEN + Main.ip + colors.ANSI_RESET + ":" + colors.ABSI_BOLD + colors.ANSI_BLUE + id +  " $ " + colors.ANSI_RESET);
            String input = "";

            input = br.readLine();
            String[] strings = input.split(" ");
            switch (strings[0]) {
                case "exit":
                case "stop":
                    commandExit.execute(api, query);
                    break;
                case "say":
                    commandSay.execute(api, query, strings);
                    break;
                case "select":
                    if(strings.length != 2) {
                        System.out.println("Usage: select <id>");
                    } else {
                        if(isInteger(strings[1])) {
                            int selected_id = Integer.parseInt(strings[1]);
                            boolean worked = true;
                            try {
                                selected = api.getClientInfo(selected_id);
                            } catch (TS3CommandFailedException e) {
                                worked = false;
                                System.err.println(prefixhelper.getPrefix() + "TS3CommandFailedException, maybe the client is not online? (Error 401)");
                            }
                            if (worked) {
                                System.out.println("You selected " + selected.getNickname() + ".");
                                Main.selected = selected;
                            }
                        } else {
                            if(strings[1].equals("~")) {
                                selected = null;
                                Main.selected = selected;
                                System.out.println("Unselected.");
                            } else {
                                System.out.println("Usage: select <id>");
                            }

                        }
                    }
                    break;
                case "license":
                    commandLicense.execute();
                    break;
                case "whoami":
                    System.out.println(clientId);
                    break;
                case "info":
                    commandInfo.execute(api, query, selected);
                    break;
                case "sgroups":
                    commandSgroups.execute(api, query);
                    break;
                case "cgroups":
                    commandCgroups.execute(api, query);
                    break;
                case "kick":
                    commandKick.execute(api, query, selected, strings);
                    break;
                case "list":
                case "ls":
                    commandList.execute(api, query, selected, strings);
                    break;
                case "addsgroup":
                    commandAddSGroup.execute(api, query, selected, strings);
                    break;
                case "delsgroup":
                    commandDelSGroup.execute(api, query, selected, strings);
                    break;
                case "help":
                    commandHelp.execute();
                    break;
                case "about":
                    commandAbout.execute();
                    break;
                default:
                    if(!strings[0].equals("")) {
                        System.out.println(prefixhelper.getPrefix() + "Unknown command. Try 'help'!");
                    }

            }

        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
