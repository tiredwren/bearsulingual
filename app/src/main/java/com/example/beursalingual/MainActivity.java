package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {

    // Adapter Class declarations:
    RecyclerView recyclerView;
    Adapter adapter;
    AsyncHttpClient client;
    Workbook workbook;
    ArrayList<String> arrayList = new ArrayList<>();

    // declaring menu buttons:
    private Button grizzlyButton;
    private Button polarButton;
    private Button pandaButton;

    // tts:
    private TextToSpeech textToSpeech;
    private TextView editText;
    private Button button;
    private SeekBar seekBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int keyNumber = intent.getIntExtra(GrizzlyPage.KEY_AS_PARAMETER, 0);

        // tts instantiations:
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        System.out.println("not available");
                    } else {
                        button.setEnabled(true);
                    }
                }
            }
        });

        editText = findViewById(R.id.textInputToCheck);
        seekBar = findViewById(R.id.seek_bar_speed);

        // adding xls url (excel spreadsheet database) to code (from github)
        String url = "https://github.com/tiredwren/bearsulingual/raw/main/hackathon.xls";

        // Adapter Class initializations:
        recyclerView = findViewById(R.id.recyclerview);


        // initializing menu buttons:
        grizzlyButton = findViewById(R.id.grizzly);
        polarButton = findViewById(R.id.polar);
        pandaButton = findViewById(R.id.panda);

        // on-click listeners for menu buttons:
        grizzlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GrizzlyPage.class));
            }
        });
        polarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PolarPage.class));
            }
        });
        pandaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PandaPage.class));
            }
        });

        // setting headers for each section / text for each button
        TextView textView = findViewById(R.id.heading);
//        textView.setOnClickListener(view -> {
//            speak(keyNumber);
//        });
        String newHeading = "";
        if (keyNumber <= 4) {
            newHeading = "communication basics";
            if (keyNumber == 1) {
                newHeading = newHeading + ": greetings";
            }
            if (keyNumber == 2) {
                newHeading = newHeading + ": prepositions and conjunctions";
            }
            if (keyNumber == 3) {
                newHeading = newHeading + ": colors";
            }
            if (keyNumber == 4) {
                newHeading = newHeading + ": animals";
            }
        }
        if (keyNumber > 4 && keyNumber <= 8) {
            newHeading = "novice communicator";
            if (keyNumber == 5) {
                newHeading = newHeading + ": conversation starters";
            }
            if (keyNumber == 6) {
                newHeading = newHeading + ": gratitude";
            }
            if (keyNumber == 7) {
                newHeading = newHeading + ": questions";
            }
            if (keyNumber == 8) {
                newHeading = newHeading + ": simple sentences";
            }
        }
        if (keyNumber > 8 && keyNumber <= 12) {
            newHeading = "intermediate communication";
            if (keyNumber == 9) {
                newHeading = newHeading + ": verbs";
            }
            if (keyNumber == 10) {
                newHeading = newHeading + ": longer sentences";
            }
            if (keyNumber == 11) {
                newHeading = newHeading + ": colloquialisms";
            }
            if (keyNumber == 12) {
                newHeading = newHeading + ": sentence stems";
            }
        }

        textView.setText(newHeading);

        // Adapter Class adding from excel to code
        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this, "We're sorry, the download failed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(MainActivity.this, "Data has been successfully loaded!", Toast.LENGTH_SHORT).show();
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file != null) {
                    try {
                        workbook = Workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int i = 0; i < 25; i++) {
                            arrayList.add("\n" + sheet.getCell(keyNumber-1, i).getContents());
                        }
//
                        showData();
//
                    } catch (IOException | BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    private void showData() {
        adapter = new Adapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void speak(int keyNumber) {
        float speed = (float) seekBar.getProgress() / 50;
        if (speed < 0.1) {
            speed = 0.1f;
        }
        textToSpeech.setSpeechRate(speed);
        for (int i = 0; i < arrayList.size(); i++) {
            textToSpeech.speak(arrayList.get(i), TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}