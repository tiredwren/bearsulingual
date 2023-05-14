package com.example.beursalingual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GrizzlyPage extends AppCompatActivity {

    Button grizzBut;
    Button polBut;
    Button panBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        grizzBut = findViewById(R.id.grizzly);
        polBut = findViewById(R.id.polar);
        panBut = findViewById(R.id.panda);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grizzly_page);

        grizzBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GrizzlyPage.this, GrizzlyPage.class));
            }
        });
    }
}