package com.thekey.stylekeyserver.s3;

import lombok.Getter;

@Getter
public enum S3ErrorMessage {
    FILE_UPLOAD_FAILED("이미지 파일 업로드에 실패했습니다."),
    FILE_ALREADY_EXISTS("동일한 이름의 이미지 파일이 존재합니다."),
    FAIL_IMAGE_DELETE("이미지 삭제에 실패했습니다."),
    NO_FILE_ITEM_ID("아이템 ID에 해당하는 이미지가 없습니다. item id : ");

    private String message;

    S3ErrorMessage(String message) {
        this.message = message;
    }
}