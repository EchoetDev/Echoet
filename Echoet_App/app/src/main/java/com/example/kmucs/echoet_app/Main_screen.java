package com.example.kmucs.echoet_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Intent go_food = new Intent(Main_screen.this, Food_list.class);
        startActivity(go_food);
        finish();

    }
}
