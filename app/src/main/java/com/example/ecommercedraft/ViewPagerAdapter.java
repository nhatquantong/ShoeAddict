package com.example.ecommercedraft;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ecommercedraft.fragment.CartFragment;
import com.example.ecommercedraft.fragment.HomeFragment;
import com.example.ecommercedraft.fragment.NotificationFragment;
import com.example.ecommercedraft.fragment.ProfileFragment;
import com.example.ecommercedraft.fragment.WishListFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new WishListFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
