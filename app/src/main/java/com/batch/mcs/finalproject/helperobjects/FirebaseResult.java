package com.batch.mcs.finalproject.helperobjects;

import java.util.logging.StreamHandler;

public class FirebaseResult {

    private Integer result;
    private String message;

    public FirebaseResult(Integer result, String message) {
        this.result = result;
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
