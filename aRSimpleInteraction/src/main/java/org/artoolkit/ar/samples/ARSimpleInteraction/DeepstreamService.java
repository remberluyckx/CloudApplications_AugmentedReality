package org.artoolkit.ar.samples.ARSimpleInteraction;

import java.net.URISyntaxException;

import io.deepstream.DeepstreamClient;

public class DeepstreamService {
    private static DeepstreamService ourInstance = new DeepstreamService();
    private String userName;

    public static DeepstreamService getInstance() {
        return ourInstance;
    }

    DeepstreamClient deepstreamClient;
    DeepstreamService() {
        try {
            this.deepstreamClient = new DeepstreamClient( "192.168.10.240:6020" );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    DeepstreamClient getDeepstreamClient() {
        return this.deepstreamClient;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
