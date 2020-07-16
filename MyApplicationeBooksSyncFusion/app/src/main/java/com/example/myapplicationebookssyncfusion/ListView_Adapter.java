package com.example.myapplicationebookssyncfusion;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListView_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item_Model> item_model_arrayList;
    private SparseBooleanArray mSelectedItemsIds;


    public ListView_Adapter(Context context, ArrayList<Item_Model> item_model_arraylist)
    {
        this.context=context;
        this.item_model_arrayList=item_model_arraylist;
        mSelectedItemsIds=new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return item_model_arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return item_model_arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if(convertView==null)
        {
convertView=inflater.inflate(R.layout.item_row,parent,false);
holder=new ViewHolder();
           holder.title=(TextView)convertView.findViewById(R.id.titleId);
           holder.subTitle=(TextView)convertView.findViewById(R.id.subtitleId);
        }
        else
            holder=(ViewHolder)convertView.getTag();
holder.title.setText(item_model_arrayList.get(position).getTitle());
holder.subTitle.setText(item_model_arrayList.get(position).getSubTitle());

/*change background color of the selected items in list view*/
        convertView.setBackgroundColor(mSelectedItemsIds.get(position)?0x9934B5E4: Color.TRANSPARENT);

        return convertView;
    }

    private class  ViewHolder
    {
        TextView title,subTitle;
    }


    //-Methods requires for do selections, remove selections, etc.
    //----Toggle selevtion methods
    public void toggleSelection(int position)
    {
        selectView(position, !mSelectedItemsIds.get(position));
    }
    //----Remove selected selection
    public void removeSelection()
    {
        mSelectedItemsIds=new SparseBooleanArray();
        notifyDataSetChanged();
    }
    //-----put or delete selected position into SparseBooleanArray
    public void selectView(int position,boolean value)
    {
        if(value)
            mSelectedItemsIds.put(position,value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }
    //-------Get Total selected Count
    public int getSelectedCount()
    {
        return mSelectedItemsIds.size();
    }

    //return all selected ids
    public SparseBooleanArray getSelectedIds()
    {
        return  mSelectedItemsIds;
    }


}
