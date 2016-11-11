package com.hudson.mindfill;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Hudson Hughes on 6/6/2016.
 */
public class Management {
    public static ArrayList<Integer> getList(Context context){
        ArrayList<Integer> ints = new ArrayList<Integer>();
        SharedPreferences prefs = context.getSharedPreferences("Hudson", Context.MODE_PRIVATE);
        JSONArray object = null;
        try {
            object = new JSONArray(prefs.getString("list", ""));
        } catch (JSONException e) {
            return new ArrayList<Integer>();
        }
        for (int i = 0; i < object.length(); i++) {
            try {
                ints.add(object.getInt(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ints;
    }
}
