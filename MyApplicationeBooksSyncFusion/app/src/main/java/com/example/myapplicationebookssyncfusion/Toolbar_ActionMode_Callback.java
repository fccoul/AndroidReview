package com.example.myapplicationebookssyncfusion;

import android.content.Context;
import android.drm.DrmStore;
import android.os.Build;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Toolbar_ActionMode_Callback implements ActionMode.Callback {

    private Context context;
    private RecyclerView_Adapter recyclerViewAdpater;
    private  ListView_Adapter listView_adapter;
    private ArrayList<Item_Model> message_models;
    private boolean isListViewFragment;


    public Toolbar_ActionMode_Callback(Context context,RecyclerView_Adapter recyclerViewAdpater,
                                       ListView_Adapter listView_adapter,ArrayList<Item_Model> message_models,
                                       boolean isListViewFragment)
    {
        this.context=context;
        this.recyclerViewAdpater=recyclerViewAdpater;
        this.listView_adapter=listView_adapter;
        this.message_models=message_models;
        this.isListViewFragment=isListViewFragment;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        //sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //so here show action menu according sdk levels
        if(Build.VERSION.SDK_INT<11)
        {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete),MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_copy),MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_forward),MenuItemCompat.SHOW_AS_ACTION_NEVER);
        }
        else
        {
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_copy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_forward).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_delete:
                //check if current action is form listview fragment or recyclerview fragment
                if(isListViewFragment) {
                    Fragment listFragment = new ActionBarToolBarActivity().getFragment(0);//get List Fragment
                    if (listFragment != null)
                        ((ListViewFragment) listFragment).deleteRows();//delete selected rows
                }else
                {
                    //if current fragment is recycler view fragment
                    Fragment recyclerFragment=new ActionBarToolBarActivity().getFragment(1);//get recycler view
                   if(recyclerFragment!=null)
                       ((RecyclerView_Fragment)recyclerFragment).deleteRows();
                }
                break;
            case R.id.action_copy:
                      //Get selected ids on baasis of fragement action mode
                SparseBooleanArray selected;
                if(isListViewFragment)
                    selected=listView_adapter.getSelectedIds();
                else
                    selected=recyclerViewAdpater.getSelectedIds();

                int selectedMessageSize=selected.size();

                for(int i=(selectedMessageSize-1);i>=0;i--)
                {
                    if(selected.valueAt(i))
                    {
                        //get selected data in model
                        Item_Model model=message_models.get(selected.keyAt(i));
                        String title=model.getTitle();
                        String subTitle=model.getSubTitle();

                        //print data to show if its working properly or not
                        Log.e("Selected item","Title -"+title+" n "+"Sub Title -"+subTitle);
                    }
                }
                Toast.makeText(context,"You selecetd Copy menu. ",Toast.LENGTH_SHORT).show();
                mode.finish();//finish action mode
                break;

            case R.id.action_forward:
                Toast.makeText(context,"You selected Forward menu",Toast.LENGTH_SHORT).show();
                mode.finish();
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        //when action mode destroyed remove selected selections and set action mode to null
        //Fisrt check current fragment action mode
        if(isListViewFragment)
        {
            listView_adapter.removeSelection();
            Fragment listFragment=new ActionBarToolBarActivity().getFragment(0);//Get List frgment
            if(listFragment!=null)
                ((ListViewFragment)listFragment).setNullToActionMode();//set action mode null
        }
        else
        {
            recyclerViewAdpater.removeSelection();//remove selection
            Fragment recyclerFragment=new  ActionBarToolBarActivity().getFragment(1);//get recycler Fragment
            if(recyclerFragment!=null)
                ((RecyclerView_Fragment) recyclerFragment).setNullToActionMode();//set action mode null
        }
    }
}
