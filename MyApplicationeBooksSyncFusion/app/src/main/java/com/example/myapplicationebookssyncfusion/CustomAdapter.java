package com.example.myapplicationebookssyncfusion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<DataItem> data;
    Context context;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context context,ArrayList<DataItem> data)
    {
        this.context=context;
        this.data=data;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    //methode return position dans la liste -cursorfierst strat at 0
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    //method should return the row ID unique or position of item into the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //see if the view needs to be inflated
        View v=convertView;
        if(v==null)
        {
            v=inflater.inflate(R.layout.list_item,null);
        }

        //extractedthe desired values
        TextView nameText= (TextView)  v.findViewById(R.id.valueLabel);
        TextView phoneText=(TextView) v.findViewById(R.id.phoneValue);

        //get the data item
        DataItem item=data.get(position);

        //display data item's properties
        nameText.setText(item.Name);
        phoneText.setText(item.PhoneNumber);

        return v;
    }
}
