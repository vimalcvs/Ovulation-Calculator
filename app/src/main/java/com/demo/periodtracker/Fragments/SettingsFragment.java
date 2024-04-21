package com.demo.periodtracker.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.periodtracker.Activities.InputActivity;
import com.demo.periodtracker.Activities.MainActivity;
import com.demo.periodtracker.Adapters.CustomThemesSelectorAdapter;
import com.demo.periodtracker.R;
import com.demo.periodtracker.ThemesFiles.MyCustomTheme;
import com.demo.periodtracker.ThemesFiles.MyThemeHandler;
import com.demo.periodtracker.Utils.OvulationCalculations;
import com.demo.periodtracker.Utils.SharedPreferenceUtils;
import com.demo.periodtracker.databinding.FragmentSettingsBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class SettingsFragment extends Fragment {
    private final MyThemeHandler handler = new MyThemeHandler();
    CustomThemesSelectorAdapter adapter;
    FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentSettingsBinding.inflate(layoutInflater);
        setData();
        binding.recalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment.this.m137x1636f66(view);
            }
        });
        binding.shareUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment.this.m138xc78df827(view);
            }
        });
        binding.rateUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment.this.m139x8db880e8(view);
            }
        });
        binding.privacyPolicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment.this.m140x53e309a9(view);
            }
        });
        setUpThemesRecycler();
        setUpTheme();
        return binding.getRoot();
    }


    public void m137x1636f66(View view) {
        Intent intent = new Intent(getActivity(), InputActivity.class);
        intent.putExtra("recalculate", true);
        startActivity(intent);
    }


    public void m138xc78df827(View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "SUBJECT");
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName());
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_us)));
    }


    public void m139x8db880e8(View view) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + requireActivity().getPackageName())));
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + requireActivity().getPackageName())));
        }
    }


    public void m140x53e309a9(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://google.com")));
    }


    private void setUpTheme() {
        if (new MyThemeHandler().getAppTheme(getActivity()).isDark()) {
            binding.headingTv.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void setData() {
        int parseInt = Integer.parseInt(SharedPreferenceUtils.getCycles(getActivity()));
        String date = SharedPreferenceUtils.getDate(getActivity());
        TextView textView = binding.cycleLengthTv;
        textView.setText(parseInt + " " + getResources().getString(R.string.days));
        TextView textView2 = binding.periodLengthTv;
        textView2.setText(SharedPreferenceUtils.getCycleLength(getActivity()) + " " + getResources().getString(R.string.days));
        int daysBetweenTwoDates = (int) OvulationCalculations.daysBetweenTwoDates(OvulationCalculations.getOvulation(date, parseInt), OvulationCalculations.getNextPeriod(date, parseInt));
        TextView textView3 = binding.lutealPhaseTv;
        textView3.setText(daysBetweenTwoDates + " " + getResources().getString(R.string.days));
    }

    private void setUpThemesRecycler() {
        this.adapter = new CustomThemesSelectorAdapter(getActivity(), new ArrayList(Arrays.asList(MyThemeHandler.CUSTOM_THEMES)), this.handler.getAppThemeIndex(getActivity())) {
            @Override
            public void onThemeItemSelected(MyCustomTheme myCustomTheme) {
                SettingsFragment.this.handler.setAppTheme(SettingsFragment.this.adapter.getSelectedTheme(), SettingsFragment.this.getActivity());
                SettingsFragment.this.startActivity(new Intent(SettingsFragment.this.getContext(), MainActivity.class));
                SettingsFragment.this.getActivity().finishAffinity();
            }
        };
        binding.themesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        binding.themesRecycler.setAdapter(this.adapter);
    }
}
