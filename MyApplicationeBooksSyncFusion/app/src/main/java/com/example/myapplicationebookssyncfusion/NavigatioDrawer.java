package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class NavigatioDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private Fragment NewsFragment;
    private Fragment BalanceFragment;
    private Fragment TransportFragment;

    //FOR EEACH Fragment  -identify by one number*
    private static  final int FRAGMENT_NEWS=0;
    private static  final int FRAGMENT_BALANCE=1;
    private static  final int FRAGMENT_TRANSPORT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigatio_drawer);

        //6 - Configure All views
        this.configureToolBar();
        this.configureDrawerLayout();;
        this.configureNavigationView();

        //-by default show First Fragment
        this.showDefaultFragment();
    }

    private void showDefaultFragment() {
        Fragment visibleFragement=getSupportFragmentManager().findFragmentById(R.id.frmLayout);
        if(visibleFragement==null) {
            //-Show News fragment
            this.showFragment(FRAGMENT_NEWS);
            //-Mark a selected the menu item corresponding to fragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        //4- handle Navigation item Click
        int id=item.getItemId();

        switch(id)
        {
            case R.id.connexion:
               // Log.d(getClass().getSimpleName(),"clicked Connexion");
                this.showFragment(FRAGMENT_NEWS);
                break;
            case R.id.balance:this.showFragment(FRAGMENT_BALANCE);
                break;
            case R.id.transport:this.showFragment(FRAGMENT_TRANSPORT);
                break;
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
        navigationView.setNavigationItemSelectedListener(this);
    }

      private void showFragment(int fragmentIdentifier)
      {
          switch(fragmentIdentifier)
          {
              case FRAGMENT_NEWS:this.showNewsFragment();
              break;
              case FRAGMENT_BALANCE:this.showBalanceFragment();
              break;
              case FRAGMENT_TRANSPORT:this.showTransportFragment();
              break;
              default:break;
          }
      }

//Create each fragment page and show it
      private void showNewsFragment()
      {
          if(this.NewsFragment==null)
              this.NewsFragment= com.example.myapplicationebookssyncfusion.NewsFragment.newInstance();
          this.startTransactionFragment(this.NewsFragment);
      }

private void showBalanceFragment()
{
    if(this.BalanceFragment==null)
        this.BalanceFragment= com.example.myapplicationebookssyncfusion.BalanceFragment.newInstance();
    this.startTransactionFragment(this.BalanceFragment);
}

      private  void showTransportFragment()
      {
          if(this.TransportFragment==null)
              this.TransportFragment= com.example.myapplicationebookssyncfusion.TransportFragment.newInstance();
          this.startTransactionFragment(this.TransportFragment);
      }

      //-method generic that will replaceand showa fragment insidethe MainActivity frame Layout
      private void startTransactionFragment(Fragment fragment)
      {
          if(!fragment.isVisible())
          {
              getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.frmLayout,fragment)
                                            .commit();
          }
      }
}
