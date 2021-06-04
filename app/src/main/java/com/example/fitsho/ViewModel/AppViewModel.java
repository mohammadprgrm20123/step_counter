package com.example.fitsho.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitsho.AppExecutors;
import com.example.fitsho.DateBase.AppDataBase;
import com.example.fitsho.entity.StepCounter;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

    AppDataBase dataBase;
    Application application;

    public AppViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        Log.i("wew","AppViewModel");



    }



    public void AddRecordStepCounter(final StepCounter stepCounter){
        if(application==null){
            Log.i("wew","application null");
        }
        else {
            Log.i("wew","application not null");

        }
        if(AppDataBase.gtInstance(application)==null){
            Log.i("wew","null");
        }
        else {
            AppDataBase.gtInstance(application).insertRecordStepCounter(stepCounter);
            Log.i("wew","not null");

        }
    }


    public LiveData<List<StepCounter>> getRecordsWithDate(){
        return AppDataBase.gtInstance(application).getRecordWithDate();
    }
}
