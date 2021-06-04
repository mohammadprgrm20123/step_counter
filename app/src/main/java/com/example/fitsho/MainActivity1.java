package com.example.fitsho;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.fitsho.Fragments.Refport_Fragments;
import com.example.fitsho.Fragments.StepCounterfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity1 extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
//        getSupportActionBar().hide();
        init();

        /*fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        todat_frg = new Today_Fgagment();
        ft.replace(R.id.fragment, todat_frg);
        ft.commit();
*/
        setUpNavigation();
    }

    private void setUpNavigation() {
        NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView,
                navHostFragment.getNavController());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void init() {
        navigationView = findViewById(R.id.navigation_btn);
    }



}
