package com.hudson.mindfill;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hudson.mindfill.activities.StoreActivity;
import com.hudson.mindfill.adapters.GridAdapter;
import com.hudson.mindfill.lib.StaticClass;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Tiles extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Context context;
    Button submitButton;
    GridView gridView;
    GridAdapter gridAdapter;
    TextView percentage;
    TextView leftText;
    SeekBar seekBar;
    ArrayList<Integer> myList = new ArrayList<Integer>();

    private static final int PICK_CONTACT = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        String projectToken = "ccdffb08bd13a2bebb50535fbd56963e";
        //MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, projectToken);
        try {
            JSONObject props = new JSONObject();
            props.put("Tiles Started", true);
            //mixpanel.track("Tiles - onCreate called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }

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
        if( StaticClass.getIntstnace().retrieve(new Date()) != -1 ){
            seekBar.setProgress(StaticClass.getIntstnace().retrieve(new Date()));
        }
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        percentage.setText("Current Mood: " + seekBar.getProgress() + "%");
        gridView = (GridView) findViewById(R.id.gridView);
        myList = StaticClass.getIntstnace().getList();
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
                StaticClass.getIntstnace().place(seekBar.getProgress(), new Date());
                Toast.makeText(context, "Mood Submitted as " + String.valueOf(StaticClass.getIntstnace().retrieve(new Date())) + "%", Toast.LENGTH_SHORT).show();
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

//        if (id == R.id.nav_settings) {
//            // Handle the camera action
//        } else
        if (id == R.id.nav_edit) {
            startActivity(new Intent(getApplicationContext(), DragDrop.class));
        } else if (id == R.id.nav_quiz) {
            startActivity(new Intent(getApplicationContext(), Quiz.class));
        } else if (id == R.id.nav_notify) {

        } else if (id == R.id.nav_chart) {
            startActivity(new Intent(getApplicationContext(), Chart.class));
        } else if (id == R.id.nav_custom) {
            startActivity(new Intent(getApplicationContext(), CustomActivity.class));
        } else if (id == R.id.nav_suicide) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Tiles.this);
            builder.setTitle("Mental Emergency")
                    .setItems(new String[]{"Call Suicide Hotline", "Call a friend"}, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case 0:
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + "18002738255"));
                                    startActivity(intent);
                                    break;
                                case 1:
                                    Intent contactsIntent= new Intent(Intent.ACTION_PICK);
                                    contactsIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                                    startActivityForResult(contactsIntent, PICK_CONTACT);
                                    break;
                            }
                        }
                    });
            builder.create().show();

        } else if (id == R.id.nav_store) {
            startActivity(new Intent(getApplicationContext(), StoreActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CONTACT) {
                if (data != null) {
                    Uri uri = data.getData();

                    if (uri != null) {
                        Cursor c = null;
                        try {
                            c = getContentResolver().query(uri, new String[]{
                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                                            ContactsContract.CommonDataKinds.Phone.TYPE },
                                    null, null, null);

                            if (c != null && c.moveToFirst()) {
                                String number = c.getString(0);

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + number));
                                startActivity(intent);
                            }
                        } finally {
                            if (c != null) {
                                c.close();
                            }
                        }
                    }
                }
            }
        }
    }
}
