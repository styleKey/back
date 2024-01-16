package com.thekey.stylekeyserver;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateJWTSecretKey {
    public static void main(String[] args) {
        byte[] randomBytes = new byte[32]; 
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        String jwtSecretKey = Base64.getEncoder().encodeToString(randomBytes);

        System.out.println("Generated JWT Secret Key: " + jwtSecretKey);
    }
}
