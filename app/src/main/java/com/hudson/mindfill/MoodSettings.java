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
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hudson Hughes on 6/19/2016.
 */
public class MoodSettings {
    Context context;
    public MoodSettings(Context ctx){
        context = ctx;
    }
    public void place(int arg, Date day){
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, Integer> map = getMap();
        map.put(dayLong, arg);
        setMap(map);

    }

    public int retrieve(Date day){
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, Integer> map = getMap();
        Integer arg = map.get(dayLong);
        if(arg == null) return -1;
        return map.get(dayLong);
    }

//    public Set<Integer> getSet(Date day){
//        Calendar calEnd = new GregorianCalendar();
//        calEnd.setTime(day);
//        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
//        calEnd.set(Calendar.HOUR_OF_DAY, 0);
//        calEnd.set(Calendar.MINUTE, 0);
//        calEnd.set(Calendar.SECOND, 0);
//        calEnd.set(Calendar.MILLISECOND, 0);
//        day = calEnd.getTime();
//        MyObject map = getMap();
//        if(map.containsKey(day))
//        return map.get(day);
//        return new HashSet<Integer>();
//    }

    public HashMap<Long, Integer> getMap(){
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) return new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>(){}.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            return object;
        }catch (Exception e){
            e.printStackTrace();
            return new HashMap<Long, Integer>();
        }
    }

    public void setMap(HashMap<Long, Integer> map){
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        prefsEditor.putString("MoodMap", json);
        prefsEditor.commit();
    }

    public HashMap<Date, Integer> getMapDate(HashMap<Date, Integer> map){
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>(){}.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        }catch (Exception e){
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }

        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            fresh.put(new Date((long) pair.getKey()), (Integer) pair.getValue());
        }
        return fresh;
    }

    public HashMap<Date, Integer> getMapLong(HashMap<Date, Integer> map){
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>(){}.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        }catch (Exception e){
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }

        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            fresh.put(new Date((long) pair.getKey()), (Integer) pair.getValue());
        }
        return fresh;
    }

    public long getStartLong(HashMap<Date, Integer> map){
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>(){}.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        }catch (Exception e){
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }
        long date = Long.MAX_VALUE;
        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            if(date > (long) pair.getKey()) date = (long) pair.getKey();
        }
        return date;
    }

    public String getStartString(HashMap<Date, Integer> map){
        long day = getStartLong(map);
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(day)); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

    public String longToString (long arg){
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(arg)); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

    public String dateToString (Date arg){
        Calendar cal = new GregorianCalendar();
        cal.setTime(arg); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

}
