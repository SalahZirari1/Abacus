package com.example.projectm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class difficultyPage extends AppCompatActivity {
    String difficulty;
    Boolean isSprint = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_page);

        ImageButton easy = findViewById(R.id.easy);
        ImageButton medium = findViewById(R.id.medium);
        ImageButton hard = findViewById(R.id.hard);
        ImageButton compareLogo = findViewById(R.id.compareGameBtn);
        ImageButton equationLogo = findViewById(R.id.equationsGameBtn);
        Button quitBtn = findViewById(R.id.quitBtn);
        Button ScoresButton = findViewById(R.id.ScoresButton);
        Switch switchBtn = findViewById(R.id.switch1);

       /* final MediaPlayer onionSong = MediaPlayer.create(this, R.raw.onion);

        onionSong.start();*/

        //styling
        if (gameName().equals("compare")) {
            equationLogo.setVisibility(View.GONE);
        } else if (gameName().equals("equations")) {
            compareLogo.setVisibility(View.GONE);
        }
        //styling_

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchBtn.isChecked()) {
                    isSprint = true;
                }
                if (!switchBtn.isChecked()) {
                    isSprint = false;
                }
            }
        });


        ScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(difficultyPage.this, scoresPage.class);
                startActivity(i);
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(difficultyPage.this, gamesPage.class);
                startActivity(i);
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyMode();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediumMode();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HardMode();
            }
        });

    }


    private void StartGame() {

        if (gameName().equals("compare")) {
            Intent i = new Intent(difficultyPage.this, compareGame.class);
            i.putExtra("difficultyOption", difficulty);
            i.putExtra("gameName", gameName());
            i.putExtra("isSprint", isSprint);
            startActivity(i);
        } else if (gameName().equals("equations")) {
            Intent i = new Intent(difficultyPage.this, equationsGame.class);
            i.putExtra("difficultyOption", difficulty);
            i.putExtra("gameName", gameName());
            i.putExtra("isSprint", isSprint);
            startActivity(i);
        }

    }


    private void EasyMode() {
        difficulty = "easy";
        StartGame();
    }

    private void MediumMode() {
        difficulty = "medium";
        StartGame();
    }

    private void HardMode() {
        difficulty = "hard";
        StartGame();
    }

    public String gameName() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("gameName");
    }
    public double precision() {
        Bundle extras = getIntent().getExtras();
        return extras.getDouble("precision");
    }

}


