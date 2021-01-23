package com.example.signin.adapter;

import android.content.Context;

import com.example.signin.fragment.SignInFragment;
import com.example.signin.fragment.SignUpFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class AccountPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    int totalTabs;
    public AccountPagerAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context=context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SignInFragment signInFragment = new SignInFragment();
                return signInFragment;
            case 1:
                SignUpFragment signUpFragment = new SignUpFragment();
                return signUpFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
