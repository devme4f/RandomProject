package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    private static void permutation(char[] set, String prefix, int n, int k, BufferedWriter bw) throws Exception {
        if (k == 0) {
            bw.write(prefix);
            bw.newLine();
            return;
        }
        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            permutation(set, newPrefix, n, k - 1, bw);
        }
    }

    public static void main(String[] args) {
        System.out.println("Usage: length wordlists string.");
        int LENGTH = 5;//Integer.parseInt(args[0]);
        char[] wordlists = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray();// args[1].toCharArray();

        // Permutation wordlists
        try {
            FileWriter fw = new FileWriter("perms" + LENGTH + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            ArrayList<String> perms = new ArrayList<String>();
            permutation(wordlists, "", wordlists.length, LENGTH, bw);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}