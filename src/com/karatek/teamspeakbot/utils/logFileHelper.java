package com.karatek.teamspeakbot.utils;

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

import com.karatek.teamspeakbot.resources.buildInfo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class logFileHelper {
    public static String logfilename = "";

    public static void createLogfile() {
        String fileSeparator = System.getProperty("file.separator");
        File logdir = new File("logs");
        if(!logdir.exists()) {
            logdir.mkdir();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        Date date = new Date();
        logfilename = "KTS-logfile-" + formatter.format(date) + ".log";
        File logfile = new File("logs" + fileSeparator + logfilename);
        try {
            logfile.createNewFile();
        } catch (IOException e) {
            System.err.println("FATAL: couldn't create logfile");
            System.exit(701);
        }
        addLineToLog("# Logfile generated " + formatter.format(date) + " by KTS v" + buildInfo.version);
    }

    public static void addLineToLog(String string) {
        String fileSeparator = System.getProperty("file.separator");
        try(FileWriter fw = new FileWriter("logs" + fileSeparator + logfilename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(string);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
