package com.example.myapplicationebookssyncfusion;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//FragmentStatePagerAdapter :dont keep many pages in memory - kepp max 03 to optimiwze performance
public class PageAdapter extends FragmentPagerAdapter {

    private int[] colors;

    //Nous créons un constructeur personnalisé qui nous permettra de passer le FragmentManager (tiens encore lui ! Normal, il est utilisé pour gérer les
    // fragments au sein de la classe mère FragmentPagerAdapter) ainsi que notre tableau de couleurs.
    public PageAdapter(FragmentManager mgr, int[] colors)
    {
        super(mgr);
        this.colors=colors;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position,this.colors[position]));
    }

    @Override
    public int getCount() {
        return 5;
    }


    //method used by TabLayout
    //La méthode getPageTitle( ) a spécialement été conçue pour retourner le titre de chacune des pages d'un ViewPager.
    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Page "+position;
    }
}
