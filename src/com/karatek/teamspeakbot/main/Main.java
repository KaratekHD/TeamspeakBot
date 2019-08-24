package com.karatek.teamspeakbot.main;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.karatek.teamspeakbot.main.utils.prefixhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

	private static String ip = "gamelts.nitrado.net";
	private static String user = "botti";
	private static String password = "Srg5RyNR";

	private static prefixhelper prefixhelper = new prefixhelper();

	public static void main(String[] args) throws IOException {
		System.out.println("Karatek Teamspeak Bot v1.0");
		System.out.println("Copyright (C) 2019 Karatek_HD. Do not distribute!");
		System.out.println("");
		final TS3Config config = new TS3Config();
		config.setHost(ip);

		System.out.println(prefixhelper.getPrefix() + "Connecting to " + ip + " with queryusername " + user + "...");
		final TS3Query query = new TS3Query(config);
		query.connect();
		final TS3Api api = query.getApi();
		api.login(user, password);
		api.selectVirtualServerByPort(12500);
		api.setNickname("Karatek Teamspeak Bot");
		System.out.println(prefixhelper.getPrefix() + "Connected successfully!");
		api.registerAllEvents();
		// Get our own client ID by running the "whoami" command
		final int clientId = api.whoAmI().getId();

		// Listen to chat in the channel the query is currently in
		// As we never changed the channel, this will be the default channel of the server
		api.addTS3Listeners(new TS3Listener() {

			@Override
			public void onTextMessage(TextMessageEvent e) {
				if (e.getTargetMode() == TextMessageTargetMode.CHANNEL && e.getInvokerId() != clientId) {
					String message = e.getMessage().toLowerCase();
					if(message.startsWith("!")) {
						switch (message) {
							case "!ping":
								api.sendChannelMessage("pong");
								break;
							case "!exit":
								api.sendChannelMessage("I am offline!");
								try{ api.setNickname("NameHere"); }catch (Exception ignored){ }
								query.exit();
								System.out.println(prefixhelper.getPrefix() + "Shutting down.");
								System.exit(0);

							case "!hello":
								api.sendChannelMessage("Hello " + e.getInvokerName() + "!");
								break;
							default:
								api.sendChannelMessage("Unknown command.");
						}
					}

				}

			}

			@Override
			public void onClientJoin(ClientJoinEvent clientJoinEvent) {
				System.out.print((char)13);
				System.out.println(prefixhelper.getPrefix() + "Client " + clientJoinEvent.getClientNickname() + " (ID: " + clientJoinEvent.getClientId() + ") logged in from " + clientJoinEvent.getClientCountry() + "!");
				System.out.print("> ");
			}

			@Override
			public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
				System.out.print((char)13);
				System.out.println(prefixhelper.getPrefix() + "Client " + clientLeaveEvent.getClientId() + " logged out!");
				System.out.print("> ");
			}

			@Override
			public void onServerEdit(ServerEditedEvent serverEditedEvent) {

			}

			@Override
			public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

			}

			@Override
			public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

			}

			@Override
			public void onClientMoved(ClientMovedEvent clientMovedEvent) {
				System.out.print((char)13);
				System.out.println(prefixhelper.getPrefix() + "Client " + api.getClientInfo(clientMovedEvent.getClientId()).getNickname() + " was moved to " + api.getChannelInfo(clientMovedEvent.getTargetChannelId()).getName());
				if(api.getChannelInfo(clientMovedEvent.getTargetChannelId()).getName().startsWith("╔ Support - Warteschlange")) {
					if(api.getClientInfo(clientMovedEvent.getClientId()).isInServerGroup(20345)) {

					} else {
						System.out.println(prefixhelper.getPrefix() + "Client " + api.getClientInfo(clientMovedEvent.getClientId()).getNickname() + " is waiting for support!");
						List<Client> clients = api.getClients();
						for (Client client : clients) {
							if (client.isInServerGroup(20345)) {
								String pushmsg = "Client '" + api.getClientInfo(clientMovedEvent.getClientId()).getNickname() + "' is waiting for support!";
								api.sendPrivateMessage(client.getId(), pushmsg);
							}
						}
					}
					}
				System.out.print("> ");
				}


			@Override
			public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

			}

			@Override
			public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

			}

			@Override
			public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

			}

			@Override
			public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

			}

			@Override
			public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

			}
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("> ");
			switch (br.readLine()) {
				case "exit":
					api.sendChannelMessage("I am offline!");
					try{ api.setNickname("NameHere"); }catch (Exception ignored){ }
					query.exit();
					System.out.print((char)13);
					System.out.println(com.karatek.teamspeakbot.main.utils.prefixhelper.getPrefix() + "Shutting down.");
					System.exit(0);
					break;
			default:
				System.out.println(prefixhelper.getPrefix() + "Unknown command.");
			}

		}

	}
}
