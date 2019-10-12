package com.karatek.teamspeakbot.utils;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.karatek.teamspeakbot.main.Main;

public class Logger {

    public static void log(String string) {
		System.out.println("\r" + prefixhelper.getPrefix() + string);
		//System.out.print("> ");
		String id = "";
		if(Main.selected == null) {
			id = "~";
		} else {
			id = Integer.toString(Main.selected.getId());
		}
		System.out.print("\r" + Main.user + "@" + Main.ip + ":" + id + "> ");
	}


}
