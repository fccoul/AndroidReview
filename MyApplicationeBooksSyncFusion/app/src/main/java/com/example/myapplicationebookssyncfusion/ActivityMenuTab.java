package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class ActivityMenuTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu_tab);
        setContentView(R.layout.layout_swipping_tab);

        //this.configureViewPager();
        this.configureViewPagerAndTabs();
    }

    private void configureViewPager() {
        ViewPager vp=(ViewPager)findViewById(R.id.menuSwipe);
        vp.setAdapter(new PageAdapter(getSupportFragmentManager(),getResources().getIntArray(R.array.colorPagesViewPager)));
    }

    private void configureViewPagerAndTabs()
    {
        ViewPager pager=(ViewPager)findViewById(R.id.menuSwipe);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),getResources().getIntArray(R.array.colorPagesViewPager)));

        TabLayout tb=(TabLayout)findViewById(R.id.menuTabs);
        tb.setupWithViewPager(pager);
        //Tabs have the same width
        tb.setTabMode(TabLayout.MODE_FIXED);
    }
}
