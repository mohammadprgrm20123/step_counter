package com.example.fitsho.DateBase.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fitsho.entity.StepCounter;

import java.util.List;

@Dao
public interface StepCounterDao {

    @Insert
    void insertRecordStepCounter(StepCounter stepCounter);

    @Query("select * from stepcounter")
    LiveData<List<StepCounter>> getRecordWithDate();

}
