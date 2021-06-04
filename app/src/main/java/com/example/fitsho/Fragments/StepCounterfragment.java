package com.example.fitsho.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitsho.G;
import com.example.fitsho.R;
import com.example.fitsho.SharePrefrence.SharePrefrenceSetting;
import com.example.fitsho.ViewModel.AppViewModel;
import com.example.fitsho.entity.StepCounter;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import me.itangqi.waveloadingview.WaveLoadingView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.SENSOR_SERVICE;


public class StepCounterfragment extends Fragment implements SensorEventListener {

    WaveLoadingView waveLoadingView;
    Button stratBtn;
    TextView tvTime;
    TextView tvCalorie;
    TextView tvDistance;
    TextView tvBmi;

    long second = 0;
    int seconLast;
    int flag = 1;
    int flagSensor = 0;
    int timeFlag = 1;
    int flagUpdate60 = 1;
    int countStepLast;
    long countStep = 0;
    Timer timer=null;
    PersianCalendar persianCalendar;
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    TextView tvAllSteps;
    AppViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         sensorManager = (SensorManager) this.getActivity().getSystemService(SENSOR_SERVICE);
         stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
         persianCalendar=new PersianCalendar(System.currentTimeMillis());

    }

    private void setLastData() {

        tvCalorie.setText(SharePrefrenceSetting.getLastAmountCalories(requireContext())+"");
        tvDistance.setText(SharePrefrenceSetting.getLastAmountDistance(requireContext())+"");
        String s=""+SharePrefrenceSetting.getAllSteps(requireContext());
        tvAllSteps.setText(getString(R.string.step2)+"   "+s);
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_today__fgagment, container, false);
        viewModel=new ViewModelProvider(requireActivity()).get(AppViewModel.class);
        Toast.makeText(requireContext(), "onCreateView", Toast.LENGTH_SHORT).show();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getPermission();
        setLastData();
        setListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTimer();
    }

    private void setListener() {
        stratBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag) {
                    case 0: {
                        flagSensor = 0;
                        timeFlag = 0;
                        stopAction();
                        flag = 1;
                        flagUpdate60 = 1;
                    }
                    break;
                    case 1: {
                        flagSensor = 1;
                        timeFlag = 1;
                        playAction();
                        flag = 0;
                    }

                }


            }
        });
    }

    public void stopAction() {
        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_play);
        stratBtn.setCompoundDrawablesWithIntrinsicBounds(myDrawable, null, null, null);
        stratBtn.setText("شروع");
    }

    public void playAction() {
        Drawable myDrawable = getResources().getDrawable(R.drawable.stop_icon1);
        stratBtn.setCompoundDrawablesWithIntrinsicBounds(myDrawable, null, null, null);
        stratBtn.setText("توقف");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(timeFlag==1){
                            setTimer();
                        }
                        else {
                            timer.cancel();
                            timeFlag=0;
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public void updateItemsUI(float steps) {

        String calories=calcuteCalorie(steps)+"";
        String distance=calcuteDistance(steps)+"";

        if (calories.length() > 5) {
            tvCalorie.setText(""+calories.substring(0, 2));
        } else {
            tvCalorie.setText(calories);
        }

        if (distance.length() > 5) {
            tvDistance.setText(distance.substring(0, 5));
        } else {
            tvDistance.setText(distance);
        }
    }

    public double calcuteCalorie(float countStep) {
        double amount = (double) (((countStep / 2.0) * 75) / 150.0) * 2;
        if (countStep == 0) {
            return 0;
        } else
            return amount * 0.1;
    }

    private double calcuteDistance(float step) {
        double amount = (step / 0.75) * (75 / 10000F);
        Log.i("distence123323",step+"=step");

        Log.i("distence123323",amount+"");
        return amount ;
    }

    public void init(View view) {
        waveLoadingView = view.findViewById(R.id.waveLoadingView);
        stratBtn = view.findViewById(R.id.start_btn_fragment_today);
        tvCalorie = view.findViewById(R.id.calorie_txt_fragment_today);
        tvDistance = view.findViewById(R.id.distance_txt_fragment_today);
        tvTime = view.findViewById(R.id.time_txt_fragment_today);
        tvAllSteps=view.findViewById(R.id.tvAllStep);
        waveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        waveLoadingView.setCenterTitleSize(60);
        waveLoadingView.setProgressValue(0);
        waveLoadingView.setCenterTitle("0");
        waveLoadingView.setAnimDuration(2000);
        waveLoadingView.pauseAnimation();
        waveLoadingView.resumeAnimation();
        waveLoadingView.cancelAnimation();
        waveLoadingView.startAnimation();
        countStep=SharePrefrenceSetting.getAllSteps(requireContext());
        waveLoadingView.setProgressValue((int) (countStep % 100));
        waveLoadingView.setCenterTitle(countStep % 100 + "");

        tvBmi=view.findViewById(R.id.tv_bmi);


        if(SharePrefrenceSetting.getBMI(requireContext())<18){

            tvBmi.setText("شما کمبود وزن دارید");
        }else {
            if(SharePrefrenceSetting.getBMI(requireContext())>18 && SharePrefrenceSetting.getBMI(requireContext())<24){
                tvBmi.setText("شما وزن ایده آل دارید");
            }else {
                if(SharePrefrenceSetting.getBMI(requireContext())>24){
                    tvBmi.setText("شما اضافه وزن دارید");
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        if(timer!=null){
            timer.cancel();
        }
    }

    public void setTimer(){

            second++;
            tvTime.setText(second / 3600 + ":" + second / 60 + ":" + second % 60);
            if (countStepLast + 1 == countStep && (seconLast + 60) == second) {
                timer.cancel();
                timeFlag = 0;
            }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerSensorManager();
        if(timer!=null){
            timer.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        countStepLast = (int) countStep;
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR && flagSensor == 1) {
            countStep++;

            seconLast = (int) second;
            waveLoadingView.setProgressValue((int) (countStep % 100));
            waveLoadingView.setCenterTitle(countStep % 100 + "");
            setData(countStep);
        }


    }

    private void setData(long step) {
        Log.i("setData",SharePrefrenceSetting.getAllSteps(requireContext())+" ");
        long sumStep=step;
        SharePrefrenceSetting.setAllSteps(getContext(),sumStep);
        tvAllSteps.setText(getString(R.string.step2)+"  "+SharePrefrenceSetting.getAllSteps(requireContext()));

        SharePrefrenceSetting.setLastAmountCalories(getContext(),calcuteCalorie(step));
        SharePrefrenceSetting.setLastAmountDistance(getContext(),calcuteDistance(step));
        updateItemsUI(step);
        saveRecordInDataBase(step);
    }

    private void saveRecordInDataBase(float step) {
        Log.i("date",persianCalendar.getPersianShortDate());
        String date=persianCalendar.getPersianShortDate();
        StepCounter stepCounter=new StepCounter(G.UserId,calcuteDistance(step),calcuteCalorie(step),date, persianCalendar.getTime().getHours()+"");
        viewModel.AddRecordStepCounter(stepCounter);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

 
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 0 && resultCode == RESULT_OK) {
            registerSensorManager();
            Toast.makeText(getActivity(), "دسترسی داده شد", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "دسترسی داده نشده است دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
            getPermission();
        }
    }

    public void registerSensorManager() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), SensorManager.SENSOR_DELAY_GAME);
    }
}
