package com.karatek.teamspeakbot.utils;

/*
 * TeamspeakBot
 * Copyright (C) 2019 Karatek_HD. Do not distribute!
 */

import com.karatek.teamspeakbot.main.Main;

import java.io.*;
import java.util.Properties;

public class setuper {


    private static void createHostConfig(File configFile, BufferedReader br, String input) {
        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String host = "";
            String user = "";
            String password = "";
            Properties props = new Properties();
            br = new BufferedReader(new InputStreamReader(System.in));
            input = "";
            System.out.println("Karatek Teamspeakbot is not yet configured. Please follow the instructions.\n");

            int status = 1;
            /*
            1 = ip
            2 = user
            3 = password
             */

            while (status < 4 && status > 0) {
                if(status == 1) {
                    System.out.print("Please insert the host: ");
                    try {
                        input = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (input.equals("")) {
                        System.out.println("Invalid host.");
                    } else {
                        host = input;
                        status++;
                    }
                }
                if(status == 2) {
                    System.out.print("Please insert the query user: ");
                    try {
                        input = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (input.equals("")) {
                        System.out.println("Invalid username.");
                    } else {
                        user = input;
                        status++;
                    }
                }
                if(status == 3) {
                    System.out.print("Please insert the query password: ");
                    try {
                        input = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (input.equals("")) {
                        System.out.println("Invalid password.");
                    } else {
                        password = input;
                        status++;
                    }
                }
            }

            System.out.println("\nThis instance is bound to " + user + "." + password + "@" + host + ".");
            System.out.println("If the bot doesn't work, try deleting the file 'host.properties'.\n");


            props.setProperty("password", password);
            props.setProperty("user", user);
            props.setProperty("host", host);

            FileWriter writer = null;
            try {
                writer = new FileWriter(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                props.store(writer, "host settings");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void setup(BufferedReader br, String input) {
        File configFile = new File("host.properties");
        createHostConfig(configFile, br, input);
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            Main.ip = props.getProperty("host");
            Main.user = props.getProperty("user");
            Main.password = props.getProperty("password");
            reader.close();
        } catch (FileNotFoundException ex) {
             // Handle Exception
        } catch (IOException ex) {
            // Handle Exception

        }
    }
}
