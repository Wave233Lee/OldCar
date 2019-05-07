package com.example.oldcar.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class MyComparatorUtil implements Comparator<JSONObject> {

    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        Double a = null;
        try {
            a = o1.getDouble("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Double b = null;
        try {
            b = o2.getDouble("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (int) (b-a);
    }
}
