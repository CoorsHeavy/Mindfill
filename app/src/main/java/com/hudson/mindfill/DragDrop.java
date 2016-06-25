package com.hudson.mindfill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.hudson.mindfill.adapters.ListAdapter;
import com.hudson.mindfill.adapters.MyListAdapter;
import com.hudson.mindfill.adapters.TheListAdapter;
import com.hudson.mindfill.lib.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DragDrop extends AppCompatActivity {
    ListView lv;
    ListView mlv;
    Button button;
    Button back;
    Context context;
    CheckBox checkBox;
    ArrayList prgmName;
    ArrayList<Integer> itemList = new ArrayList<Integer>();
    ListAdapter itemAdapter;
    TheListAdapter theItemAdapter;
    MyListAdapter myitemAdapter;
    ArrayList<Integer> myList = new ArrayList<Integer>();
    ArrayList<Integer> stockList = new ArrayList<Integer>();
    ArrayList<Integer> theList = new ArrayList<Integer>();
    Activity activity;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_drag_drop);

        activity = this;
        {
            SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
            if(sp.getBoolean("q1", true)){
                theList.addAll(new LinkedList<Integer> (Arrays.asList(11, 12, 22, 0, 7, 8, 9, 10, 12, 16, 17, 19)));
            }
            if(sp.getBoolean("q2", true)){
                theList.addAll(new LinkedList<Integer> (Arrays.asList(11, 13, 22)));
            }
            if(sp.getBoolean("q3", true)){
                theList.add(1);
            }
            if(sp.getBoolean("q4", true)){
                theList.add(6);
            }
            if(sp.getBoolean("q5", true)){
                theList.add(4);
            }
        }
        Set<Integer> hs = new HashSet<>();
        hs.addAll(theList);
        theList.clear();
        theList.addAll(hs);
        myList = helper.getList(DragDrop.this);
        stockList = helper.getArrayIntList(context);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        button = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.back);
        lv=(ListView) findViewById(R.id.stocklistview);
        mlv=(ListView) findViewById(R.id.mylistview);
        itemAdapter = new ListAdapter(this, stockList);
        myitemAdapter = new MyListAdapter(this, myList);
        theItemAdapter = new TheListAdapter(this, theList);
        mlv.setAdapter(myitemAdapter);
        lv.setAdapter(itemAdapter);
        refreshButton();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Schedule.class);
                Bundle b = new Bundle();
                b.putIntegerArrayList("key", myList); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Hudson Hughes", String.valueOf(position));
                if (!myList.contains(position))
                    myList.add(position);
                myitemAdapter.notifyDataSetChanged();
                Log.d("Hudson", "Clicked");
                refreshButton();
            }
        });

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myList.remove(position);
                myitemAdapter.notifyDataSetChanged();
                refreshButton();
            }
        });
        mlv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int index = myList.get(position);
                helper.launchDialog(context, index);
                return true;
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                helper.launchDialog(context, position);
                return true;
            }
        });
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    lv.setAdapter(itemAdapter);
                else
                    lv.setAdapter(theItemAdapter);
            }
        });
        if(checkBox.isChecked())
            lv.setAdapter(itemAdapter);
        else
            lv.setAdapter(theItemAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Quiz.class);
                startActivity(intent);
            }
        });
    }
    public void refreshButton(){
        if(myList.isEmpty())button.setEnabled(false);
        else button.setEnabled(true);
    }
}
