package com.example.kmucs.echoet_app.History;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kmucs.echoet_app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import AndroidHttpRequest.Environment;
import AndroidHttpRequest.HttpRequestor;
import AndroidHttpRequest.HttpRequestorBuilder;
import AndroidHttpRequest.HttpResponseListener;

public class HistoryActivity extends AppCompatActivity {
    int year, month, day;
    TextView textSave;

    ListView listview ;
    HistoryListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        year = getIntent().getIntExtra("year", 2017);
        month = getIntent().getIntExtra("month", 10) + 1;
        day = getIntent().getIntExtra("day", 28);

        TextView textDateView = (TextView) findViewById(R.id.textDateView);
        textSave = (TextView) findViewById(R.id.textSaveView);

        // Adapter 생성
        adapter = new HistoryListAdapter() ;

        textDateView.setText(month + "-" + day + "-" + year);
        getTotalData();
    }

    public void addFood(String foodName, int foodKcal, int foodCout, int foodCount){
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.hambuger),
                foodName, "Kcal : " + foodKcal + "kcal\nCEQ : " + foodCout + "g\nCount : " + foodCount) ;
    }

    private void getTotalData() {
        try {
            Log.e("Error", year + "," + month +"," + day);
            JSONObject json = buildJson(year, month, day);
            HttpRequestorBuilder builder = new HttpRequestorBuilder(Environment.serverUrl + "/daily-eat/all");
            HttpRequestor requestor = builder.build();
            requestor.post(json, new HttpResponseListener() {
                @Override
                protected void httpResponse(String data) {
                    try {
                        int dayTotalKcal = 0;
                        int dayTotalTree = 0;
                        JSONObject json = new JSONObject(data);
                        JSONArray jsonArray = json.getJSONArray("daily_eats");
                        Log.e("Error", jsonArray.length() + "");
                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String foodName = jsonObject.getString("food_name");
                            int kcal = jsonObject.getInt("kcal");
                            int co2 = jsonObject.getInt("co2");
                            int count = jsonObject.getInt("count");

                            dayTotalKcal += (kcal * count);
                            dayTotalTree += (co2 * count);

                            addFood(foodName, kcal, co2, count);
                        }

                        dayTotalKcal = 2400 - dayTotalKcal;
                        dayTotalTree = 8000 - dayTotalTree;

                        textSave.setText("loose kcal : " + dayTotalKcal + "kcal\nsave CEQ : " + dayTotalTree + "g");
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage(), e.fillInStackTrace());
                    }
                }

                @Override
                protected void httpExcepted(Exception e) {

                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage(), e.fillInStackTrace());
        }
    }
    private JSONObject buildJson(int year, int month, int date) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", Environment.userId);
        jsonObject.put("year", ""+year);
        jsonObject.put("month", ""+month);
        jsonObject.put("date", ""+date);

        return jsonObject;
    }
}
