package com.example.myapplicationebookssyncfusion;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFrgamentlist=new ArrayList<>();
    private final List<String> mfragmentTitleList=new ArrayList<>();


    public ViewPagerAdapter(FragmentManager manager)
    {
        super(manager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFrgamentlist.get(position);
    }

    @Override
    public int getCount() {
        return mFrgamentlist.size();
    }

    //adding fragments and title method
    public void addFrag(Fragment fragment,String title)
    {
        mFrgamentlist.add(fragment);
        mfragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mfragmentTitleList.get(position);
    }
}
