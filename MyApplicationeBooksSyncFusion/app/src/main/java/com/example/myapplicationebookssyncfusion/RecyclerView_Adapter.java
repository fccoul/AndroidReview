package com.example.myapplicationebookssyncfusion;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<DemoViewHolder> {

    private ArrayList<Item_Model> arrayList;
    private Context context;
    private SparseBooleanArray mSelectedItemsIds;

    public RecyclerView_Adapter(Context context, ArrayList<Item_Model> arrayList)
    {
this.context=context;
this.arrayList=arrayList;
mSelectedItemsIds=new SparseBooleanArray();
    }


    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ViewGroup mainGroup=(ViewGroup)inflater.inflate(R.layout.item_row,parent,false);
        return new DemoViewHolder(mainGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {

        //setting text over text view
        holder.title.setText(arrayList.get(position).getTitle());
        holder.subTitle.setText(arrayList.get(position).getSubTitle());

        //-change backgroud color of the selected items in list view
        holder.itemView.setBackgroundColor(mSelectedItemsIds.get(position)?0x9934B5E4: Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return (null!=arrayList?arrayList.size():0);
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
