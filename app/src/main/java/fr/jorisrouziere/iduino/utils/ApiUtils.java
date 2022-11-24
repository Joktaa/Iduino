package fr.jorisrouziere.iduino.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.jorisrouziere.iduino.model.Bar;

public class ApiUtils {

    public static void get() {
        new Thread(() -> {
            ArrayList<Bar> bars = new ArrayList<>();
            JSONArray array = null;
            API api = API.getInstance();
            try {
                JSONObject j = new JSONObject(api.getBars());
                array =  j.getJSONArray("records");
            } catch (Exception e) {e.printStackTrace();}
            JSONObject obj = null;
            JSONObject fields = null;
            Bar bar;
            for (int i = 0; i < array.length(); i++) {
                try {
                    obj = array.getJSONObject(i);
                    fields = obj.getJSONObject("fields");
                } catch (JSONException e) {e.printStackTrace(); continue;}
                bar = new Bar();

                try {bar.setId(obj.getString("recordid"));} catch (JSONException ignored) {}
                try {bar.setName(fields.getString("name"));} catch (JSONException ignored) {}
                try {bar.setPhone(fields.getString("phone"));} catch (JSONException ignored) {}
                try {bar.setLat((Double) fields.getJSONArray("geo_point_2d").get(0));} catch (JSONException ignored) {}
                try {bar.setLon((Double) fields.getJSONArray("geo_point_2d").get(1));} catch (JSONException ignored) {}

                bars.add(bar);
            }
        }).start();
    }
}
