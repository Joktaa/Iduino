package fr.jorisrouziere.iduino.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {

    private static final String BASE_URL = "https://data.opendatasoft.com/api/records/1.0/search/?dataset=osm-fr-bars%40babel&q=&rows=3000&facet=brewery&facet=wheelchair&facet=toilets_wheelchair&facet=addr_postcode&timezone=Europe%2FParis";

    private static API sAPI;
    private final OkHttpClient mHttpClient;


    private API() {
        mHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static API getInstance() {
        if (sAPI == null) {
            sAPI = new API();
        }
        return sAPI;
    }

    private Response getSynchronous(String path) throws IOException {
        return mHttpClient.newCall(buildGet(path)).execute();
    }

    private Request buildGet(String path) {
        return new Request
                .Builder()
                .url(String.format("%s%s", BASE_URL, path))
                .build();
    }

    public String getBars() throws IOException {
        Response listResponse = getSynchronous("");

        if (HttpsURLConnection.HTTP_OK == listResponse.code()) {
            return listResponse.body().string();
        } else {
            return null;
        }
    }
}
