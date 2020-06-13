package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LayoutList extends AppCompatActivity {


    private static final String TAG="Layout_GRiDListkpleus";
    private int count;
    private ArrayList<String> data;
private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.listviewdata);
        setContentView(R.layout.gridviewdata);
        //String [] data=new String[]{"item 1","item 2","item 3","item 4","item 5","item 6","item 7","item 8"};


        this.data=new ArrayList<String>();
        data.add("item 1");
        data.add("item 2");
        data.add("item 3");

        this.count=3;

        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //ListView lstvw=(ListView) findViewById(R.id.lstVwData);
        //lstvw.setAdapter(adapter);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //ArrayAdapter<String>  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        GridView gdVw=(GridView) findViewById(R.id.grdVwData);
        gdVw.setAdapter(adapter);

        gdVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            TextView tvw=(TextView) view;
                                            Log.d(TAG, String.format("You clicked : %s",tvw.getText()));
                                            Log.d(TAG,"position :"+position + " id : "+id);

                                            data.remove(position);
                                            //tvw.setText(tvw.getText()+ "updated");
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
        );


    }

    public void addItem (View view)
    {
       count++;
       String newItem=String.format("Item %d",count);
       this.data.add(newItem);
       this.adapter.notifyDataSetChanged();
    }
}
