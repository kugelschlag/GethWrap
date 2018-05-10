package com.kugelschlag.geth.responseobjs;

/**
 * Created by dbarber on 4/18/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Error response object that is returned by a mal-formed request to Ethereum node Server
 */
public class Error {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;


    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}