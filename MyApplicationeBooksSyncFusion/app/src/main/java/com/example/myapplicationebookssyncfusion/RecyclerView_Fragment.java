package com.example.myapplicationebookssyncfusion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.SparseBooleanArray;
//import android.view.ActionMode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerView_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerView_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static  View view;
    private static RecyclerView recyclerView;
    private static ArrayList<Item_Model> item_models;
    private static RecyclerView_Adapter adapter;
    private ActionMode mActionMode;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerView_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerView_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerView_Fragment newInstance(String param1, String param2) {
        RecyclerView_Fragment fragment = new RecyclerView_Fragment();
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

        view=inflater.inflate(R.layout.fragment_recycler_view,container,false);
        populateRecycleView();
        implemementRecyclerViewClicklisteners();
        return view;
    }

    //--poulate Listview with dummy datas
    private void populateRecycleView() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerVwId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        item_models=new ArrayList<>();
        for(int i=1;i<=40;i++)
            item_models.add(new Item_Model("Title "+i,"Sub Title "+i));
        adapter=new RecyclerView_Adapter(getActivity(),item_models);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void onListItemSelect(int position) {

        adapter.toggleSelection(position);//toggle the selection
        boolean hasCheckedItems=adapter.getSelectedCount()>0; //check if any items are already selected or not
        if(hasCheckedItems && mActionMode==null)
            mActionMode=((AppCompatActivity)getActivity()).startSupportActionMode(new Toolbar_ActionMode_Callback(getActivity(),adapter,null,item_models,false));

        else if(!hasCheckedItems && mActionMode!=null)
            //there no selected items,finish the actionMode
            mActionMode.finish();
        if(mActionMode!=null)
            //set actionmode title on item selection
            mActionMode.setTitle(String.valueOf(adapter.getSelectedCount())+ " selected");

    }

    private void implemementRecyclerViewClicklisteners() {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerClick_Listener() {
            @Override
            public void onClick(View view, int position) {
                //if actionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                //Select item on long click
                onListItemSelect(position);
            }
        }));
    }

       //set actionmode null after use
        public void setNullToActionMode() {
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
        mActionMode.finish();//finish action mode after use
    }
    }

