package com.kugelschlag.geth.client;

import com.kugelschlag.geth.exceptions.GethEtherException;

/**
 * Created by dbarber on 3/13/18.
 */


public class GethJavaClientDriver {

    GethJavaClientDriver() {
    }

    public static void main(String args[]) {
        connectWithGethEtherServer();
    }


    /**
     * Test driver to connect with a running Geth Ethereum node server
     * Runs a few JSON-REST API commands that Ethereum exposes at the host IP:port
     */
    static private void connectWithGethEtherServer()  {

        //----------------------------------------------------------------------------------------------------------
        //Test Geth Ethereum API with wrapped calls and just print out response
        //----------------------------------------------------------------------------------------------------------

        try {
            GethEtherClient gethEtherClient = new GethEtherClient("http://111.111.111.111:8545");

            System.out.println("Is Syncing - Geth Ethereum Server says:" + gethEtherClient.ethSyncing().getJsonBody());
            System.out.println("Is Syncing - Geth Ethereum Server says:" + gethEtherClient.ethSyncing().getResult().getCurrentlySyncing()+ "\n");

            System.out.println("Gas Price - Geth Ethereum Server says:" + gethEtherClient.gasPrice().getJsonBody());
            System.out.println("Gas Price - Geth Ethereum Server says:" + gethEtherClient.gasPrice().getResult()+ "\n");

            System.out.println("Block Number - Geth Ethereum Server says:" + gethEtherClient.blockNumber().getJsonBody());
            System.out.println("Block Number - Geth Ethereum Server says:" + gethEtherClient.blockNumber().getResult()+ "\n");


            System.out.println("Estimate Gas - Geth Ethereum Server says:" + gethEtherClient.estimateGas().getJsonBody());
            System.out.println("Estimate Gas - Geth Ethereum Server says:" + gethEtherClient.estimateGas().getError().getCode() + "\n");


        } catch (GethEtherException e) {
            e.printStackTrace();
        }

    }

}
