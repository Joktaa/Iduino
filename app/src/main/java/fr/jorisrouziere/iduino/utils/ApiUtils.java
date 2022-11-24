package fr.jorisrouziere.iduino.utils;

import android.content.Context;

import java.io.IOException;
import java.util.List;

public class ApiUtils {

    public static void get(Context applicationContext, Context activityContext) {

        new Thread(() -> {
            API api = API.getInstance();
            try {
                // TODO : AJOUTER GET, ME DEMANDER QUAND API + ROUTES TROUVÉES
            } catch (Exception e /*API.ApiErrorException | IOException e*/) {
                e.printStackTrace();
            }
        }).start();
    }
}
