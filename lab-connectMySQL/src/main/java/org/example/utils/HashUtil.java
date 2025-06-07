package org.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String hashPassword(String password, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm); // "SHA-256" or "SHA-512"
            byte[] hashBytes = md.digest(password.getBytes());
            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not supported", e);
        }
    }
}

