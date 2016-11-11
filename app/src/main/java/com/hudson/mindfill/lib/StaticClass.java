package com.hudson.mindfill.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hudson.mindfill.MyApplication;
import com.hudson.mindfill.R;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hudsonhughes on 8/18/16.
 */
public class StaticClass {
    private static StaticClass helper;
    private static StaticClass mapSettings = null;
    private Moshi moshi;

    public static StaticClass getIntstnace() {
        if (mapSettings != null) {
            return mapSettings;
        } else {
            return mapSettings = new StaticClass(MyApplication.getContext());
        }
    }

    private StaticClass(Context context) {
        this.context = context;
        this.mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        this.moshi = new Moshi.Builder()
                .build();
        Type listMyData = Types.newParameterizedType(List.class, CustomTile.class);
        JsonAdapter<List<CustomTile>> adapter = moshi.adapter(listMyData);

    }

    Context context;
    SharedPreferences mPrefs;

    public JSONObject getTreatmentObject(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    public String getTreatmentName(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "";
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getTreatmentShopping(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "";
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("shopping");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getTreatmentExamine(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "";
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("examine");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getAmazonLink(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "";
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("shopping");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getTreatmentDescription(int index) {
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "Description Here";
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("Description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        name = name + "\n" + getTreatmentExamine(index);
        return name;
    }

    public List<String> getTreatmentEvidence(int index) {
        JSONObject obj = null;
        ArrayList<String> result = new ArrayList<String>();
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        JSONArray name = new JSONArray();
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getJSONArray("Sources");
            for (int i = 0; i < name.length(); i++) {
                result.add(name.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public int getAmount() {
        int itemAmount = 0;
        String name = "";
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            itemAmount = m_jArry.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemAmount;
    }

    public ArrayList<Integer> getArrayIntList() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int itemAmount = 0;
        String name = "";
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            itemAmount = m_jArry.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < itemAmount; i++) result.add(i);
        return result;
    }

    public int getCategoryResource(int index) {
        int cat = new Random().nextInt(2);
        if (cat == 0) {
            return R.drawable.roundtiles1;
        }
        if (cat == 1) {
            return R.drawable.roundtiles2;
        }
        if (cat == 2) {
            return R.drawable.roundtiles3;
        }
        return R.drawable.roundtiles1;
    }

    public int getCategory() {
        int cat = new Random().nextInt(2);
        if (cat == 0) {
            return 0;
        }
        if (cat == 1) {
            return 1;
        }
        if (cat == 2) {
            return 2;
        }
        return 0;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("item.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void putList(ArrayList<Integer> ints) {
        JSONArray object = new JSONArray();
        for (int i = 0; i < ints.size(); i++) {
            object.put(ints.get(i));
        }
        SharedPreferences prefs = context.getSharedPreferences("Hudson", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("list", object.toString());
        editor.commit();
    }

    public ArrayList<Integer> getList() {
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

    public void add(int arg, Date day) {
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
        if (set == null) {
            today = new HashSet<Integer>();
        } else {
            today = deserializeSet(set);
        }
        today.add(arg);
        map.put(dayLong, serializeSet(today));
        setMapString(map);
    }

    public void minus(int arg, Date day) {
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
        if (set == null) {
            today = new HashSet<Integer>();
        } else {
            today = deserializeSet(set);
        }
        today.remove(arg);
        map.put(dayLong, serializeSet(today));
        setMapString(map);
    }

    public boolean retrieve(int arg, Date day) {
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
        if (set == null) return false;
        HashSet<Integer> today = deserializeSet(set);
        if (today.contains(arg)) return true;
        return false;
    }

    public HashMap<Long, String> getMap() {

        if (!mPrefs.contains("CompletionMap")) return new HashMap<Long, String>();
        String json = mPrefs.getString("CompletionMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, String>>() {
            }.getType();
            HashMap<Long, String> object = gson.fromJson(json, stringMap);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<Long, String>();
        }
    }

    public HashSet<String> getStringsForDay(int i) {
        HashSet<Integer> set = deserializeSet(getMap().get(intToLong(i)));
        HashSet<String> setString = new HashSet<>();
        for (Integer u : set) {
            setString.add(getTreatmentName(u));
        }
        return setString;
    }

    public void setMapString(HashMap<Long, String> map) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        prefsEditor.putString("CompletionMap", json);
        prefsEditor.commit();
    }

    public String serializeSet(HashSet<Integer> set) {
        Gson gson = new Gson();
        String result = gson.toJson(set);
        return result;
    }

    public HashSet<Integer> deserializeSet(String string) {
        Gson gson = new Gson();
        Type stringSet = new TypeToken<HashSet<Integer>>() {
        }.getType();
        HashSet<Integer> result = gson.fromJson(string, stringSet);
        return result;
    }

    public void place(int arg, Date day) {
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, Integer> map = getMapInteger();
        map.put(dayLong, arg);
        setMap(map);

    }

    public int retrieve(Date day) {
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(day);
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        day = calEnd.getTime();
        long dayLong = day.getTime();

        HashMap<Long, Integer> map = getMapInteger();
        Integer arg = map.get(dayLong);
        if (arg == null) return -1;
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

    public HashMap<Long, Integer> getMapInteger() {
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) return new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>() {
            }.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<Long, Integer>();
        }
    }

    public void setMap(HashMap<Long, Integer> map) {
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        prefsEditor.putString("MoodMap", json);
        prefsEditor.commit();
    }

    public HashMap<Date, Integer> getMapDate() {
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>() {
            }.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        } catch (Exception e) {
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }

        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            fresh.put(new Date((long) pair.getKey()), (Integer) pair.getValue());
        }
        return fresh;
    }

    public HashMap<Date, Integer> getMapLong() {
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>() {
            }.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        } catch (Exception e) {
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }

        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            fresh.put(new Date((long) pair.getKey()), (Integer) pair.getValue());
        }
        return fresh;
    }

    public HashMap<Integer, Integer> getMapInt() {
        HashMap<Long, Integer> original;
        SharedPreferences mPrefs = context.getSharedPreferences("mindfull", Context.MODE_PRIVATE);
        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>() {
            }.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        } catch (Exception e) {
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }

        HashMap<Integer, Integer> fresh = new HashMap<Integer, Integer>();
        try {
            Iterator it = original.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
                fresh.put(difference(new Date((long) pair.getKey()), new Date(getStartLong())), (Integer) pair.getValue());
            }
        }catch (Exception e){

        }

        return fresh;
    }

    public long getStartLong() {
        HashMap<Long, Integer> original;

        if (!mPrefs.contains("MoodMap")) original = new HashMap<Long, Integer>();
        String json = mPrefs.getString("MoodMap", "");
        Gson gson = new Gson();
        try {
            Log.d("Hudson", json);
            Type stringMap = new TypeToken<HashMap<Long, Integer>>() {
            }.getType();
            HashMap<Long, Integer> object = gson.fromJson(json, stringMap);
            original = object;
        } catch (Exception e) {
            e.printStackTrace();
            original = new HashMap<Long, Integer>();
        }
        long date = Long.MAX_VALUE;
        HashMap<Date, Integer> fresh = new HashMap<Date, Integer>();
        Iterator it = original.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            if (date > (long) pair.getKey()) date = (long) pair.getKey();
        }
        return date;
    }

    public String getStartString(HashMap<Date, Integer> map) {
        long day = getStartLong();
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(day)); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

    public String longToString(long arg) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(arg)); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

    public String dateToString(Date arg) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(arg); // Give your own date
        String date = cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR);
        return date;
    }

    public long intToLong(int input) {

        return getStartLong() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS) * input;
    }

    public Date intToDate(int input) {
        return new Date(getStartLong() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS) * input);
    }

    public String dateToString1(Date date) {
        // Create an instance of SimpleDateFormat used for formatting
// the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = date;
        String reportDate = df.format(today);
        return reportDate;
    }

    public static int difference(Date day1, Date day2) {

        return (int) TimeUnit.DAYS.convert(Math.abs(day2.getTime() - day1.getTime()), TimeUnit.MILLISECONDS) + 1;
    }

    public void launchDialog(Context mContext, int integer) {
        new InfoDialog(mContext, integer).show();
    }

    public void launchSearch(final Context mContext, final int integer) {

        final SharedPreferences prefs = mContext.getSharedPreferences("Hudson", Context.MODE_PRIVATE);

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.search_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        userInput.setText(prefs.getString("search", ""));


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("search", userInput.getText().toString());
                                editor.commit();
                                String location = userInput.getText().toString();
                                if (location.length() == 0) {
                                    location = "San Francisco";
                                }
                                String query = helper.getTreatmentName(integer);
                                Uri uri = Uri.parse("http://www.yelp.com/search?find_desc=" + query + "&find_loc=" + location);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                mContext.startActivity(intent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void launchShopping(final Context mContext, final int integer) {
        final String string = getTreatmentShopping(integer);
        if (string.equals("")) {
            launchSearch(mContext, integer);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setItems(new String[]{"Search Yelp", "Buy Online"}, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            dialog.dismiss();
                            launchSearch(mContext, integer);
                            break;
                        case 1:
                            dialog.dismiss();
                            Uri uri = Uri.parse(string);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            mContext.startActivity(intent);
                            break;
                    }
                }
            });
            builder.create().show();

        }

        final SharedPreferences prefs = mContext.getSharedPreferences("Hudson", Context.MODE_PRIVATE);

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.search_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        userInput.setText(prefs.getString("search", ""));


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("search", userInput.getText().toString());
                                editor.commit();
                                String location = userInput.getText().toString();
                                if (location.length() == 0) {
                                    location = "San Francisco";
                                }
                                String query = helper.getTreatmentName(integer);
                                Uri uri = Uri.parse("http://www.yelp.com/search?find_desc=" + query + "&find_loc=" + location);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                mContext.startActivity(intent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    //    public void editName(final Context context) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//        alertDialog.setTitle("Change Your username");
//
//        final EditText input = new EditText(context);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        input.setLayoutParams(lp);
//        alertDialog.setView(input);
//
//        alertDialog.setPositiveButton("Finish",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        String password = input.getText().toString();
//                        SharedPreferences prefs = context.getSharedPreferences("Hudson", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.putString("user", password);
//                        editor.commit();
//                    }
//                });
//
//        alertDialog.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//        alertDialog.show();
//    }
//
//    public void setName(final Context context) {
//        final SharedPreferences prefs = context.getSharedPreferences("Hudson", Context.MODE_PRIVATE);
//        if(prefs.getString("user", "") != "")return;
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//        alertDialog.setTitle("Set Your username");
//        alertDialog.setMessage("Use your designated name");
//        final EditText input = new EditText(context);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        input.setLayoutParams(lp);
//        alertDialog.setView(input);
//
//        alertDialog.setPositiveButton("Finish",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        String password = input.getText().toString();
//
//                        SharedPreferences.Editor editor = prefs.edit();
//                        editor.putString("user", password);
//                        editor.commit();
//                    }
//                });
//
//        alertDialog.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog dialog = alertDialog.create();
//        dialog.show();
//    }

    public int getCategoryColor() {
        int cat = new Random().nextInt(2);
        if (cat == 0) {
            return Color.BLUE;
        }
        if (cat == 1) {
            return Color.GREEN;
        }
        if (cat == 2) {
            return Color.MAGENTA;
        }
        return Color.BLUE;
    }

    public List<CustomTile> loadCustomTiles() {
        String result = "";
        result = mPrefs.getString("CustomTiles", "");
        Type listMyData = Types.newParameterizedType(List.class, CustomTile.class);
        JsonAdapter<List<CustomTile>> adapter = moshi.adapter(listMyData);
        try {
            List<CustomTile> event = adapter.fromJson(result);
            return event;
        } catch (IOException e) {
            Log.d("Hudson", "No custom tiles");
            return new ArrayList<CustomTile>();
        }
    }

    public List<CustomTile> saveCustomTiles(List<CustomTile> customTiles) {
        Type listMyData = Types.newParameterizedType(List.class, CustomTile.class);
        JsonAdapter<List<CustomTile>> adapter = moshi.adapter(listMyData);
        String string = adapter.toJson(customTiles);
        mPrefs.edit().putString("CustomTiles", string).commit();
        return loadCustomTiles();
    }

    public List<CustomTile> addCustomTile(CustomTile customTile) {
        List<CustomTile> tiles = loadCustomTiles();
        tiles.add(customTile);
        saveCustomTiles(tiles);
        return loadCustomTiles();
    }

    public List<CustomTile> removeCustomTile(int input) {
        List<CustomTile> tiles = loadCustomTiles();
        tiles.remove(input);
        saveCustomTiles(tiles);
        return loadCustomTiles();
    }

    public List<CustomTile> editCustomTile(int input, CustomTile fresh) {
        List<CustomTile> tiles = loadCustomTiles();
        tiles.set(input, fresh);
        saveCustomTiles(tiles);
        return loadCustomTiles();
    }

    public HashSet<Integer> loadCustomIntArray() {
        HashSet<Integer> result = new HashSet<>();
        int index = 0;
        for (CustomTile e : loadCustomTiles()) {
            result.add(index + 1000);
            index++;
        }

        return result;
    }
}
