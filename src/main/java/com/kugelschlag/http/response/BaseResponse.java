package com.kugelschlag.http.response;


public abstract class BaseResponse {



    private String error;
    private String jsonBody = "";

    public boolean isSuccess() {
        return error == null;
    }

    public String getErrorMsg() {
        return error;
    }


    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public String getJsonBody() {
        return jsonBody;
    }


}