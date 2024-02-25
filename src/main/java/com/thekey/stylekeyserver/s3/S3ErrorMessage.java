package com.thekey.stylekeyserver.s3;

public enum S3ErrorMessage {
    FILE_UPLOAD_FAILED("File upload failed"),
    FILE_ALREADY_EXISTS("File already exists"),
    URL_DECODING_FAILED("URL decoding failed:");
    private String msg;

    S3ErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
