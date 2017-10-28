package com.example.kmucs.echoet_app;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    float rotateEarth = 0.0f;
    int fatNum = 0;
    int fatSize = 5;
    int earthGreen = 3;
    ImageView imageEarth;
    ImageView imageFat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* test by occidere */
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

    public void setEarth(int green){
        switch (green){
            case 1:
                imageEarth.setImageResource(R.drawable.earth001);
                break;
            case 2:
                imageEarth.setImageResource(R.drawable.earth002);
                break;
            case 3:
                imageEarth.setImageResource(R.drawable.earth003);
                break;
            case 4:
                imageEarth.setImageResource(R.drawable.earth004);
                break;
            case 5:
                imageEarth.setImageResource(R.drawable.earth005);
                break;
        }
    }

    class EarthThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
                earthHandler.sendEmptyMessage(0);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    }
    Handler earthHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){   // Message id 가 0 이면
                rotateEarth = (rotateEarth + 1.0f) % 360.0f;
                imageEarth.setRotation(rotateEarth);
            }
        }
    };

    class FatThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
                fatHandler.sendEmptyMessage(0);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    }
    Handler fatHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){   // Message id 가 0 이면
                fatNum = (fatNum + 1) % 6;
                switch (fatNum){
                    case 0:
                        imageFat.setImageResource(R.drawable.fat001);
                        break;
                    case 1:
                        imageFat.setImageResource(R.drawable.fat002);
                        break;
                    case 2:
                        imageFat.setImageResource(R.drawable.fat003);
                        break;
                    case 3:
                        imageFat.setImageResource(R.drawable.fat004);
                        break;
                    case 4:
                        imageFat.setImageResource(R.drawable.fat005);
                        break;
                    case 5:
                        imageFat.setImageResource(R.drawable.fat006);
                        break;
                }
            }
        }
    };
}
