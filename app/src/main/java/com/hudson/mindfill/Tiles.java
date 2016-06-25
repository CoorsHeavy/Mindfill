package com.hudson.mindfill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hudson.mindfill.adapters.GridAdapter;
import com.hudson.mindfill.lib.helper;

import java.util.ArrayList;
import java.util.Date;

public class Tiles extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Button submitButton;
    GridView gridView;
    GridAdapter gridAdapter;
    TextView percentage;
    TextView leftText;
    SeekBar seekBar;
    ArrayList<Integer> myList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        leftText = (TextView)  findViewById(R.id.LeftText);
        percentage = (TextView) findViewById(R.id.RightText);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        submitButton = (Button) findViewById(R.id.submitButton);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        if( new MoodSettings(context).retrieve(new Date()) != -1 ){
            seekBar.setProgress(new MoodSettings(context).retrieve(new Date()));
        }
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        percentage.setText("Current Mood: " + seekBar.getProgress() + "%");
        gridView = (GridView) findViewById(R.id.gridView);
        myList = helper.getList(this);
        gridAdapter = new GridAdapter(this, myList);
        gridView.setAdapter(gridAdapter);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage.setText("Current Mood: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MoodSettings(context).place(seekBar.getProgress(), new Date());
                Toast.makeText(context, "Mood Submitted as " + String.valueOf(new MoodSettings(context).retrieve(new Date())) + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.tiles, menu);g
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the camera action
        } else if (id == R.id.nav_edit) {
            startActivity(new Intent(getApplicationContext(), DragDrop.class));
        } else if (id == R.id.nav_quiz) {
            startActivity(new Intent(getApplicationContext(), Quiz.class));
        } else if (id == R.id.nav_notify) {

        } else if (id == R.id.nav_chart) {
            startActivity(new Intent(getApplicationContext(), Chart.class));
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
