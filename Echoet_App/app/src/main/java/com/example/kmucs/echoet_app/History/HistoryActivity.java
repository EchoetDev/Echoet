package com.example.kmucs.echoet_app.History;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kmucs.echoet_app.R;

public class HistoryActivity extends AppCompatActivity {
    int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        year = getIntent().getIntExtra("year", 2017);
        month = getIntent().getIntExtra("month", 10) + 1;
        day = getIntent().getIntExtra("day", 28);

        TextView textDateView = (TextView) findViewById(R.id.textDateView);

        textDateView.setText(year + "년 " + month + "월 " + day + "일");

        addFood("햄버거", 100, 10, 1);
    }

    public void addFood(String foodName, int foodKcal, int foodCout, int foodCount){
        ListView listview ;
        HistoryListAdapter adapter;

        // Adapter 생성
        adapter = new HistoryListAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.earth001),
                foodName, "칼로리 : " + foodKcal + "kcal\n탄소배출량 : " + foodCout + "g\n수량 : " + foodCount + "개") ;
    }
}
