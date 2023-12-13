package com.fantasy.fantasyleague.FaqRule.Model;

public enum Response {
    INSERT_SUCCESS("Inserted Successfully"),
    UPDATE_SUCCESS("Updated Successfully"),
    DELETE_SUCCESS("Deleted Successfully"),
    DELETE_FAIL("Unsuccessful Delete"),
    INSERT_FAIL("Unsuccessful Insert"),
    UPDATE_FAIL("Unsuccessful Update");

    private final String message;

    Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
