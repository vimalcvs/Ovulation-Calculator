package com.demo.periodtracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.periodtracker.Adapters.OnBoardingFragmentsAdapter;

import com.demo.periodtracker.R;
import com.demo.periodtracker.Utils.SharedPreferenceUtils;
import com.demo.periodtracker.Utils.Utils;
import com.demo.periodtracker.databinding.ActivityInputBinding;


public class InputActivity extends AppCompatActivity {
    public int cyclesLength = 0;
    public int periodLength = 0;
    ActivityInputBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityInputBinding inflate = ActivityInputBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());



        getWindow().setFlags(512, 256);
        if (!SharedPreferenceUtils.getDate(this).isEmpty() && !SharedPreferenceUtils.getCycles(this).isEmpty() && !getIntent().getBooleanExtra("recalculate", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
            return;
        }
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setUserInputEnabled(false);
        Utils.setStatusBarColor(R.color.input_screen_bg_color, this);
        binding.viewPager.setAdapter(new OnBoardingFragmentsAdapter(getSupportFragmentManager(), getLifecycle()));
    }
}
