package org.example;

import org.apache.commons.codec.digest.DigestUtils;
public class Bruteforcing implements Runnable {
    private String salt;
    private String password;
    private String hashed_password;
    private String brute;

    public Bruteforcing(String salt, String password, String hashed_password, String brute) {
        this.salt = salt;
        this.password = password;
        this.hashed_password = hashed_password;
        this.brute = brute;
    }

    @Override
    public void run() {
        String pattern = salt + password + brute;
        if (DigestUtils.sha1Hex(pattern).equalsIgnoreCase(hashed_password)) {
            System.out.println("[FOUND]=====================> " + pattern);
            System.exit(1);
        }
    }
}