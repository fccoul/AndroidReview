package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class NavigatioDrawer extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigatio_drawer);

        //6 - Configure All views
        this.configureToolBar();
        this.configureDrawerLayout();;
        this.configureNavigationView();
    }

    @Override
    public void onBackPressed(){
        //5- Handle back click to close menu
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item)
    {
        //4- handle Navigation item Click
        int id=item.getItemId();

        switch(id)
        {
            case R.id.connexion:break;
            case R.id.balance:break;
            case R.id.transport:break;
            default:break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);
        return  true;
    }
    //---------------------------------------------------------
    //Configuration
    //---------------------------------------------------------

    //1- toolbar
    private void configureToolBar()
    {
        this.toolbar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    //2- Configure drawer layout
    private void configureDrawerLayout()
    {
        this.drawerLayout=(DrawerLayout)findViewById(R.id.drwLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //3 - Configure NavigationView
    private void configureNavigationView()
    {
        this.navigationView=(NavigationView)findViewById(R.id.navigView);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this.navigationView);
    }


}
