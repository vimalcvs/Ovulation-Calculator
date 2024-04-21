package com.demo.periodtracker.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.demo.periodtracker.Fragments.BlogsFragment;
import com.demo.periodtracker.Fragments.CalendarFragment;
import com.demo.periodtracker.Fragments.CategoryBlogsFragment;
import com.demo.periodtracker.Fragments.HomeFragment;
import com.demo.periodtracker.Fragments.SettingsFragment;


public class FragmentsAdapter extends FragmentStateAdapter {
    public FragmentsAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public Fragment createFragment(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        return new SettingsFragment();
                    }
                    return new HomeFragment();
                }
                return new CategoryBlogsFragment();
            }
            return new BlogsFragment();
        }
        return new CalendarFragment();
    }
}
