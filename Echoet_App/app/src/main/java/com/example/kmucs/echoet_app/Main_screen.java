package com.example.kmucs.echoet_app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class Main_screen extends Activity
{
    private boolean isEarthScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.earth_screen_layout, new EarthFragment());
        fragmentTransaction.commit();
    }

    public void clickCalendarButton(View view) {

    }

    public void clickAddButton(View view) {
    }

    public void clickFragmentTestButton(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(isEarthScreen) {
            isEarthScreen = !isEarthScreen;
            transaction.replace(R.id.earth_screen_layout, new GraphFragment());
            transaction.commit();
        } else {
            isEarthScreen = !isEarthScreen;
            transaction.replace(R.id.earth_screen_layout, new EarthFragment());
            transaction.commit();
        }
    }
}
