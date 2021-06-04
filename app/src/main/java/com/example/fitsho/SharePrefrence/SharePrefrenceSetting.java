package com.example.fitsho.SharePrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fitsho.R;

public class SharePrefrenceSetting {

    public static SharedPreferences getSharedPreferencesSetting(Context context) {
        return context.getSharedPreferences("MyApp", Context.MODE_PRIVATE);
    }





    public static void setLastAmountCalories(Context context,double calories){
        getSharedPreferencesSetting(context).edit().putLong(context.getString(R.string.calories_person), (long) calories).commit();
    }

    public static long getLastAmountCalories(Context context){
        return getSharedPreferencesSetting(context).getLong(context.getString(R.string.calories_person),0);
    }

    public static void setBMI(Context context,double calories){
        getSharedPreferencesSetting(context).edit().putLong("bmi", (long) calories).commit();
    }

    public static long getBMI(Context context){
        return getSharedPreferencesSetting(context).getLong("bmi",0);
    }

    public static void setLastAmountDistance(Context context,double calories){
        getSharedPreferencesSetting(context).edit().putLong(context.getString(R.string.distance_person), (long) calories).commit();
    }

    public static long getLastAmountDistance(Context context){
        return getSharedPreferencesSetting(context).getLong(context.getString(R.string.distance_person),0);
    }

    public static void setAllSteps(Context context,double step){
        getSharedPreferencesSetting(context).edit().putLong(context.getString(R.string.stepcout), (long) step).commit();
    }

    public static long getAllSteps(Context context){
        return getSharedPreferencesSetting(context).getLong(context.getString(R.string.stepcout),0);
    }



}
