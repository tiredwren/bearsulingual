package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class GrizzlyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button buttonGenerateSpeech;
        EditText editText;
        TextToSpeech t1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grizzly_page);
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR)
                    t1.setLanguage(Locale.CANADA);
            }
        });

        buttonGenerateSpeech = findViewById()

        // (n) = number
        // (t): grizzly=1, polar=2, panda=3
        // each (grizzly, polar, panda) have five sections in them rn (can add more later)

        // if button(n) is pressed, go to a page and open data from column (t*n)
        // list input boxes out in a recyclerview, with a play button on the left side
        // (r) = number in recyclerview
        // when button is pressed, text-to-speech speaking out word in column (t*n), row (r)
        // does user input match what is written in column (t*n)?
    }
}