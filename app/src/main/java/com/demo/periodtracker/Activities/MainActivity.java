package com.demo.periodtracker.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.demo.periodtracker.Adapters.FragmentsAdapter;

import com.demo.periodtracker.R;
import com.demo.periodtracker.ThemesFiles.MyCustomTheme;
import com.demo.periodtracker.ThemesFiles.MyThemeHandler;
import com.demo.periodtracker.Utils.Utils;
import com.demo.periodtracker.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private final int[] tabIcons = {R.drawable.ic_home, R.drawable.ic_calendar, R.drawable.ic_blogs, R.drawable.ic_settings, R.drawable.ic_settings_gear};
    ActivityMainBinding binding;
    MyCustomTheme theme;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.theme = new MyThemeHandler().getAppTheme(this);
        binding.viewPager.setAdapter(new FragmentsAdapter(supportFragmentManager, getLifecycle()));
        binding.viewPager.setOffscreenPageLimit(4);
        binding.viewPager.setUserInputEnabled(false);
        setUpTheme();
        final TabLayout tabLayout = binding.tabLayout;
        for (int i : this.tabIcons) {
            tabLayout.addTab(tabLayout.newTab().setIcon(i));
        }
        for (int i2 = 0; i2 < tabLayout.getTabCount(); i2++) {
            tabLayout.getTabAt(i2).getIcon().setColorFilter(ContextCompat.getColor(this, this.theme.getThemeColor()), PorterDuff.Mode.SRC_IN);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i3) {
                TabLayout tabLayout2 = tabLayout;
                tabLayout2.selectTab(tabLayout2.getTabAt(i3));
            }
        });
    }

    private void setUpTheme() {
        Utils.makeTransparentStatusBar(this);
        binding.mainParentLayout.setBackground(getResources().getDrawable(this.theme.getBgImg()));
        binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(this.theme.getThemeColor()));
    }

    @Override
    public void onBackPressed() {
        int currentItem = binding.viewPager.getCurrentItem();

        if (currentItem != 0) {

            binding.viewPager.setCurrentItem(0);
        } else {

            super.onBackPressed();
        }

    }
}
