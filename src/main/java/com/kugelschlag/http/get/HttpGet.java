package com.kugelschlag.http.get;

import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

public class HttpGet {

//    public static String JSON_CONTENT_TYPE = "application/json";

    public static final String AUTHORIZATION = "Authorization";

    private final OkHttpClient client = new OkHttpClient();
    private final String host;
    private String token;

    public HttpGet(String host, String token) {
        this.host = host;
        this.token = token;
    }

    public HttpGet(String host) {
        this.host = host;
    }


    /**
     * Do the post without auth token
     *
     * @param body
     * @return
     * @throws IOException
     */
    public String post(String body) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .get()
                .url(host)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }


    /**
     * Uses a bearer auth token (i.e. issued by Digital Ocean)
     * with basic auth instead of username, pass is empty
     *
     * @param body
     * @param token Base64 encoded Basic auth token
     * @return
     * @throws IOException
     */
    public String post(String body, String token) throws IOException {

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader(HttpGet.AUTHORIZATION, token)
                .get()
                .url(host)
                .build();


        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
