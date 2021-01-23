package com.example.signin;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.signin.adapter.AccountPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.signin.database.AccountDB;

public class AccountActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    AccountDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = new AccountDB(this);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view);

        tabLayout.addTab(tabLayout.newTab().setText("Sign in"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AccountPagerAdapter adapter = new AccountPagerAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(onTabSelectedListener(viewPager));


    }
    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
