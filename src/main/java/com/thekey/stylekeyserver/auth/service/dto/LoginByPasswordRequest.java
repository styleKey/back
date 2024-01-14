package com.thekey.stylekeyserver.auth.service.dto;

public class LoginByPasswordRequest {
    private final String id;
    private final String password;

    public LoginByPasswordRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}