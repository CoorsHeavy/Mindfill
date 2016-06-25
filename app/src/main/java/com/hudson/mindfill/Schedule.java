package com.hudson.mindfill;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.hudson.mindfill.adapters.MyListAdapter;
import com.hudson.mindfill.lib.helper;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Schedule extends AppCompatActivity {
    ListView listview;
    Button button;
    MyListAdapter myitemAdapter;
    ArrayList<Integer> myList = new ArrayList<Integer>();
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Map out progress");
        alertDialogBuilder.setMessage("Here you can map schedule activities with your phones calender app. Tap an activity to add it to your calender. Do this as many times as you want. If you don't want to bother with the calender Mindfull can send you daily reminders to stay on top of your task.");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Bundle b = getIntent().getExtras();
        myList = b.getIntegerArrayList("key");
        myitemAdapter = new MyListAdapter(this, myList);
        listview = (ListView) findViewById(R.id.listViewSchedule);
        listview.setAdapter(myitemAdapter);
        button = (Button) findViewById(R.id.nextSchedule);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, helper.getTreatmentName(Schedule.this, myList.get(position)));
                try {
                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Schedule.this);
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("Either your calendar apps are incompatible or nonexistent");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helper.putList(myList, Schedule.this);
                Intent intent = new Intent(Schedule.this, Tiles.class);
                startActivity(intent);
            }
        });
    }
}
