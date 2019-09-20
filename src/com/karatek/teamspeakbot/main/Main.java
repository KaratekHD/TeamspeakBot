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
import com.github.theholywaffle.teamspeak3.api.exception.TS3CommandFailedException;
import com.github.theholywaffle.teamspeak3.api.exception.TS3ConnectionFailedException;
import com.github.theholywaffle.teamspeak3.api.exception.TS3QueryShutDownException;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.karatek.teamspeakbot.main.utils.prefixhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
		final TS3Api api = query.getApi();
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
			public void onTextMessage(TextMessageEvent e) {
				if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
					String message = e.getMessage().toLowerCase();
					if(api.getClientInfo(e.getInvokerId()).isInServerGroup(20345))
					if(message.startsWith("!")) {
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
								System.exit(0);
							default:
								api.sendPrivateMessage(e.getInvokerId(),"Unknown command.");
						}
					}

				} else {
					if(e.getTargetMode() != TextMessageTargetMode.CLIENT && clientId != e.getInvokerId()) {
						System.out.print((char)13);
						System.out.println(prefixhelper.getPrefix() + api.getClientInfo(e.getInvokerId()).getNickname() + "[" + e.getInvokerId() + "]" + " said: " + e.getMessage());
						System.out.print("> ");
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
				System.out.println("\b\b" + prefixhelper.getPrefix() + "The Server was edited by " + serverEditedEvent.getInvokerName());
				log("The Server was edited by " + serverEditedEvent.getInvokerName() + " [" + serverEditedEvent.getInvokerId() + "]");
			}

			@Override
			public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {
				log("The channel " + api.getChannelInfo(channelEditedEvent.getChannelId()).getName() + " was edited by " + channelEditedEvent.getInvokerName() + " [" + channelEditedEvent.getInvokerId() + "]");
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
				log("Channel " + api.getChannelInfo(channelCreateEvent.getChannelId()).getName() + " was created by " + channelCreateEvent.getInvokerName() + " [" + channelCreateEvent.getInvokerId() + "]");
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
				log("Client " + api.getClientInfo(privilegeKeyUsedEvent.getClientId()).getNickname() + " used a privilege key :" + privilegeKeyUsedEvent.getPrivilegeKey());
				try {
					api.sendPrivateMessage(privilegeKeyUsedEvent.getClientId(), "Your server group was added. The privilege key " + privilegeKeyUsedEvent.getPrivilegeKey() + " is now used." );
				} catch (TS3CommandFailedException e) {
					log("TS3CommandFailed (Error 401)");
				}


			}
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int selected_id;
		ClientInfo selected = null;
		while(true) {
			System.out.print("> ");
			input = br.readLine();
			String[] strings = input.split(" ");
			String tosay = "";
			switch (strings[0]) {
				case "exit":
				case "stop":
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
					System.out.println(com.karatek.teamspeakbot.main.utils.prefixhelper.getPrefix() + "Shutting down.");
					System.exit(0);
					break;
				case "say":
					if(strings.length < 2) {
						System.out.println(com.karatek.teamspeakbot.main.utils.prefixhelper.getPrefix() + "Usage: say <text>");
					}
					int i = 1;
					while (i < strings.length) {
						tosay = tosay + " " + strings[i];
						i++;
					}
					api.sendServerMessage(tosay);
					System.out.println(com.karatek.teamspeakbot.main.utils.prefixhelper.getPrefix() + "Broadcasted '" + tosay + "'");
					break;
				case "select":
					if(strings.length != 2) {
						System.out.println("Usage: select <id>");
					} else {
						if(isInteger(strings[1])) {
							selected_id = Integer.parseInt(strings[1]);
							boolean worked = true;
							try {
								selected = api.getClientInfo(selected_id);
							} catch (TS3CommandFailedException e) {
								worked = false;
								System.err.println(prefixhelper.getPrefix() + "TS3CommandFailedException, maybe the client is not online? (Error 401)");
							}
							if (worked) {
								System.out.println("You selected " + selected.getNickname() + ".");
							}
						} else {
							System.out.println("Usage: select <id>");
						}
					}
					break;
				case "whoami":
					System.out.println(clientId);
					break;
				case "info":
					if(selected == null) {
						System.out.println("Please make a selection.");
					} else {
						selected = api.getClientInfo(selected.getId());
						String output_info = "General information\n" +
								"Nickname: " + selected.getNickname() + "\n" +
								"Description: " + selected.getDescription() + "\n" +
								"Location: " + selected.getCountry() + "\n" +
								"Platform: " + selected.getPlatform() + "\n\n" +
								"Channel information\n" +
								"Channel name: " + api.getChannelInfo(selected.getChannelId()).getName() + "\n" +
								"Channel description: " + api.getChannelInfo(selected.getChannelId()).getDescription() + "\n\n" +
								"Internal information\n\n" +
								"ID: " + selected.getId() + "\n" +
								"UUID: " + selected.getUniqueIdentifier() + "\n" +
								"Database ID: " + selected.getDatabaseId() + "\n" +
								"IP: " + selected.getIp()+ "\n";

						System.out.println(output_info);
					}
					break;
				case "kick":
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
					break;
                case "list":
				case "ls":
                    int list_counted = 0;
                    Object[] array = api.getClients().toArray();
					int clients = array.length;
					Client client;
					System.out.println("The following Users are online: \n");
					System.out.println("ID	Nickname");
					while (list_counted < clients) {
                    	Object object = array[list_counted];
                    	String str = object.toString();
                    	str = str.replace("{", "");
						str = str.replace("}", "");
						String[] clientInfo = str.split(",");
						String ls_id = clientInfo[15].replace(" clid=", "");

						if(!ls_id.equals(String.valueOf(clientId))) {
							System.out.println(ls_id + "	" + clientInfo[2].replace(" client_nickname=", ""));
							Client ls_user = api.getClientByNameExact(clientInfo[2].replace(" client_nickname=", ""), false);
						}
						list_counted ++;
					}
					list_counted = list_counted -1;
                    System.out.println("\nTotal: " + list_counted);
                    break;
				case "help":
					System.out.println("List of commands:\n" +
							"'exit' - End this bot\n" +
							"'say <text>' - Send a message to the server.\n" +
							"'ls' - list all clients\n" +
							"'select <id> - select a client\n" +
							"'info' - get information about a client, use 'select' first!\n" +
							"'kick' (<message>) - kicks a client with an optional message, use 'select' first!\n" +
							"'help' - See this list");
					break;
			default:
				System.out.println(prefixhelper.getPrefix() + "Unknown command. Try 'help'!");
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

	public static void log(String string) {
		System.out.println("\b\b" + prefixhelper.getPrefix() + string);
		System.out.print("> ");
	}

}
