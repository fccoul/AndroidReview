package com.example.myapplicationebookssyncfusion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public static View view;
    private static ListView_Adapter adapter;
    private static ListView listView;
    //Action mode Toolbar
    private ActionMode mActionMode;
    private static ArrayList<Item_Model> item_models;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list_view, container, false);
        populateListView();
        implementListViewClickListeners();
        return view;
    }

    private void implementListViewClickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if ActionMode not null select item
                if(mActionMode!=null)
                    onListItemSelect(position);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Select item long click
                 onListItemSelect(position);
                 return true;
            }
        });
    }

    private void onListItemSelect(int position) {

        adapter.toggleSelection(position);//toggle the selection
        boolean hasCheckedItems=adapter.getSelectedCount()>0; //check if any items are already selected or not
        if(hasCheckedItems && mActionMode==null)
          mActionMode=((AppCompatActivity)getActivity()).startSupportActionMode((ActionMode.Callback) new Toolbar_ActionMode_Callback(getActivity(),null,adapter,item_models,true));
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();
        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(adapter
                    .getSelectedCount()) + " selected");
    }

    //Set Action mode null after use
    public void setNullToActionMode()
    {
        if(mActionMode!=null)
            mActionMode=null;
    }

    //Delete Selected rows
    public void deleteRows()
    {
        SparseBooleanArray selected=adapter.getSelectedIds();
        for(int i=(selected.size()-1);i>=0;i--)
        {
            if(selected.valueAt(i))
            {
                //if current id is selected remove tne item via key
                item_models.remove(selected.keyAt(i));
                adapter.notifyDataSetChanged();
            }
        }
        Toast.makeText(getActivity(),selected.size()+" item deleted",Toast.LENGTH_SHORT).show();
        mActionMode.finish();;
    }

    //--poulate Listview with dummy datas
    private void populateListView() {
        listView=(ListView)view.findViewById(R.id.lstVwid);
        item_models=new ArrayList<>();
        for(int i=1;i<=40;i++)
            item_models.add(new Item_Model("Title "+i,"Sub Title "+i));
        adapter=new ListView_Adapter(getActivity(),item_models);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
