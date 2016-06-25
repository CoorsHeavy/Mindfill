package com.hudson.mindfill;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Hudson Hughes on 5/19/2016.
 */
public class MapSettings {
    Context context;
    public MapSettings(Context ctx){
        context = ctx;
    }
    public void add(int arg, Date day){
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, String> map = getMap();
        String set = map.get(dayLong);
        HashSet<Integer> today;
        if(set == null){
            today = new HashSet<Integer>();
        }else {
            today = deserializeSet(set);
        }
        today.add(arg);
        map.put(dayLong, serializeSet(today));
        setMap(map);
    }

    public void minus(int arg, Date day){
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, String> map = getMap();
        String set = map.get(dayLong);
        HashSet<Integer> today;
        if(set == null){
            today = new HashSet<Integer>();
        }else {
            today = deserializeSet(set);
        }
        today.remove(arg);
        map.put(dayLong, serializeSet(today));
        setMap(map);
    }

    public boolean retrieve(int arg, Date day){
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, String> map = getMap();
        String set = map.get(dayLong);
        if(set == null) return false;
        HashSet<Integer> today = deserializeSet(set);
        if(today.contains(arg)) return true;
        return false;
    }

    public HashMap<Long, String> getMap(){
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("CompletionMap")) return new HashMap<Long, String>();
        String json = mPrefs.getString("CompletionMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, String>>(){}.getType();
            HashMap<Long, String> object = gson.fromJson(json, stringMap);
            return object;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<Long, String>();
        }
    }

    public void setMap(HashMap<Long, String> map){
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        prefsEditor.putString("CompletionMap", json);
        prefsEditor.commit();
    }

    public String serializeSet(HashSet<Integer> set){
        Gson gson = new Gson();
        String result = gson.toJson(set);
        return result;
    }

    public HashSet<Integer> deserializeSet(String string){
        Gson gson = new Gson();
        Type stringSet = new TypeToken<HashSet<Integer>>(){}.getType();
        HashSet<Integer> result = gson.fromJson(string, stringSet);
        return result;
    }
}