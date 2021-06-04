package com.example.fitsho;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitsho.SharePrefrence.SharePrefrenceSetting;


public class Splash extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    //    getSupportActionBar().hide();
        init();

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fount.ttf");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(SharePrefrenceSetting.getBMI(getBaseContext())>0){
                    Intent intent=new Intent(Splash.this,MainActivity1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(Splash.this,Register.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        },500);
    }

    private void init() {
       // textView1=findViewById(R.id.textView2);

    }

}
