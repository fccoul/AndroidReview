package com.example.myapplicationebookssyncfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class RatingbarActivity extends AppCompatActivity {

    private static RatingBar ratingBar_default,getRatingBar,setRatingBar;
    private static TextView ratingBarValue_default,setCountText;
    int count;
    float curRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);

        init();
        setListeners();
    }

    private void init(){
        ratingBar_default=(RatingBar)findViewById(R.id.ratingBarDefault);
        getRatingBar=(RatingBar)findViewById(R.id.getRatingBar);
        setRatingBar=(RatingBar)findViewById(R.id.setRatingBar);
        ratingBarValue_default=(TextView)findViewById(R.id.ratingBarDefaultNumber);
        setCountText=(TextView)findViewById(R.id.setCountText);
    }

     void setListeners() {

        //rating_Bar Default
        ratingBar_default.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                   //get current rating
                String ratedVaue=String.valueOf(rating);

                //get numbers of stars
                int numStars=ratingBar.getNumStars();

                //setthe rated value and numstars on textview
                ratingBarValue_default.setText("Ratingvalue is : " +ratedVaue+ "/"+numStars);
            }
        });

        //GetRatingBar
         getRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                 //Decimal fromat for converting all floats into decimal
                 DecimalFormat decimalFormat=new DecimalFormat("#.#");

                 //Get Current rating
                 curRate=Float.valueOf(decimalFormat.format((curRate *count+rating) / ++count));

                 //showing a toast of current tasting
                 Toast.makeText(RatingbarActivity.this, "New rating : "+curRate,Toast.LENGTH_SHORT).show();

                 //Set the current rating to setRatingbar
                 setRatingBar.setRating(curRate);

                 //set the current count value
                 setCountText.setText(count+" Ratings ");
             }
         });
    }



}