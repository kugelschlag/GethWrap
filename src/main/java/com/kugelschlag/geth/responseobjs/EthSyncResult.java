package com.kugelschlag.geth.responseobjs;

/**
 * Created by dbarber on 4/17/18.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EthSyncResult {

    @SerializedName("currentBlock")
    @Expose
    private String currentBlock;
    @SerializedName("highestBlock")
    @Expose
    private String highestBlock;
    @SerializedName("knownStates")
    @Expose
    private String knownStates;
    @SerializedName("pulledStates")
    @Expose
    private String pulledStates;
    @SerializedName("startingBlock")
    @Expose
    private String startingBlock;

    private String currentlySyncing;



    public EthSyncResult(String currentBlock, String highestBlock, String knownStates, String pulledStates, String startingBlock) {
        this.currentBlock = currentBlock;
        this.highestBlock = highestBlock;
        this.knownStates = knownStates;
        this.pulledStates = pulledStates;
        this.startingBlock = startingBlock;
     }

    public EthSyncResult() {

    }

    public void setCurrentlySyncing(String syncing) {
        currentlySyncing = syncing;
    }

    public String getCurrentlySyncing() {
        return currentlySyncing;
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public String getHighestBlock() {
        return highestBlock;
    }

    public String getKnownStates() {
        return knownStates;
    }

    public String getPulledStates() {
        return pulledStates;
    }

    public String getStartingBlock() {
        return startingBlock;
    }
}