package com.kugelschlag.geth.responseobjs;

/**
 * Created by dbarber on 4/17/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kugelschlag.http.response.BaseResponse;


/**
 * Generates and returns an estimate of how much gas is necessary to allow the transaction to complete.
 * The transaction will not be added to the blockchain.
 * Note that the estimate may be significantly more than the amount of gas actually used by the transaction,
 * for a variety of reasons including EVM mechanics and node performance.
 */
public class EstimateGas extends BaseResponse {

    public static final String ACTION = "eth_estimateGas";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;

    @SerializedName(value = "result")
    @Expose
    private String result;


    @SerializedName(value = "error")
    @Expose
    private Error error;

    public EstimateGas(String id, String jsonrpc) {
        this.id = id;
        this.jsonrpc = jsonrpc;
    }

    /**
     *
     * @return The reponse error object if generated
     */
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    /**
     * @return estimate of how much gas is necessary to allow the transaction to complete
     */
    public String getResult() {
        return result;
    }
}