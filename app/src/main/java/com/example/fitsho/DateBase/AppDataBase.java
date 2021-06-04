package com.example.fitsho.DateBase;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitsho.AppExecutors;
import com.example.fitsho.DateBase.dao.StepCounterDao;
import com.example.fitsho.ViewModel.AppViewModel;
import com.example.fitsho.entity.StepCounter;

import java.util.List;

@Database(entities = StepCounter.class, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    static AppDataBase appDataBase=null;
    public abstract StepCounterDao stepCounterDao();


    public static AppDataBase gtInstance(Context application) {
        if (appDataBase == null) {
            Log.i("data","database is NUll");
            appDataBase = Room.databaseBuilder(application,
                    AppDataBase.class,
                    "step_cnouter_room")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDataBase;
    }

    public  void insertRecordStepCounter(final StepCounter stepCounter){

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                stepCounterDao().insertRecordStepCounter(stepCounter);
            }
        });
    }


    public LiveData<List<StepCounter>> getRecordWithDate(){
        return stepCounterDao().getRecordWithDate();
    }

}
