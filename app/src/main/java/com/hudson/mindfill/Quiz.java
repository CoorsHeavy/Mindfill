package com.hudson.mindfill;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Quiz extends AppCompatActivity {
    Context context;
    Activity activity;
    AlertDialog.Builder dialog;
    RadioGroup q1;
    RadioGroup q2;
    RadioGroup q3;
    RadioGroup q4;
    RadioGroup q5;
    RadioButton q1a1;
    RadioButton q2a1;
    RadioButton q3a1;
    RadioButton q4a1;
    RadioButton q5a1;
    Button button;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_help:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Quiz.this);
                alertDialogBuilder.setMessage("");
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarQuiz);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Personal Preference Quiz");
        context = this;
        activity = this;
        ButterKnife.bind(this);
        dialog = new AlertDialog.Builder(context)
                .setTitle("Welcome to Mindfull")
                .setMessage("Insert Welcome Text")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
        q1 = (RadioGroup) findViewById(R.id.q1);
        q2 = (RadioGroup) findViewById(R.id.q2);
        q3 = (RadioGroup) findViewById(R.id.q3);
        q4 = (RadioGroup) findViewById(R.id.q4);
        q5 = (RadioGroup) findViewById(R.id.q5);
        q1a1 = (RadioButton) findViewById(R.id.q1a1);
        q2a1 = (RadioButton) findViewById(R.id.q2a1);
        q3a1 = (RadioButton) findViewById(R.id.q3a1);
        q4a1 = (RadioButton) findViewById(R.id.q4a1);
        q5a1 = (RadioButton) findViewById(R.id.q5a1);
        q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(!(q1.getCheckedRadioButtonId() == -1 ||
                        q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1 || q4.getCheckedRadioButtonId() == -1 || q5.getCheckedRadioButtonId() == -1));
            }
        });
        q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(!(q1.getCheckedRadioButtonId() == -1 ||
                        q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1 || q4.getCheckedRadioButtonId() == -1 || q5.getCheckedRadioButtonId() == -1));
            }
        });
        q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(!(q1.getCheckedRadioButtonId() == -1 ||
                        q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1 || q4.getCheckedRadioButtonId() == -1 || q5.getCheckedRadioButtonId() == -1));
            }
        });
        q4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(!(q1.getCheckedRadioButtonId() == -1 ||
                        q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1 || q4.getCheckedRadioButtonId() == -1 || q5.getCheckedRadioButtonId() == -1));
            }
        });
        q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setEnabled(!(q1.getCheckedRadioButtonId() == -1 ||
                        q2.getCheckedRadioButtonId() == -1 || q3.getCheckedRadioButtonId() == -1 || q4.getCheckedRadioButtonId() == -1 || q5.getCheckedRadioButtonId() == -1));
            }
        });
        button = (Button) findViewById(R.id.buttonq);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
                editor.putBoolean("q1", q1a1.isChecked());
                editor.putBoolean("q2", q2a1.isChecked());
                editor.putBoolean("q3", q3a1.isChecked());
                editor.putBoolean("q4", q4a1.isChecked());
                editor.putBoolean("q5", q5a1.isChecked());
                startActivity(new Intent(activity, DragDrop.class));
            }
        });
    }

}

