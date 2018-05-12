package com.kugelschlag.geth.responseobjs.deserializers;

import com.google.gson.*;
import com.kugelschlag.geth.responseobjs.EstimateGas;
import com.kugelschlag.geth.responseobjs.EthSyncing;
import com.kugelschlag.geth.responseobjs.EthSyncResult;
import com.kugelschlag.geth.responseobjs.Error;

import java.lang.reflect.Type;

/**
 * Created by dbarber on 4/18/18.
 */
public class Deserializer {

    /**
     * Change serialization for specific ethSyncing endpoint on Ethereum node server depending on result
     */
    static private JsonDeserializer<EthSyncing> ethSyncingDeserializer = new JsonDeserializer<EthSyncing>() {

        @Override
        public EthSyncing deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


            JsonObject jsonObject = json.getAsJsonObject();

            EthSyncing ethObj = new EthSyncing(
                    jsonObject.get("id").getAsString(),
                    jsonObject.get("jsonrpc").getAsString()
            );

            //"result" is either returned from server in JSON as a string or an object, we need to de-serialize it accordingly
            if (jsonObject.has("result")) {

                JsonElement resultElem = jsonObject.get("result");


                if (resultElem != null && !resultElem.isJsonNull() && !resultElem.isJsonObject()) {

                    String resultStr = resultElem.getAsString();

                    if (resultStr.equalsIgnoreCase("false")) {
                        EthSyncResult resultObj = new EthSyncResult();
                        resultObj.setCurrentlySyncing(resultStr);
                        ethObj.setResult(resultObj);


                        //System.out.println("****** Eth Syncing False" + new Gson().toJson(ethObj));
                    }

                    //ethereum node server *is* synching and results is an object
                } else if (resultElem != null) {

                    JsonObject jsonObj = resultElem.getAsJsonObject();


                    //Make sure the order here matches the parameter order of the EthSyncResult constructor!
                    EthSyncResult resultObj = new EthSyncResult(
                            jsonObj.get("currentBlock").getAsString(),
                            jsonObj.get("highestBlock").getAsString(),
                            jsonObj.get("knownStates").getAsString(),
                            jsonObj.get("pulledStates").getAsString(),
                            jsonObj.get("startingBlock").getAsString()

                    );

                    resultObj.setCurrentlySyncing("true");
                    ethObj.setResult(resultObj);

                    //System.out.println("****** Eth Syncing True" + new Gson().toJson(ethObj));
                }
            }

            return ethObj;
        }
    };


    /**
     * Change serialization for specific EstimateGas endpoint on Ethereum node server depending on result
     */
    static private JsonDeserializer<EstimateGas> estimateGasDeserializer = new JsonDeserializer<EstimateGas>() {

        @Override
        public EstimateGas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


            JsonObject jsonObject = json.getAsJsonObject();

            EstimateGas estimateGasObj = new EstimateGas(
                    jsonObject.get("id").getAsString(),
                    jsonObject.get("jsonrpc").getAsString()
            );

            if (jsonObject.has("result")) {

                JsonElement resultElem = jsonObject.get("result");
//                System.out.println("Result ************** " + resultElem.toString());


            }


            if (jsonObject.has("error")) {

                JsonElement errorElem = jsonObject.get("error");

                JsonObject jsonObj = errorElem.getAsJsonObject();
                Error error = new Error(
                        jsonObj.get("code").getAsString(),
                        jsonObj.get("message").getAsString()
                );
                estimateGasObj.setError(error);
            }
            return estimateGasObj;
        }


    };


    /**
     * Get the ethSyncing deserializer
     *
     * @return JsonDeserializer<EthSyncing>
     */
    public static JsonDeserializer<EthSyncing> getEthSyncingDeserializer() {
        return ethSyncingDeserializer;
    }


    /**
     * Get the EstimateGas deserializer
     *
     * @return JsonDeserializer<EstimateGas>
     */
    public static JsonDeserializer<EstimateGas> getEstimateGasDeserializer() {
        return estimateGasDeserializer;
    }
}
