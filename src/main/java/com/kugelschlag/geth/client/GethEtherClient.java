package com.kugelschlag.geth.client;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kugelschlag.geth.exceptions.GethEtherException;
import com.kugelschlag.geth.responseobjs.BlockNumber;
import com.kugelschlag.geth.responseobjs.EstimateGas;
import com.kugelschlag.geth.responseobjs.EthSyncing;
import com.kugelschlag.geth.responseobjs.GasPrice;
import com.kugelschlag.geth.responseobjs.deserializers.Deserializer;
import com.kugelschlag.http.post.HttpPost;
import com.kugelschlag.http.request.Request;
import com.kugelschlag.http.response.BaseResponse;

import java.io.IOException;

public class GethEtherClient {
    
    public static final String ACTION_METHOD_KEY = "method";

    private final HttpPost httpPost;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(EthSyncing.class, Deserializer.getEthSyncingDeserializer()) //adapter only affects EthSyncing classes
            .registerTypeAdapter(EstimateGas.class, Deserializer.getEstimateGasDeserializer()) //adapter only affects EstimateGas classes
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();


    /**
     * Constructors
     *
     * @param host
     */
    public GethEtherClient(String host) {
        this.httpPost = new HttpPost(host);
    }

    public GethEtherClient(HttpPost httpPost) {
        this.httpPost = httpPost;
    }


    //-----------------------------------------------------------------------------------
    //Provision/populate the response object with data from server
    //-----------------------------------------------------------------------------------

    /**
     * EthSyncing - is the geth server syncing at this moment?
     *
     * @return Response - Returns an object with data about the sync status or false.
     */
    public EthSyncing ethSyncing() {
        Request request = Request.action(ACTION_METHOD_KEY, EthSyncing.ACTION)
                .param("jsonrpc", "2.0")
                .param("params", "[]")
                .param("id", "1")
                .build();

        return provisionReponse(request, EthSyncing.class);

    }


    /**
     * Gas Price
     * @return Response - Returns the current price per gas in wei reponse.
     */
    public GasPrice gasPrice() {
        Request request = Request.action(ACTION_METHOD_KEY, GasPrice.ACTION)
                .param("jsonrpc", "2.0")
                .param("params", "[]")
                .param("id", "1")
                .build();

        return provisionReponse(request, GasPrice.class);

    }


    /**
     * Block Number
     * @return Response - Returns the number of most recent block.
     */
    public BlockNumber blockNumber() {
        Request request = Request.action(ACTION_METHOD_KEY, BlockNumber.ACTION)
                .param("jsonrpc", "2.0")
                .param("params", "[]")
                .param("id", "1")
                .build();

        return provisionReponse(request, BlockNumber.class);

    }


    /**
     * Estimate Gas
     * @return Reponse - Estimate of how much gas is necessary to allow the transaction to complete
     */
    public EstimateGas estimateGas() {
        Request request = Request.action(ACTION_METHOD_KEY, EstimateGas.ACTION)
                .param("jsonrpc", "2.0")
                .param("params", "[]")
                .param("id", "1")
                .build();

        return provisionReponse(request, EstimateGas.class);

    }





    //-----------------------------------------------------------------------------------
    // Responses
    //-----------------------------------------------------------------------------------

    private <T extends BaseResponse> T provisionReponse(Request request, Class<T> classType) {

        try {
            String json = gson.toJson(request.getMap());
//            System.out.println("*****" + json);



            String body = httpPost.post(json);
//            System.out.println("***** body" + body);

            T t = gson.fromJson(body, classType);

            t.setJsonBody(body);

            if (!t.isSuccess()) {
                throw new GethEtherException(t.getErrorMsg());
            }

            //return the provisioned response object
            return t;


        } catch (IOException e) {
            throw new GethEtherException("Eth - Unable to communicate with node, correct IP:port address?", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
