package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PandaPage extends AppCompatActivity {
    public static final String KEY_AS_PARAMETER = "com.example.beursalingual.KEY_AS_PARAMETER";

    // declaring menu buttons:
    private Button grizzlyButton;
    private Button polarButton;
    private Button pandaButton;

    // declaring lesson buttons:
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_page);

        // initializing menu buttons:
        grizzlyButton = findViewById(R.id.grizzly);
        polarButton = findViewById(R.id.polar);
        pandaButton = findViewById(R.id.panda);

        // initializing lesson buttons:
        btn1 = findViewById(R.id.panda_one);
        btn2 = findViewById(R.id.panda_two);
        btn3 = findViewById(R.id.panda_three);
        btn4 = findViewById(R.id.panda_four);

        // on-click listeners for menu buttons:
        grizzlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PandaPage.this, GrizzlyPage.class));
            }
        });
        polarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PandaPage.this, PolarPage.class));
            }
        });
        pandaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PandaPage.this, PandaPage.class));
            }
        });

        // on-click listeners for lesson buttons:
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(9);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(10);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(11);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(12);
            }
        });
    }

    // method to transition from lesson buttons to actual activity page (main)
    public void openMainActivity(int key) {
        Intent intent = new Intent (this, MainActivity.class);
        intent.putExtra(KEY_AS_PARAMETER, key);
        startActivity(intent);
    }
}