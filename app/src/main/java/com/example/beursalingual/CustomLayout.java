package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class CustomLayout extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private TextView editText;
    private SeekBar seekBar;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

//        Intent intent = getIntent();
//        String wordPosition = intent.getStringExtra(Adapter.POSITION_OF_WORD);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        editText = findViewById(R.id.textInputToCheck);
        seekBar = findViewById(R.id.seek_bar_speed);

//        speak(wordPosition);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("theWord")) {
            String word = getIntent().getStringExtra("theWord");
            speak(word);
        }
    }

    private void testWord (String word, String inputtedWord) {
        if (word.toLowerCase(Locale.ROOT).equals(inputtedWord.toLowerCase())){
//            editText.
        }
    }

    public void speak(String word) {
        float speed = (float) seekBar.getProgress() / 50;
        if (speed < 0.1) {
            speed = 0.1f;
        }
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }
}