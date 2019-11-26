package com.karatek.teamspeakbot.main;

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
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.exception.TS3ConnectionFailedException;
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.console.Console;
import com.karatek.teamspeakbot.listeners.*;
import com.karatek.teamspeakbot.utils.Logger;
import com.karatek.teamspeakbot.utils.logFileHelper;
import com.karatek.teamspeakbot.utils.prefixhelper;
import com.karatek.teamspeakbot.utils.setuper;

import java.io.*;

public class Main {

	public static final  TS3Query ts3Query = null;
	public static final TS3Config config = new TS3Config();
	public static TS3Query query = null;
	public static TS3Api api = null;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static String input;
	public static ClientInfo selected = null;
	public static String ip = "";
	public static String user = "";
	public static String password = "";
	public static boolean end = false;

	private static prefixhelper prefixhelper = new prefixhelper();




	public static void main(String[] args) throws IOException {
		System.out.println("Karatek TeamspeakBot Copyright (C) 2019  Karatek_HD\n" +
                "This program comes with ABSOLUTELY NO WARRANTY.\n" +
                "This is free software, and you are welcome to redistribute it\n" +
                "under certain conditions; type `license' for details.\n");
		logFileHelper.createLogfile();
		setuper.setup(br, input);
		config.setHost(ip);

		System.out.println(prefixhelper.getPrefix() + "Connecting to " + ip + " with queryusername " + user + "...");
		logFileHelper.addLineToLog("Connecting to " + ip + " with queryusername " + user + "...");
		query = new TS3Query(config);
		try {
			query.connect();
		} catch (TS3CommandFailedException e) {
			System.err.println(prefixhelper.getPrefix() + "FATAL: TS3CommandFailed (Error 401)");
			System.err.println(prefixhelper.getPrefix() + "Ending.");
			System.exit(401);
		} catch (TS3ConnectionFailedException e) {
			System.out.println("FATAL: TS3ConnectionFailed (Error 304)");
			System.err.println(prefixhelper.getPrefix() + "Ending.");
			System.exit(304);
		}
		api = query.getApi();
		if(!query.isConnected()) {
			System.err.println(prefixhelper.getPrefix() + "FATAL: TS3ConnectionFailed (Error 304)");
			System.err.println(prefixhelper.getPrefix() + "Ending.");
			System.exit(304);
		}
		api.login(user, password);
		api.selectVirtualServerByPort(12500);
		try {
			api.setNickname("Karatek Teamspeak Bot");
		} catch (TS3CommandFailedException e) {
			System.err.println(prefixhelper.getPrefix() + "FATAL: TS3CommandFailed (Error 401)");
			System.err.println(prefixhelper.getPrefix() + "Ending.");
			System.exit(401);
		}

		logFileHelper.addLineToLog("Connected successfully!");
		System.out.println(prefixhelper.getPrefix() + "Connected successfully!");
		api.sendServerMessage("I am online!");
		api.registerAllEvents();
		// Get our own client ID by running the "whoami" command
		final int clientId = api.whoAmI().getId();

		// Listen to chat in the channel the query is currently in
		// As we never changed the channel, this will be the default channel of the server
		api.addTS3Listeners(new TS3Listener() {

			@Override
			public void onTextMessage(TextMessageEvent textMessageEvent) {
				textMessageListener.onText(textMessageEvent, api, query);
			}

			@Override
			public void onClientJoin(ClientJoinEvent clientJoinEvent) {
				clientJoinListener.onJoin(clientJoinEvent, api, query);
			}

			@Override
			public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
				clientLeaveListener.onLeave(clientLeaveEvent, query, api);
			}

			@Override
			public void onServerEdit(ServerEditedEvent serverEditedEvent) {
				serverEditListener.onServerEdit(serverEditedEvent);
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {
				channelEditListener.onChannelEdit(channelEditedEvent, api, query);
			}

			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

			}

			@Override
			public void onClientMoved(ClientMovedEvent clientMovedEvent) {
				clientMoveListener.onClientMove(clientMovedEvent, api, query);
			}


			@Override
			public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {
				channelCreateListener.onChannelCreate(channelCreateEvent, api);
			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {
				log("Channel " + api.getChannelInfo(channelDeletedEvent.getChannelId()).getName() + " was deleted by " + channelDeletedEvent.getInvokerName() + " [" + channelDeletedEvent.getInvokerId() + "]");
			}

			@Override
			public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {
				log("Channel " + api.getChannelInfo(channelMovedEvent.getChannelId()).getName() + " was moved by " + channelMovedEvent.getInvokerName());
			}

			@Override
			public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {
				log("The password of channel " + api.getChannelInfo(channelPasswordChangedEvent.getChannelId()).getName() + " was changed by " + channelPasswordChangedEvent.getInvokerName());
			}

			@Override
			public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {
				privKeyListener.onPrivKeyUsage(privilegeKeyUsedEvent, api);
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run() {
				if(!end) {
					Logger.log("Trying to finish politely ...");
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
				}
			}
		});
		Console.startConsole(new Main(), api, query, br, selected, clientId);

	}

	public static void log(String string) {
		Logger.log(string);
	}

}
