package com.example.kmucs.echoet_app;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.kmucs.echoet_app.calendar.CalendarFragment;
import com.example.kmucs.echoet_app.selected_food_list.AddDailyEatActivity;
import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

public class MainActivity extends AppCompatActivity implements GraphFragment.OnFragmentInteractionListener
, EarthFragment.OnFragmentInteractionListener, CalendarFragment.OnFragmentInteractionListener{

    private boolean isEarthScreen = true;
    private Swipe swipe;
    private int current_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.e("Error", "1");
        swipe = new Swipe();
        swipe.setListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(MotionEvent event) {
            }

            @Override
            public void onSwipedLeft(MotionEvent event) {
                if(current_fragment == 1) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.earth_screen_layout, new GraphFragment());
                    transaction.commit();
                    current_fragment = 2;
                } else if(current_fragment == 0) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.earth_screen_layout, new EarthFragment());
                    transaction.commit();
                    current_fragment = 1;
                }

            }

            @Override
            public void onSwipingRight(MotionEvent event) {
                Log.e("Error", "3");

            }

            @Override
            public void onSwipedRight(MotionEvent event) {
                if(current_fragment == 1) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.earth_screen_layout, new CalendarFragment());
                    transaction.commit();
                    current_fragment = 0;
                } else if(current_fragment == 2) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.earth_screen_layout, new EarthFragment());
                    transaction.commit();
                    current_fragment = 1;
                }
            }

            @Override
            public void onSwipingUp(MotionEvent event) {
                Log.e("Error", "5");

            }

            @Override
            public void onSwipedUp(MotionEvent event) {
                Log.e("Error", "6");

            }

            @Override
            public void onSwipingDown(MotionEvent event) {
                Log.e("Error", "7");

            }

            @Override
            public void onSwipedDown(MotionEvent event) {
                Log.e("Error", "8");

            }

        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.earth_screen_layout, new EarthFragment());
        fragmentTransaction.commit();
        current_fragment = 1;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    public void clickAddButton(View view) {
        Intent intent = new Intent(this, AddDailyEatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
