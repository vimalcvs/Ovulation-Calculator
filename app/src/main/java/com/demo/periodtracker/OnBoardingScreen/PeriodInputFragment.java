package com.demo.periodtracker.OnBoardingScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.demo.periodtracker.Activities.InputActivity;
import com.demo.periodtracker.R;
import com.demo.periodtracker.databinding.FragmentPeriodInputBinding;


public class PeriodInputFragment extends Fragment {
    FragmentPeriodInputBinding binding;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentPeriodInputBinding inflate = FragmentPeriodInputBinding.inflate(layoutInflater);
        this.binding = inflate;
        inflate.nextSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodInputFragment.this.m146x41c2179c(view);
            }
        });
        binding.backSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodInputFragment.this.m147x9acd631d(view);
            }
        });
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodInputFragment.this.m148xf3d8ae9e(view);
            }
        });
        binding.prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodInputFragment.this.m149x4ce3fa1f(view);
            }
        });
        return binding.getRoot();
    }


    public void m146x41c2179c(View view) {
        InputActivity inputActivity = (InputActivity) requireActivity();
        inputActivity.periodLength = Integer.parseInt(binding.cycleLengthInp.getText().toString());
        ((ViewPager2) inputActivity.findViewById(R.id.viewPager)).setCurrentItem(2, true);
    }


    public void m147x9acd631d(View view) {
        ((ViewPager2) requireActivity().findViewById(R.id.viewPager)).setCurrentItem(0, true);
    }


    public void m148xf3d8ae9e(View view) {
        if (Integer.parseInt(binding.cycleLengthInp.getText().toString()) < 9) {
            binding.cycleLengthInp.setText(String.valueOf(Integer.parseInt(binding.cycleLengthInp.getText().toString()) + 1));
        } else {
            binding.cycleLengthInp.setText(String.valueOf(3));
        }
    }


    public void m149x4ce3fa1f(View view) {
        if (Integer.parseInt(binding.cycleLengthInp.getText().toString()) > 3) {
            binding.cycleLengthInp.setText(String.valueOf(Integer.parseInt(binding.cycleLengthInp.getText().toString()) - 1));
        } else {
            binding.cycleLengthInp.setText(String.valueOf(9));
        }
    }
}
