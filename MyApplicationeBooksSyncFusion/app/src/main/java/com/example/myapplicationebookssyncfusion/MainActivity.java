package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String TAG="MainActivityKpleus";

    public static final String EXTRA_MESSAGE="Kpleus.messge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextPage(View view) {

        Log.d(TAG,"switch next Page");
        Button button=(Button)view;
        String message=button.getText().toString();
        Log.i(TAG,message);
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}
