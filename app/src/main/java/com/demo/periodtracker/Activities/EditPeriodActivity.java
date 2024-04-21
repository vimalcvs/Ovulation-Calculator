package com.demo.periodtracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.periodtracker.Databases.Entities.DateDetails;
import com.demo.periodtracker.Databases.OvulationDetailsHandler;
import com.demo.periodtracker.Databases.Params;
import com.demo.periodtracker.R;
import com.demo.periodtracker.Utils.OvulationCalculations;
import com.demo.periodtracker.Utils.SharedPreferenceUtils;
import com.demo.periodtracker.databinding.ActivityEditPeriodBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EditPeriodActivity extends AppCompatActivity {
    ActivityEditPeriodBinding binding;
    OvulationDetailsHandler handler;

    Calendar selectedDate;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityEditPeriodBinding inflate = ActivityEditPeriodBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());


        selectedDate = Calendar.getInstance();
        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> selectedDate.set(year, month, dayOfMonth));
        binding.cancelBtn.setOnClickListener(EditPeriodActivity.this::m110x7f7ce216);
        this.handler = new OvulationDetailsHandler(this);
        binding.saveBtn.setOnClickListener(EditPeriodActivity.this::m111x4f3d15b5);
    }


    public void m110x7f7ce216(View view) {
        finish();
    }

    public void m111x4f3d15b5(View view) {
        saveData();
    }

    private void saveData() {
        binding.cancelBtn.setVisibility(View.GONE);
        binding.saveBtn.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(selectedDate.getTime());
        int parseInt = Integer.parseInt(SharedPreferenceUtils.getCycles(this));
        String ovulation = OvulationCalculations.getOvulation(format, parseInt);
        String fertileWindow = OvulationCalculations.getFertileWindow(format, parseInt);
        String safeDays = OvulationCalculations.getSafeDays(format, parseInt, Integer.parseInt(SharedPreferenceUtils.getCycleLength(this)));
        OvulationDetailsHandler ovulationDetailsHandler = this.handler;
        ovulationDetailsHandler.addOvulationDetail(new DateDetails(fertileWindow, format + " --- " + OvulationCalculations.minusDays(format, 1), format, ovulation), Params.OVULATION_DETAILS_TABLE_HOME);
        this.handler.addOvulationDetail(new DateDetails(fertileWindow, safeDays, format, ovulation), Params.OVULATION_DETAILS_TABLE_CALENDAR);
        Toast.makeText(this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}
