package com.kugelschlag.geth;

import com.kugelschlag.geth.client.GethEtherClient;
import com.kugelschlag.geth.responseobjs.EthSyncing;
import com.kugelschlag.geth.responseobjs.GasPrice;
import com.kugelschlag.http.post.HttpPost;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GethEtherClientTest {


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HttpPost httpPost;
    private GethEtherClient client;

    @Before
    public void setUp() throws Exception {
        httpPost = mock(HttpPost.class);

        client = new GethEtherClient(httpPost);
    }

    @Test
    public void testGetGasPrice() throws Exception {
        expectJson("eth_gasPrice");

        GasPrice gasPrice = client.gasPrice();
        assertEquals("0x430e23400", gasPrice.getResult());
        assertEquals("1", gasPrice.getId());
        assertEquals("2.0", gasPrice.getJsonrpc());

    }


    @Test
    public void testGetEthSyncingWhenFalse() throws Exception {
        expectJson("eth_syncing_when_false");

        EthSyncing sync = client.ethSyncing();
        assertEquals("false", sync.getResult().getCurrentlySyncing());
        assertEquals("1", sync.getId());
        assertEquals("2.0", sync.getJsonrpc());

    }


    @Test
    public void testGetEthSyncingWhenTrue() throws Exception {
        expectJson("eth_syncing_when_true");

        EthSyncing sync2 = client.ethSyncing();

        assertEquals("1", sync2.getId());
        assertEquals("2.0", sync2.getJsonrpc());

        assertEquals("45425", sync2.getResult().getCurrentBlock());
        assertEquals("2e091e", sync2.getResult().getHighestBlock());
        assertEquals("6c916", sync2.getResult().getKnownStates());
        assertEquals("68beb", sync2.getResult().getPulledStates());
        assertEquals("44e47", sync2.getResult().getStartingBlock());
        assertEquals("true", sync2.getResult().getCurrentlySyncing());
    }

    private void expectJson(String name) throws IOException {
        when(httpPost.post(anyString())).thenReturn(loadJson(name));
    }


    private String loadJson(String name) {
        String filename = "json/" + name + ".json";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename);
        return new Scanner(stream).useDelimiter("\\A").next();
    }



}
