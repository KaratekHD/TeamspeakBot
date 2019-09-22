package com.karatek.teamspeakbot.main;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.exception.TS3ConnectionFailedException;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.console.Console;
import com.karatek.teamspeakbot.listeners.*;
import com.karatek.teamspeakbot.utils.prefixhelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static final  TS3Query ts3Query = null;
	public static final TS3Config config = new TS3Config();
	public static TS3Query query = null;
	public static TS3Api api = null;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static String input;
	public static int selected_id;
	public static ClientInfo selected = null;
	public static String ip = "gamelts.nitrado.net";
	public static String user = "botti";
	private static String password = "Srg5RyNR";

	private static prefixhelper prefixhelper = new prefixhelper();



	public static void main(String[] args) throws IOException {
		System.out.println("Karatek Teamspeak Bot v1.1");
		System.out.println("Copyright (C) 2019 Karatek_HD. Do not distribute!");
		System.out.println("");
		config.setHost(ip);

		System.out.println(prefixhelper.getPrefix() + "Connecting to " + ip + " with queryusername " + user + "...");
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
		Console.startConsole(new Main(), api, query, input, br, selected_id, selected, clientId);

	}



	public static void log(String string) {
		System.out.println("\b\b" + prefixhelper.getPrefix() + string);
		System.out.print("> ");
	}

}
