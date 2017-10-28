package com.example.kmucs.echoet_app;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(MainActivity.this, Food_list.class);
        startActivity(intent);



        imageEarth = (ImageView) findViewById(R.id.imageEarth);
        imageFat = (ImageView) findViewById(R.id.imageFat);
        imageFat.setScaleX(1.0f - 0.1f * (5 - fatSize));
        imageFat.setScaleY(1.0f - 0.1f * (5 - fatSize));

        EarthThread earthThreadhread = new EarthThread();
        earthThreadhread.setDaemon(true);
        earthThreadhread.start();

        FatThread fatThread = new FatThread();
        fatThread.setDaemon(true);
        fatThread.start();

        setEarth(earthGreen);
    }

}
