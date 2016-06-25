package com.hudson.mindfill;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mp = null;
    SurfaceView mSurfaceView=null;

    private Button bottom;
    private Button top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom = (Button) findViewById(R.id.bottom);

        top = (Button) findViewById(R.id.top);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Management.getList(MainActivity.this).isEmpty())
                    startActivity(new Intent(getApplicationContext(), Quiz.class));
                else startActivity(new Intent(getApplicationContext(), Tiles.class));
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dictionary.class));
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
