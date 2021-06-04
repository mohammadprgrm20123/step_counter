package com.example.fitsho.Fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.fitsho.R;
import com.example.fitsho.ViewModel.AppViewModel;
import com.example.fitsho.entity.StepCounter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class Refport_Fragments extends Fragment {
    View view;
    private BubbleChart chart;
    private BarChart chart2;
    String[] a;

    TextView report_todat;
    TextView report_weekly;
    String[] b = {"شنبه", "یکشنبه", "دوشنبه", "سه شنبه", "چهار شنبه", "پنج شنبه", "جمعه"};
    AppViewModel viewModel;
    List<StepCounter> stepCountersToday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_refport__fragments, container, false);
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);
        init(view);
        getDataFromDb();

        return view;

    }

    private void setDataInBarchart(List<StepCounter> stepCounter) {

        ArrayList<BarEntry> valueCalories = new ArrayList<>();
        ArrayList<BarEntry> valueSteps = new ArrayList<>();
        ArrayList<BarEntry> valuesDistance = new ArrayList<>();

            PersianCalendar persianCalendar = new PersianCalendar(System.currentTimeMillis());

            persianCalendar.add(Calendar.DAY_OF_MONTH, -3);


            for (int i = 0; i < 7; i++) {
                List<StepCounter> listStepDay=new ArrayList<>();
                Log.i("frv", persianCalendar.getPersianShortDate());
                persianCalendar.add(Calendar.DAY_OF_MONTH, 1);

                for (int j=0;j<stepCounter.size();j++){

                    Log.i("coutn_pppp",j+"");
                    if(stepCounter.get(j).getDate().equals(persianCalendar.getPersianShortDate())){
                        listStepDay.add(stepCounter.get(j));
                    }
                }
                double calories=0;
                double distance=0;
                for(int f=0;f<listStepDay.size();f++){
                     calories =+ listStepDay.get(f).getCalories();
                     distance =+ listStepDay.get(f).getDistance();
                }

                Log.i("xssscsd","cal="+calories);
                Log.i("xssscsd","dis="+distance);
                Log.i("xssscsd","step="+listStepDay.size());

                valueCalories.add(new BarEntry((float) calories,i));
                valueSteps.add(new BarEntry((float) listStepDay.size(),i));
                valuesDistance.add(new BarEntry((float) distance,i));

            }



        IBarDataSet set1, set2, set3;

        set1 = new BarDataSet(valuesDistance, "میانگین مسافت");
        ((BarDataSet) set1).setBarShadowColor(Color.RED);
        set2 = new BarDataSet(valueSteps, "میانگین قدم ها");
        ((BarDataSet) set2).setColor(Color.GREEN);
        set3 = new BarDataSet(valueCalories, "میانگین کالری سوخته شده");
        ((BarDataSet) set3).setColor(Color.BLUE);


        List<IBarDataSet> p = new ArrayList<>();

        p.add(set1);
        p.add(set2);
        p.add(set3);
        //    chart2.getBarData().setGroupSpace(;
        // (float).08);
        BarData data = new BarData(b, p);
        data.setValueFormatter(new LargeValueFormatter());

        chart2.invalidate();
        chart2.setData(data);
        chart2.animateY(3000, Easing.EasingOption.EaseInOutQuad);
        chart2.setDescription("گزارشات روز های هفته");
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "font/dana_bold.ttf");
        chart2.setDescriptionTypeface(typeface);
        chart2.setDescriptionColor(Color.BLACK);
        chart2.setDescriptionTextSize(18);

    }

    private void getDataFromDb() {


            PersianCalendar persianCalendar = new PersianCalendar(System.currentTimeMillis());
            Log.i("xsx22", persianCalendar.getPersianShortDate());
            viewModel.getRecordsWithDate().observe(getViewLifecycleOwner(), stepCounter -> {
                stepCountersToday.clear();
                Log.i("wqw","stepCounters="+stepCounter.size());

                for(int j=0;j<stepCounter.size();j++){
                    Log.i("wqw",persianCalendar.getPersianShortDate()+"    "+(stepCounter.get(j).getDate()));
                    Log.i("wqw",j+"");
                    if(persianCalendar.getPersianShortDate().equals(stepCounter.get(j).getDate())){
                        stepCountersToday.add(stepCounter.get(j));
                    }
                }
                setDataInchartboubble(stepCountersToday);
                setDataInBarchart(stepCounter);
            });





    }

    private void getDataOfTheOneWeek(List<StepCounter> stepCounter) {



    }

    private void setDataInchartboubble(List<StepCounter> stepCountersToday2) {
        ArrayList<BubbleEntry> valuesCalories = new ArrayList<>();
        ArrayList<BubbleEntry> values_steps = new ArrayList<>();
        ArrayList<BubbleEntry> values_distance = new ArrayList<>();
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        Legend l = chart.getLegend();
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);
        Random random = new Random();
        for (int i = 1; i <24; i++) {


            List<StepCounter> stepCountersHourly=new ArrayList<>();

            /*if (i >= 7) {
                values_speed.add(new BubbleEntry(i, (float) random.nextInt(20), (float) random.nextInt(30) * 3, getResources().getDrawable(R.drawable.stop_icon)));
                values_steps.add(new BubbleEntry(i, (float) random.nextInt(1000), (float) random.nextInt(1000) * 2, getResources().getDrawable(R.drawable.start_icon)));
                values_distance.add(new BubbleEntry(i, (float) random.nextInt(50), (float) random.nextInt(40) * 5, getResources().getDrawable(R.drawable.start_icon)));
            } else {
                values_speed.add(new BubbleEntry(i, (float) random.nextInt(40), (float) random.nextInt(10) * 3, getResources().getDrawable(R.drawable.stop_icon)));
                values_steps.add(new BubbleEntry(i, (float) random.nextInt(40), (float) random.nextInt(300) * 2, getResources().getDrawable(R.drawable.start_icon)));
                values_distance.add(new BubbleEntry(i, (float) random.nextInt(10), (float) random.nextInt(10) * 5, getResources().getDrawable(R.drawable.start_icon)));
            }*/
           // Log.i("trt","size="+stepCountersToday2.size());

            for (int f=0;f<stepCountersToday2.size();f++){

             //   Log.i("dfdf","hour="+stepCountersToday2.get(f).getHour()+"     "+i);
               // Log.i("dfdf","f="+f);


                if (stepCountersToday2.get(f).getHour().equals(i+"")){
                    stepCountersHourly.add(stepCountersToday2.get(f));
                }
            }
            //Log.i("trt","hour="+i+"      stepCountersHourly size="+stepCountersHourly.size());


            if(!stepCountersHourly.isEmpty()){
                valuesCalories.add(new BubbleEntry(i,(float)stepCountersHourly.get(stepCountersHourly.size()-1).getCalories(), (float)stepCountersHourly.get(stepCountersHourly.size()-1).getCalories(), getResources().getDrawable(R.drawable.stop_icon)));
                values_steps.add(new BubbleEntry(i,(float)stepCountersHourly.size(),(float) stepCountersHourly.size(), getResources().getDrawable(R.drawable.start_icon)));
                values_distance.add(new BubbleEntry(i, (float)stepCountersHourly.get(stepCountersHourly.size()-1).getDistance(), (float)stepCountersHourly.get(stepCountersHourly.size()-1).getCalories(), getResources().getDrawable(R.drawable.start_icon)));
            }
            else {
                valuesCalories.add(new BubbleEntry(i,(float)0, (float)0, getResources().getDrawable(R.drawable.stop_icon)));
                values_steps.add(new BubbleEntry(i,(float)0,(float) 0, getResources().getDrawable(R.drawable.start_icon)));
                values_distance.add(new BubbleEntry(i, (float)0, (float)0, getResources().getDrawable(R.drawable.start_icon)));
            }


           // Log.i("rere","hour="+i+"      stepCountersHourly="+stepCountersHourly.size());

            }


        BubbleDataSet set1 = new BubbleDataSet(valuesCalories, "کالری");
        set1.setColor(Color.RED, 150);
        set1.setHighlightCircleWidth(50);
        set1.setDrawValues(true);

        BubbleDataSet set2 = new BubbleDataSet(values_steps, "قدم ها");
        set2.setColor(Color.GREEN, 120);
        set2.setHighlightCircleWidth(300);
        set2.setDrawValues(true);

        BubbleDataSet set3 = new BubbleDataSet(values_distance, "مسافت");
        set3.setColor(Color.YELLOW, 130);
        set3.setDrawValues(true);

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BubbleData bubbleData = new BubbleData(a, dataSets);
        bubbleData.setDrawValues(false);
        bubbleData.setValueTextSize(8f);
        bubbleData.setValueTextColor(Color.WHITE);
        bubbleData.setHighlightCircleWidth(1.5f);
        chart.setData(bubbleData);
        chart.animate();
        chart.setDescription("گزارش روزانه بر اساس ساعات روز میباشد");
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "font/dana_bold.ttf");

        chart.setDescriptionTypeface(typeface);
        chart.setDescriptionColor(Color.WHITE);


        chart.animateY(3000, Easing.EasingOption.EaseOutBack);
        chart.invalidate();

    }

    private void init(View view) {
        chart = view.findViewById(R.id.chart1);
        chart2 = view.findViewById(R.id.chart2);
        report_todat = view.findViewById(R.id.textView);
        report_weekly = view.findViewById(R.id.textView4);
        //   animation_txt();

        stepCountersToday=new ArrayList<>();

        chart.setDrawGridBackground(false);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setMaxVisibleValueCount(200);
        chart.getAxisRight().setEnabled(false);
        chart.setDescriptionTextSize(19);
        YAxis yl = chart.getAxisLeft();
        yl.setSpaceTop(30f);
        yl.setSpaceBottom(30f);
        yl.setDrawZeroLine(false);


        a = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24"};


    }

    private void animation_txt() {
        YoYo.with(Techniques.RotateInUpRight)
                .duration(2500)
                .repeat(0)
                .playOn(report_todat);
        YoYo.with(Techniques.RotateInUpLeft)
                .duration(2500)
                .repeat(0)
                .playOn(report_weekly);


    }


}

