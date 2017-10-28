package com.example.kmucs.echoet_app;

/**
 * Created by occid on 2017-10-28.
 */

public class SelectedFoodListItem {
    String foodName;
    int kcal;
    int co2;
//    int count;

    public SelectedFoodListItem(String foodName, int kcal, int co2) { //, int count) {
        this.foodName = foodName;
        this.kcal = kcal;
        this.co2 = co2;
//        this.count = count;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getKcal() {
        return kcal;
    }

    public int getCo2() {
        return co2;
    }

//    public int getCount() {
//        return count;
//    }
}
