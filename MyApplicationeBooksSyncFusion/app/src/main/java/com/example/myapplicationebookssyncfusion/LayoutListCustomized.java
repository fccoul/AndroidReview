package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LayoutListCustomized extends AppCompatActivity {

    private ArrayList<DataItem> data;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listviewcustomized);

        this.data=new ArrayList<DataItem>();
        data.add(new DataItem("John Smith","(555) 454-5545"));
        data.add(new DataItem("Mart mel","(555) 665-5665"));
        data.add(new DataItem("Bill Kim","(555) 446-4464"));

        adapter=new CustomAdapter(this,data);
        ListView lvw=(ListView) findViewById(R.id.lstVwCust);
        lvw.setAdapter(adapter);

    }
}
