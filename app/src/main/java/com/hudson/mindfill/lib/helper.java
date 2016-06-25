package com.hudson.mindfill.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.hudson.mindfill.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kaylab on 4/1/16.
 */
public class helper {
    public helper() {
    }

    static public JSONObject getTreatmentObject(Context context, int index){
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    static public String getTreatmentName(Context context, int index){
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "";
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    static public String getTreatmentDescription(Context context, int index){
        JSONObject obj = null;
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        String name = "Description Here";
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getString("Description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    static public List<String> getTreatmentEvidence(Context context, int index){
        JSONObject obj = null;
        ArrayList<String> result = new ArrayList<String>();
        int itemAmount = 0;
        JSONObject item = new JSONObject();
        JSONArray name = new JSONArray();
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            item = m_jArry.getJSONObject(index);
            name = item.getJSONArray("Sources");
            for (int i = 0 ; i < name.length(); i++) {
                result.add(name.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    static public int getAmount(Context context){
        int itemAmount = 0;
        String name = "";
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            itemAmount = m_jArry.length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemAmount;
    }

    static public ArrayList<Integer> getArrayIntList(Context context){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int itemAmount = 0;
        String name = "";
        try
        {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("array");
            itemAmount = m_jArry.length();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < itemAmount; i++)result.add(i);
        return result;
    }

    static public int getCategoryResource(int index, Context context){
        int cat = new Random().nextInt(2);
        if(cat == 0){
            return R.drawable.roundtiles1;
        }
        if(cat == 1){
            return R.drawable.roundtiles2;
        }
        if(cat == 2){
            return R.drawable.roundtiles3;
        }
        return R.drawable.roundtiles1;
    }

    static public int getCategory(){
        int cat = new Random().nextInt(2);
        if(cat == 0){
            return 0;
        }
        if(cat == 1){
            return 1;
        }
        if(cat == 2){
            return 2;
        }
        return 0;
    }

    public static String loadJSONFromAsset(Context context) {
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

    public static void putList(ArrayList<Integer> ints, Context context){
        JSONArray object = new JSONArray();
        for (int i = 0; i < ints.size(); i++) {
            object.put(ints.get(i));
        }
        SharedPreferences prefs = context.getSharedPreferences("Hudson", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("list", object.toString());
        editor.commit();
    }

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


    public static void launchDialog(Context mContext, int integer) {
        new InfoDialog(mContext, integer).show();
    }

    public static void launchSearch(final Context mContext, final int integer) {

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
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("search", userInput.getText().toString());
                                editor.commit();
                                String location = userInput.getText().toString();
                                if(location.length() == 0){
                                    location = "San Francisco";
                                }
                                String query = helper.getTreatmentName(mContext, integer);
                                Uri uri = Uri.parse("http://www.yelp.com/search?find_desc=" + query + "&find_loc=" + location);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                mContext.startActivity(intent);
                                dialog.cancel();
                        }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

//    public static void editName(final Context context) {
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
//    public static void setName(final Context context) {
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

    public static int getCategoryColor() {
        int cat = new Random().nextInt(2);
        if(cat == 0){
            return Color.BLUE;
        }
        if(cat == 1){
            return Color.GREEN;
        }
        if(cat == 2){
            return Color.MAGENTA;
        }
        return Color.BLUE;
    }
}
