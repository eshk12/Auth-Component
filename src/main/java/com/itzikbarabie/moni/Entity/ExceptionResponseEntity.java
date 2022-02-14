package com.itzikbarabie.moni.Entity;

public class ExceptionResponseEntity {
    private String error;

    public ExceptionResponseEntity(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
