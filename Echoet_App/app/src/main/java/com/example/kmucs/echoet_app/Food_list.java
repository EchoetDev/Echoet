package com.example.kmucs.echoet_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.LinkedList;

import AndroidHttpRequest.Environment;
import AndroidHttpRequest.HttpRequestor;
import AndroidHttpRequest.HttpRequestorBuilder;
import AndroidHttpRequest.HttpResponseListener;

public class Food_list extends AppCompatActivity {

    private Button selectFoodBtn; //Food Select Button
//    final String[] foods = {"Food 1", "Food 2", "Food 3", "Food 4", "Food 5", "Food 6"};
//    final List<SelectedFoodListItem> foods = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        /* Get List from food select method */
        //Should be received from DB
    }

    /**
     * Get Food List from User's selection(AlertDialog)
     * @param foods foods list (SelectedFoodListItem type)
     * @return selected food's List<SelectedFoodListItem> (size is 0 if nothing selected)
     * @author occidere
     */
    private List<SelectedFoodListItem> selectFood(final List<SelectedFoodListItem> foods){
        final List<SelectedFoodListItem> selectedFoods = new LinkedList<>();
        final String[] foodName = new String[foods.size()];

        int i=0;
        for(SelectedFoodListItem each : foods) {
            foodName[i++] = each.getFoodName();
            Toast.makeText(Food_list.this, foodName[i-1], Toast.LENGTH_LONG).show();
        }
        Toast.makeText(Food_list.this, "size = "+i, Toast.LENGTH_LONG).show();

        AlertDialog.Builder dialog = new AlertDialog.Builder(Food_list.this);
        dialog.setTitle("Select the foods")
                .setMultiChoiceItems(foodName,
                        new boolean[foods.size()],
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked){
                                if(isChecked)  selectedFoods.add(foods.get(which));
                                else selectedFoods.remove(foods.get(which));
                            }
                        })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if(selectedFoods.size() == 0) {
                            Toast.makeText(Food_list.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                        } else{
                            SelectedFoodListadaptor adaptor = new SelectedFoodListadaptor();
                            ListView listview = (ListView) findViewById(R.id.selected_food_list_view);
                            listview.setAdapter(adaptor);
                            for(SelectedFoodListItem each : selectedFoods){
                                adaptor.addItem(each);
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return; //Do nothing if choose "Cancel"
                    }
                }).create().show();
        return selectedFoods; //Return selected food's List<SelectedFoodListItem> (size is 0 if nothing selected)
    }

    public void clickSelectedFoofButton(View view) {
        final List<SelectedFoodListItem> foods = new LinkedList<>();
        HttpRequestorBuilder builder = new HttpRequestorBuilder(Environment.serverUrl + "/food");
        HttpRequestor requestor = builder.build();
        requestor.get(new HttpResponseListener() {
            @Override
            protected void httpResponse(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("foods");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonRow = jsonArray.getJSONObject(i);
                        String food = jsonRow.getString("name");
                        int kcal = jsonRow.getInt("kcal");
                        int co2 = jsonRow.getInt("co2");
                        foods.add(new SelectedFoodListItem(food, kcal, co2));
                    }
                    selectFood(foods);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage(), e.fillInStackTrace());
                }
            }

            @Override
            protected void httpExcepted(Exception e) {
                Log.e("Error", e.getMessage(), e.fillInStackTrace());
            }
        });



    }
}
