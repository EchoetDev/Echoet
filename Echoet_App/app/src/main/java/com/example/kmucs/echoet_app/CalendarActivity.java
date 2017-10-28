package com.example.kmucs.echoet_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.addDecorators(new OnedayDecorator(), new SundayDecorator());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent intent = new Intent(context, HistoryActivity.class);
                intent.putExtra("userName", "test");
                intent.putExtra("year", date.getYear());
                intent.putExtra("month", date.getMonth());
                intent.putExtra("day", date.getDay());
                startActivity(intent);

            }
        });
    }
}
