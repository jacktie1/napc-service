package org.apathinternational.faithpathrestful.model.response;

public class ErrorResponse {
    private Integer code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getters and setters...
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode (Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
