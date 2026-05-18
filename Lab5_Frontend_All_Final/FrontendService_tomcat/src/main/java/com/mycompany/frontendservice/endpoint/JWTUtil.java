package com.mycompany.frontendservice.endpoint;

import java.util.Base64;

public class JWTUtil {

    private static final String SECRET = "mysecretkey";

    public static String generateToken(String username) {

        String header = Base64.getEncoder().encodeToString("{\"alg\":\"HS256\"}".getBytes());

        String payload = Base64.getEncoder().encodeToString(
                ("{\"user\":\"" + username + "\"}").getBytes()
        );

        String signature = Base64.getEncoder().encodeToString(
                (header + "." + payload + SECRET).getBytes()
        );

        return header + "." + payload + "." + signature;
    }

    public static boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");

            String signatureCheck = Base64.getEncoder().encodeToString(
                    (parts[0] + "." + parts[1] + SECRET).getBytes()
            );

            return signatureCheck.equals(parts[2]);
        } catch (Exception e) {
            return false;
        }
    }

    // 🔥 NEW: extract username
    public static String getUsername(String token) {
        try {
            String[] parts = token.split("\\.");
            String payload = new String(Base64.getDecoder().decode(parts[1]));

            // payload = {"user":"bob"}
            return payload.split("\"")[3];

        } catch (Exception e) {
            return null;
        }
    }
}