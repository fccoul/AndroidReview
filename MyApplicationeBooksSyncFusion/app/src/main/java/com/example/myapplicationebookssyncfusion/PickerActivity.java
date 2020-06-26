package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class PickerActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView txt=(TextView)findViewById(R.id.pickerText);
        String message=String.format("Selected Date : %d %d %d",month+1,dayOfMonth,year);
        txt.setText(message);
    }
    public void showDatePickerDialog(View view)
    {
        DialogFragment picker=new DatePickerFragment();
        picker.show(getSupportFragmentManager(),"datePicker");


    }



}
