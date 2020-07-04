package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.FontsContract;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG ="SecondActivityKpleus" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent=getIntent();
        String message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tv = (TextView) findViewById(R.id.txtSndActivity);
        if(message!=null &&!message.isEmpty()) {

            tv.setText(message);
            Log.d(TAG, message);
        }

        /*
        String messPath=intent.getStringExtra(PersistADataActivity.EXTRA_MESSAGE_PATH);
        if(messPath!=null &&!messPath.isEmpty()) {
            try {
                ReadInfoFile(messPath,tv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        GetInfosFromBDD();
    }

    void ReadInfoFile(String fileName,TextView tv) throws IOException {
        FileInputStream input = null;
        try {
            input = openFileInput(fileName);
            //The FileInputStream.available() method returns the estimated number of bytes that can
            //be read without blocking for more input. We use this to figure out the length of a new byte array,
            //which is populated by FileInputStream.read(). These bytes are then converted to a string
            //and displayed to the user.
            int maxbytes = input.available();
            byte[] data = new byte[maxbytes];
            input.read(data, 0, maxbytes);
            while (input.read(data) != -1) {
            }
            ;

            String existingInput = new String(data);
            tv.setText(existingInput);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

void GetInfosFromBDD()
        {
        //Load the most recent record from SQLite database
            //----access to BDDD to readable
            MessageOpenHelper dbHelper=new MessageOpenHelper(this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();

            //Fetchthe records with the appropiate author name
            //String authorNmae="Edge";
            String authorNmae="Kpleus";
            String []columns={MessageOpenHelper.COLUMN_ID,
                           MessageOpenHelper.COLUMN_MESSAGE};
            String selection=MessageOpenHelper.COLUMN_AUTHOR+" = '"+authorNmae+"'";

            //You can define the columns to select, the selection constraints, the
            //grouping and ordering behavior, and selection limits.
            Cursor cursor=db.query(MessageOpenHelper.TABLE_MESSAGES, columns,selection,null,null,null,null);
           // Cursor cursor=db.query(MessageOpenHelper.TABLE_MESSAGES, columns,selection,null,null,null,null);
            TextView tvd = (TextView) findViewById(R.id.txtSndActivity);
            if(cursor!=null && cursor.getCount()>0) {
                //Display the most recent record
                cursor.moveToLast();
                //cursor.moveToPosition(3);
                long id = cursor.getLong(0); //index position tient compte d elaposition lors d ela declaration de []columns
                String message = cursor.getString(1);
                Log.d(getClass().getSimpleName(), String.format("Retrieved into from database. ID:%d Message: %s", id, message));


                tvd.setText(message);
            }
            else
            {
                Log.d(getClass().getSimpleName(), "no data");
                tvd.setText("no data is matching with filter");
            }
            //-clean up
            cursor.close();
            dbHelper.close();


        }
}

