package fr.jorisrouziere.iduino.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API {

    // TODO : AJOUTER URL
    private static final String BASE_URL = "...";

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

    private void handleApiError(Response response) throws ApiErrorException, IOException {
        if (response.code() >= HttpsURLConnection.HTTP_BAD_REQUEST) {
            throw new ApiErrorException(Objects.requireNonNull(response.body()).string());
        }
    }

    private List<?> handleApiErrorList(Response response) throws ApiErrorException, IOException {
        if (HttpsURLConnection.HTTP_NOT_FOUND == response.code()) {
            response.close();
            return new ArrayList<>();
        } else {
            throw new ApiErrorException(Objects.requireNonNull(response.body()).string());
        }
    }

    public static class ApiErrorException extends Exception {
        public ApiErrorException(String message) {
            super(message);
        }
    }

    // TODO : AJOUTER GET, ME DEMANDER QUAND API + ROUTES TROUVÉES
}
