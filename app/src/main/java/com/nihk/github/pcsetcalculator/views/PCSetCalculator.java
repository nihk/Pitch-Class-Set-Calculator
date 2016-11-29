package com.nihk.github.pcsetcalculator.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nihk.github.pcsetcalculator.R;

public class PCSetCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcsetcalculator);

        // Immediately begin the view pager fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_pcsetcalculator, new ViewPagerFragment())
                .commit();
    }
}
