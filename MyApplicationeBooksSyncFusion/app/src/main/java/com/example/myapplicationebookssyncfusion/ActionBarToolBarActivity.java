package com.example.myapplicationebookssyncfusion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.ListView;
//import android.supportwidget.Toolbar;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class ActionBarToolBarActivity extends AppCompatActivity {

  private  static  ViewPagerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_tool_bar);


        androidx.appcompat.widget.Toolbar toolbar=(androidx.appcompat.widget.Toolbar)findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);

        ViewPager viewPager=(ViewPager)findViewById(R.id.vwPagerTab);
        setUpViewPager(viewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tblayoutId);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
           adapter=new ViewPagerAdapter(getSupportFragmentManager());
           adapter.addFrag(new ListViewFragment(),"ListView");
           adapter.addFrag(new RecyclerView_Fragment(),"RecycleView");
           viewPager.setAdapter(adapter);
    }

    //return current fragment on basis of position
    public Fragment getFragment(int pos)
    {
        return  adapter.getItem(pos);
    }
}
