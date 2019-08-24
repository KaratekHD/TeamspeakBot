package com.karatek.teamspeakbot.main.utils;

/*
 * KTS
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class prefixhelper {

    public static String getPrefix() {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return "[" + formatter.format(date) + "] ";
    }
}
