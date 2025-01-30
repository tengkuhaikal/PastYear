package com.tmhta.pastyear;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView scoreTV;
    ImageButton mathsButton, englishButton;

    static int cumulativeScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTV = findViewById(R.id.totalScoreTV);
        mathsButton = findViewById(R.id.mathsButton);
        englishButton = findViewById(R.id.englishButton);

        mathsButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, MathActivity.class));
        });

        englishButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, EnglishActivity.class));
        });

        cumulativeScore += getIntent().getIntExtra("score", cumulativeScore);
        scoreTV.setText(String.valueOf(cumulativeScore));
    }
}