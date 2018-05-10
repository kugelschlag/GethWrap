package com.kugelschlag.geth.responseobjs;

/**
 * Created by dbarber on 4/17/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kugelschlag.http.response.BaseResponse;

public class EthSyncing extends BaseResponse {

    public static final String ACTION = "eth_syncing";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName(value = "result")
    @Expose
    private EthSyncResult result;

    public EthSyncing(String id, String jsonrpc) {
        this.id = id;
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public EthSyncResult getResult() {
        return result;
    }

    public void setResult(EthSyncResult r) {
        this.result = r;
    }
}