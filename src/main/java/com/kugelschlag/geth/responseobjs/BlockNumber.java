package com.kugelschlag.geth.responseobjs;

/**
 * Created by dbarber on 4/17/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kugelschlag.http.response.BaseResponse;


/**
 * Returns the number of most recent block.
 */
public class BlockNumber extends BaseResponse {

    public static final String ACTION = "eth_blockNumber";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName(value = "result")
    @Expose
    private String result;


    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    /**
     *
     * @return number of the current block number the client is on.
     */
    public String getResult() {
        return result;
    }

}