package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This project is freezed!!
public class Main {
    private static final int THREADS = 1000;
    private static boolean isMissing(String str) {
        if(str != null && !str.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws  Exception {
        // user input
        int LENGTH = Integer.parseInt(args[0]);
        String salt = args[1];
        String password = args[2];
        String hashed_password = args[3];

        if (isMissing(password) || isMissing(hashed_password)) {
            System.out.println("Missing arguments, expect: length [salt] password hashed_password. Salt could be an empty string!");
            System.exit(0);
        }

        List<Thread> threads = new ArrayList<Thread>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            threads.add(null);
        }
        File f = new File("GeneratePerms\\perms" + LENGTH + ".txt");
        Scanner sn = new Scanner(f);
        List<String> perms = new ArrayList<String>();
        while (sn.hasNextLine()) {
            String data = sn.nextLine();
            perms.add(data);
        }
        sn.close();
        int perms_size = perms.size();
        int running = 0;
        System.out.println(perms);
        System.out.print("Characters: ");
        System.out.println("[INFO] - Start brute forcing with " + THREADS + " threads again " + perms_size + "("+LENGTH+" characters)!...");
        System.out.println("[INFO] - Format: salt + password + string of " + LENGTH + " characters!");
        System.exit(1);
        for (int pdex = 0; pdex < perms_size; pdex++) {
            for (int i = 0; i < THREADS; i++) {
                if (threads.get(i) == null || !threads.get(i).isAlive()) { // null or not alive
                    Runnable task = new Bruteforcing(salt, password, hashed_password, perms.get(pdex));
                    Thread worker = new Thread(task);
                    worker.start();
                    threads.set(i, worker);
                    break;
                }
            }

            if (pdex % 10000 == 0) {
                System.out.println("[STATUS] - Brute forced " + pdex + "!!!");
            }
        }

        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread != null && thread.isAlive()) {
                    running++;
                }
            }
        } while (running > 0);
    }
}