package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistADataActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE_PATH="Kpleus.messgePath";
    private static String SHARED_PREF_KEYS="FCO Existing input";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_a_data);

        EditText edT=(EditText)findViewById(R.id.txData);
        edT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input=v.getText().toString();
                saveStringWithSharedPreferences(SHARED_PREF_KEYS,input);
                return false;
                //return true;
            }
        });

        //Load the string from SharedPreferences
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        String existingInput=prefs.getString(SHARED_PREF_KEYS,"");

        //Log.d(getClass().getSimpleName(),existingInput);

        //if(existingInput !=null && !existingInput.isEmpty())
        //{edT.setTextColor(Color.RED);
            edT.setText(existingInput);
    //}
        //else {
        //edT.setTextColor(Color.YELLOW);
        //}

    }

    private void saveStringWithSharedPreferences(String key, String value) {


        //the easiest way to store information between user
        //sessions. It allows you to store primitive data (Booleans, floats, ints, longs, and strings) using
        //key-value pairs, much like a persistent hashtable
        //Get the SharedPreference editor
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();

        //save the string
        Log.d(getClass().getSimpleName(),value);
        editor.putString(key,value);

        //commit the changes
        editor.commit();
    }

    //store information directly on the device’s hard drive.  for complex datas
    //FileOutputStream works with bytes, so the string has to be converted before
    //passing it to write().
    public void saveStringWithInternalStorage(String filename,String value) throws IOException {
        FileOutputStream output=  openFileOutput(filename,MODE_PRIVATE);
        byte[] data=value.getBytes();
        output.write(data);
        output.close();
    }

    public void saveStringWithDatabase(String value)
    {
        ContentValues values=new ContentValues();
        values.put(MessageOpenHelper.COLUMN_AUTHOR,"Kpleus");
        values.put(MessageOpenHelper.COLUMN_MESSAGE,value);
        //Record the contentvalues in sqlite database

        //---------acces to BDD to write
        MessageOpenHelper dbHelper=new MessageOpenHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();


        long id=db.insert(MessageOpenHelper.TABLE_MESSAGES,null,values);

        Log.d(getClass().getSimpleName(),String.format("Saved new record to database with ID : %d",id));
        dbHelper.close();
    }

    public void ClearDataSession(View view) {
Button b=(Button)view;
/*
SharedPreferences settings=getPreferences(MODE_PRIVATE);
settings.edit().clear().commit(); //remove them all
Log.d(getClass().getSimpleName(),"All Data Session Cleared !");
 */
    }

    public void SwitchPage(View view) throws IOException {
        Intent intent=new Intent(this,SecondActivity.class);

        EditText edT=(EditText)findViewById(R.id.txData);
        //-------------------Save into File in disk drive
        /*
        String filePath="/Get_File/filename.txt";
        File myFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+filePath);
        if(!myFile.exists())
        {
            try {
                myFile.createNewFile();
                Log.i(getClass().getSimpleName(), myFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EditText edT=(EditText)findViewById(R.id.txData);
        saveStringWithInternalStorage(myFile.toString(),edT.getText().toString());

        intent.putExtra(EXTRA_MESSAGE_PATH,myFile.toString());
        */

        saveStringWithDatabase(edT.getText().toString());
        startActivity(intent);
    }
}
