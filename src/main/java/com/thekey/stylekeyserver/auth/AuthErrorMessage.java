package com.thekey.stylekeyserver.auth;

public enum AuthErrorMessage {
    ALREADY_REGISTERED("이미 가입되었습니다.");

    private String msg;

    AuthErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
