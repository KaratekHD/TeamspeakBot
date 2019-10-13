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

import com.karatek.teamspeakbot.main.Main;
import com.karatek.teamspeakbot.resources.colors;

public class Logger {

    public static void log(String string) {
		System.out.println("\r" + prefixhelper.getPrefix() + string);
		logFileHelper.addLineToLog(prefixhelper.getPrefix() + string);
		String id = "";
		if(Main.selected == null) {
			id = "~";
		} else {
			id = Integer.toString(Main.selected.getId());
		}
		System.out.print("\r" + colors.ABSI_BOLD + colors.ANSI_GREEN +  Main.user + "@" + colors.ANSI_GREEN + Main.ip + colors.ANSI_RESET + ":" + colors.ABSI_BOLD + colors.ANSI_BLUE + id +  " $ " + colors.ANSI_RESET);
	}


}
