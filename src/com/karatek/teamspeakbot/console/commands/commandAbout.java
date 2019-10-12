package com.karatek.teamspeakbot.console.commands;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.karatek.teamspeakbot.resources.buildInfo;

public class commandAbout {

    public static void execute() {
        System.out.println("Karatek Teamspeakbot v" + buildInfo.version);
        System.out.println("Copyright (C) 2019 Karatek_HD. Do not distribute!");
        System.out.println("Source: https://github.com/KaratekHD/TeamspeakBot");
    }
}
