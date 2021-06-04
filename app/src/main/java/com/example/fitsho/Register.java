package com.example.fitsho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fitsho.SharePrefrence.SharePrefrenceSetting;
import com.example.fitsho.databinding.ActivitySplashBinding;

public class Register extends AppCompatActivity {


    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.tvHeight.getText().toString().isEmpty() || binding.tvWieght.getText().toString().isEmpty() ){
                    Toast.makeText(Register.this, "لطفا قد و وزن را درست وارد کنید", Toast.LENGTH_SHORT).show();
                }else
                {
                    double height = Double.parseDouble(binding.tvHeight.getText().toString());
                    double weight =  Double.parseDouble(binding.tvWieght.getText().toString());
                    SharePrefrenceSetting.setBMI(getBaseContext(),getBMI(height, weight));
                    Toast.makeText(Register.this, getBMI(height, weight)+"", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,MainActivity1.class);
                    startActivity(intent);
                }


            }
        });


    }

    private long getBMI(double height, double weight) {

        long s=100L;
        double h= (height / s);
        Log.i("sew",h+"   "+ h*h +"   ");
        return (long) (weight/(h*h));
    }
}