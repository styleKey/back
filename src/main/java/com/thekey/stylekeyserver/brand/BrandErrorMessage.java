package com.thekey.stylekeyserver.brand;

public enum BrandErrorMessage {
    NOT_FOUND_BRAND("Brand does not found with id: "),
    FILE_ALREADY_EXISTS("File already exists in the bucket."),
    FILE_UPLOAD_FAILED("File upload failed.");

    private String msg;

    BrandErrorMessage(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
