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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistADataActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE_PATH="Kpleus.messgePath";
    private static String SHARED_PREF_KEYS="FCO Existing input";

    SharedPreferences sharedPreferences;
    SharedPreferences prefs;
    TextView name;
    TextView email;
    TextView codPin;
    EditText edT;
    public static final String myPreference="myPrefFCO";
    public static final String Name="nameKey";
    public static final String Email="emailKey";
    private static final String CodPIN="codePin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_a_data);

         edT=(EditText)findViewById(R.id.txData);

        name=(TextView)findViewById(R.id.etName);
        email=(TextView)findViewById(R.id.etEmail);
        codPin=(TextView)findViewById(R.id.etcodePIN);
        //-method automatically creates the file with the name specified,
        sharedPreferences=getSharedPreferences(myPreference,0);//0:mode_private

        //-To figure out when the user has pressed the Done button,
        edT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_NEXT) {
                    String input = v.getText().toString();
                    Log.d(getClass().getSimpleName(), input);
                    saveStringWithSharedPreferences(SHARED_PREF_KEYS, input);
                    return true;
                }
                Log.i(getClass().getSimpleName(),"acton not allowed !!!");
                return  false;
                //return true;
            }
        });

        edT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.d(getClass().getSimpleName(),s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d(getClass().getSimpleName(),s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.d(getClass().getSimpleName(),s.toString());
                /*
                if(s.charAt(s.length()-1)=='\n')
                {
                    Log.d(getClass().getSimpleName(),"ouf well well");
                    String input =s.toString();
                    saveStringWithSharedPreferences(SHARED_PREF_KEYS, input);
                }*/
            }
        });

        //Load the string from SharedPreferences SHARED_PREF_KEYS
         prefs=getPreferences(MODE_PRIVATE);
        String existingInput=prefs.getString(SHARED_PREF_KEYS,"");
        Log.d(getClass().getSimpleName(),existingInput);

        if(existingInput !=null && !existingInput.isEmpty())
        {edT.setTextColor(Color.GREEN);
        edT.setText(existingInput);
        }
        else {
        edT.setTextColor(Color.BLACK);
        }
    }

    //https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
    //Shared Preferences allows activities and applications to keep preferences, in the form of key-value pairs
    //datas will persist even when the user closes the application
    //Android stores Shared Preferences settings as XML file in shared_prefs folder under DATA/data/{application package} directory.
    //The DATA folder can be obtained by calling Environment.getDataDirectory().
    // the data is lost on performing one of the following options:
    //on uninstalling the application
    //on clearing the application data (through Settings)
    private void saveStringWithSharedPreferences(String key, String value) {

        //Log.d(getClass().getSimpleName(),"helloooooo"+value);
        //the easiest way to store information between user
        //sessions. It allows you to store primitive data (Booleans, floats, ints, longs, and strings) using
        //key-value pairs, much like a persistent hashtable
        //Get the SharedPreference editor
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editorPref=prefs.edit();

        //save the string
        Log.d(getClass().getSimpleName(),value);
        editorPref.putString(key,value);

        //commit the changes
        editorPref.commit();
    }

    //store information directly on the device’s hard drive.  for complex datas
    //FileOutputStream works with bytes, so the string has to be converted before
    //passing it to write().
    //Files saved to internal storage are deleted when your app in
    //uninstalled.
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

    public void SwitchPage(View view) throws IOException {
        Intent intent=new Intent(this,SecondActivity.class);

        //EditText edT=(EditText)findViewById(R.id.txData);
        //-------------------Save into File in disk drive

        //String filePath="/Get_File/filename.txt";
        String filePath="filename.txt";
        File myFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+filePath);
        Log.d(getClass().getSimpleName(),myFile.getAbsolutePath());
        /*
        if(!myFile.exists())
        {
            try {
                myFile.createNewFile();
                Log.i(getClass().getSimpleName(), myFile.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        edT=(EditText)findViewById(R.id.txData);
        //saveStringWithInternalStorage(myFile.toString(),edT.getText().toString());
        saveStringWithInternalStorage(filePath.toString(),edT.getText().toString());

        //intent.putExtra(EXTRA_MESSAGE_PATH,myFile.toString());
        intent.putExtra(EXTRA_MESSAGE_PATH,filePath.toString());

        saveStringWithDatabase(edT.getText().toString());
        startActivity(intent);
    }

//https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
    public void Save(View view) {
        String n=name.getText().toString();
        String m=email.getText().toString();
        String c=codPin.getText().toString();

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Name,n);
        editor.putString(Email,m);
        editor.putInt(CodPIN,Integer.parseInt(c));

        editor.commit();
    }

    public void Read(View view) {

        sharedPreferences=getSharedPreferences(myPreference,Context.MODE_PRIVATE);

        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        if(sharedPreferences.contains(Name))
        {
            name.setText(sharedPreferences.getString(Name,""));
            name.setTextColor(Color.RED);
        }
        else
        {
            if(prefs.contains(SHARED_PREF_KEYS))
            {
                name.setText(prefs.getString(SHARED_PREF_KEYS,""));
                name.setTextColor(Color.GREEN);
            }
            else
            {
                Log.d(getClass().getSimpleName(),"no share pref keys !!!!");
            }

        }
        if(sharedPreferences.contains(Email))
        {
            email.setText(sharedPreferences.getString(Email,""));
            email.setTextColor(Color.RED);
        }

       if(sharedPreferences.contains(CodPIN))
        {
            Integer xx=sharedPreferences.getInt(CodPIN,0);
            Log.d(getClass().getSimpleName(),xx.toString());
            codPin.setText(xx.toString(), TextView.BufferType.EDITABLE);
            codPin.setTextColor(Color.RED);

        }

    }

    public void Clean(View view) {
        /*
        name=(TextView)findViewById(R.id.etName);
        email=(TextView)findViewById(R.id.etEmail);
        codPin=(TextView)findViewById(R.id.etcodePIN);
        */
        name.setText("");
        name.setTextColor(Color.BLACK);
        email.setText("");
        email.setTextColor(Color.BLACK);
        codPin.setText("");
        codPin.setTextColor(Color.BLACK);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(Name);
        editor.remove(Email);
        editor.remove(CodPIN);
        editor.commit();


       SharedPreferences.Editor editPref=prefs.edit();
       editPref.clear() ; //delete all
        editPref.commit();
        edT.setText("");
        edT.setTextColor(Color.BLACK);
    }
}
