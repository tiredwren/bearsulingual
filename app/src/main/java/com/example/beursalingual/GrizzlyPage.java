package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GrizzlyPage extends AppCompatActivity {

    private Button grizzlyButton;
    private Button polarButton;
    private Button pandaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        grizzlyButton = findViewById(R.id.grizzly);
        polarButton = findViewById(R.id.polar);
        pandaButton = findViewById(R.id.panda);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grizzly_page);

        grizzlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GrizzlyPage.this, GrizzlyPage.class));
            }
        });
        polarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GrizzlyPage.this, PolarPage.class));
            }
        });
        pandaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GrizzlyPage.this, PandaPage.class));
            }
        });
    }
}