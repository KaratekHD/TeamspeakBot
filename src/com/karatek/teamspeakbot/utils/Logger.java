package com.karatek.teamspeakbot.utils;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

public class Logger {

    public static void log(String string) {
		System.out.println("\b\b" + prefixhelper.getPrefix() + string);
		System.out.print("> ");
	}


}
