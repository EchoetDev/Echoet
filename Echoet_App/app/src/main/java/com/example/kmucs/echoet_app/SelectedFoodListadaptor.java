package com.example.kmucs.echoet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by occid on 2017-10-28.
 */

public class SelectedFoodListadaptor extends BaseAdapter {
    List<SelectedFoodListItem> list = new LinkedList<>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SelectedFoodListItem item = list.get(i);
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.selected_list_item_layout, viewGroup, false);
        TextView indexTextView = (TextView)view.findViewById(R.id.selected_list_item_index_text_view);
        indexTextView.setText((i+1)+"");
        TextView foodNameTextView = (TextView)view.findViewById(R.id.selected_list_item_food_name_text_view);
        foodNameTextView.setText(item.getFoodName());
        TextView kcalTextView = (TextView)view.findViewById(R.id.selected_list_item_kcal_text_view);
        kcalTextView.setText(item.getKcal()+"");
        TextView co2TextView = (TextView)view.findViewById(R.id.selected_list_item_co2_text_view);
        co2TextView.setText(item.getCo2()+"");
//        TextView countTextView = (TextView)view.findViewById(R.id.selected_list_item_index_count_view);
//        countTextView.setText(item.getCount()+"");

        return view;
    }
    public void addItem(SelectedFoodListItem item) {
        list.add(item);
    }
}
