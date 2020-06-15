package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityComponentUI extends AppCompatActivity {

    private static final String TAG="AcComponentUIKpleus";
   boolean isGreen;
   boolean isBold;
   boolean isBig;

   ArrayList<String> colorSpinner=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_u_i);

        ImageView img=(ImageView) findViewById(R.id.imgVw);
        Resources res=getResources();
        Drawable srcImg=res.getDrawable(R.drawable.ic_launcher_background);
img.setImageDrawable((srcImg));

        ImageView img1=(ImageView) findViewById(R.id.imgVw1);
        img1.setImageResource(R.mipmap.ic_launcher_round);

        Button btnImg=(Button)findViewById(R.id.btnImg);
        btnImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button sender=(Button)v;
                String Title=sender.getText().toString();
                Log.d(TAG,Title);
            }
        });

       EditText EdTxt=(EditText)findViewById(R.id.eDTxt);
        String txt=EdTxt.getText().toString();
        //Log.i(TAG,txt);
       EdTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if(actionId== EditorInfo.IME_ACTION_SEARCH)
               {
                   String txt=v.getText().toString();
                   Log.d(TAG,txt);
                   return  true;
               }
               else
               {
                   Log.d(TAG,"Nothing woman" + actionId);
               }
               return false;
           }
       });
       isGreen=true;
       isBold=false;
       isBig=false;
       synchronizeChkBox();
       synchronizeTxtVw();

        RadioButton serif=(RadioButton)findViewById(R.id.serif);
        serif.setChecked(true);
        rdButtonClicked(null);

    }

    private void synchronizeTxtVw() {
        TextView tv=(TextView) findViewById(R.id.txtVwChk);
        if(isGreen)
        {
            tv.setTextColor(Color.parseColor("#FF009900"));
        }
        else
        {
            tv.setTextColor(Color.parseColor("#FF000000"));
        }

        if(isBold)
        {
            Log.d(TAG,"isBold OK");
            tv.setTypeface(Typeface.create("default",Typeface.BOLD));
        }
        else
        {
            Log.d(TAG,"isBold KO KO");
            tv.setTypeface(Typeface.create("default",Typeface.NORMAL));
        }

        if(isBig)
        {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        }
        else
        {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        }

        initSpinner();
    }

    private void initSpinner() {
        colorSpinner.add("Red");
        colorSpinner.add("Blue");
        colorSpinner.add("Yellow");
        colorSpinner.add("Orange");
        colorSpinner.add("Green");
        colorSpinner.add("Purple");

        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colorSpinner);
        //data form xml....
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.spinnerColor,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spin=(Spinner) findViewById(R.id.colorSpinner);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String setColor=(String)parent.getItemAtPosition(position);
                setTextColor(setColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setTextColor(String setColor) {
        String hexColor="#FF000000";
        switch(setColor)
        {
            case "Red":hexColor="#FFAA0000";
                break;
            case "Blue":hexColor="#FF0000AA";
                break;
            case "Yellow":hexColor="#FFCCAA00";
                break;
            case "Orange":hexColor="#FFCC6600";
                break;
            case "Green":hexColor="#FF00AA00";
                break;
            case "Purple":hexColor="#FF6600AA";
                break;

        }

        TextView tv=(TextView) findViewById(R.id.txtVwSpin);
        tv.setTextColor(Color.parseColor(hexColor));
    }

    private void synchronizeChkBox() {

        CheckBox ckGreen=(CheckBox)findViewById(R.id.chkGreen);
        CheckBox ckBold=(CheckBox)findViewById(R.id.chkBold);
        CheckBox ckBig=(CheckBox)findViewById(R.id.chkBig);
        ckGreen.setChecked(isGreen);
        ckBold.setChecked(isBold);
        ckBig.setChecked(isBig);
    }

    public void chkBoxClicked(View view) {
        CheckBox chk=(CheckBox) view;
        boolean isChecked=chk.isChecked();
        switch (view.getId())
        {
            case R.id.chkGreen:isGreen= isChecked;Log.d(TAG,"isGeen clickede");
            break;
            case R.id.chkBold:isBold= isChecked;Log.d(TAG,"isBold clickede");
            break;
            case R.id.chkBig:isBig= isChecked;
            break;
        }
        synchronizeTxtVw();
    }

    public void rdButtonClicked(View view) {

        RadioGroup grp=(RadioGroup) findViewById(R.id.rdGrp);
        String typeface;

        switch (grp.getCheckedRadioButtonId())
        {
            case R.id.sSerif:typeface="sans";
            break;
            case R.id.serif:typeface="serif";
            break;
            case R.id.monoSpace:typeface="monospace";
            break;
            default: typeface="default";
        }

        TextView tv=(TextView)findViewById(R.id.txtVwRd);
        tv.setTypeface(Typeface.create(typeface,Typeface.NORMAL));

        //grp.clearCheck();
    }
}
