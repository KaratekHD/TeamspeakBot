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
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;
import com.karatek.teamspeakbot.main.Main;
import com.karatek.teamspeakbot.utils.Logger;
import com.karatek.teamspeakbot.utils.prefixhelper;

public class textMessageListener {

    public static void onText(TextMessageEvent e, TS3Api api, TS3Query query) {
        if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != api.whoAmI().getId()) {
					String message = e.getMessage().toLowerCase();
					if(api.getClientInfo(e.getInvokerId()).isInServerGroup(20345))
					if(message.startsWith("!")) {
						Logger.log(api.getClientInfo(e.getInvokerId()).getNickname() + " issued bot command: " + e.getMessage());
						switch (message) {
							case "!ping":
								api.sendPrivateMessage(e.getInvokerId(),"pong");
								break;
							case "!exit":
								api.sendServerMessage("I am offline!");
								try{ api.setNickname("KTS_Internal"); }catch (Exception ignored){ }
								try {
									query.exit();
								} catch (TS3QueryShutDownException ex) {
									System.err.println(prefixhelper.getPrefix() + "FATAL: TS3QuerySHutdownException (Error 402)");
									System.exit(402);
								}
								System.out.print((char)13);
								System.out.println(prefixhelper.getPrefix() + "Shutting down.");
								Main.end = true;
								System.exit(0);
							default:
								api.sendPrivateMessage(e.getInvokerId(),"Unknown command.");
						}
					}

				} else {
					if(e.getTargetMode() != TextMessageTargetMode.CLIENT && api.whoAmI().getId() != e.getInvokerId()) {
                        Logger.log(api.getClientInfo(e.getInvokerId()).getNickname() + " [" + e.getInvokerId() + "]" + " said: " + e.getMessage());
					}
				}
    }
}
