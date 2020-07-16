package com.example.myapplicationebookssyncfusion;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DemoViewHolder extends RecyclerView.ViewHolder {

    public TextView title,subTitle;

    public DemoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.title=(TextView)itemView.findViewById(R.id.titleId);
        this.subTitle=(TextView)itemView.findViewById(R.id.subtitleId);
    }
}
