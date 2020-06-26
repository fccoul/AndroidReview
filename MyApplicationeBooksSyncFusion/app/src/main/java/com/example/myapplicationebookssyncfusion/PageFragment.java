package com.example.myapplicationebookssyncfusion;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class PageFragment extends Fragment {


    //variables statiques servant d'identifiants des futures valeurs passées à notre Bundle.
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";

    public PageFragment(){}

 //method that will create a new instance of pageFrgment and add data to its bundle
    public static PageFragment newInstance(int position,int color)
    {
        PageFragment frag=new PageFragment();
        Bundle args=new Bundle();
        args.putInt(KEY_POSITION,position);
        args.putInt(KEY_COLOR,color);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //get le layout du Fragment
        View result=inflater.inflate(R.layout.activity_menu_swipe, container, false);

//get widget du layout et serialize it
        LinearLayout rootView=(LinearLayout)result.findViewById(R.id.layoutMenuSwipe);
        TextView textView=(TextView)result.findViewById(R.id.frgPageTitle);

        //get  data from bundle
        int position=getArguments().getInt(KEY_POSITION,-1);
        int color=getArguments().getInt(KEY_COLOR,-1);

        //Update widget with it
        rootView.setBackgroundColor(color);
        textView.setText("Page numéro " +position);

        Log.e(getClass().getSimpleName(),"OnCreateView called for fragment number " +position);
        Log.d(getClass().getSimpleName(),"OnCreateView called for fragment number " +position);
        // Inflate the layout for this fragment
        return result;
    }
}
