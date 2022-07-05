package com.example.projectm;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class compareGame extends AppCompatActivity {

    // fix bug that happens when you dont answer in sprint mode

    Button higher;
    Button lower;
    Button equal;
    TextView n1Tv;
    TextView n2Tv;
    TextView user_choice;
    Chronometer chronometerCountDown;
    TextView emojiTv;


    double num2;
    double num1;
    int counter = 0;
    double countDown;
    public int score = 0;
    public boolean verifier = false;
    Random random = new Random();
    boolean firstExecution = true;
    boolean alreadyExecuted = false;

//main algs
    private void onChronometerTickHandler() {

        if (!isSprint() && countDown < 0) {

            generateNumber();

        } else if (isSprint() && countDown == 1) { // end of activity sprint mode
            goToResult();
        }
        chronometerCountDown.setText(String.format("%.0f", countDown));
        countDown--;

    }

    public void generateNumber() {
        if (!firstExecution) {
            count(verifier);
        }
        if (isSprint()) {
            generateNumberSprintMode();

        } else {
            generateNumberPrecisionMode();
        }

        chronometerCountDown.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                onChronometerTickHandler();
            }
        });
        firstExecution = false;
        verifier = false;
    }

    public void generateNumberPrecisionMode() {
        if (difficulty().equals("easy")) {
            countDown = 20;
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);
        } else if (difficulty().equals("medium")) {
            countDown = 5;
            num1 = Math.ceil(Math.random() * 100);
            num2 = Math.ceil(Math.random() * 200 - 100);
        } else if (difficulty().equals("hard")) {

            countDown = 0.25;
            num1 = Math.ceil(Math.random() * 200 - 100);
            num2 = Math.ceil(Math.random() * 200 - 100);
        }

        n1Tv.setText(String.format("%.0f", num1));
        n2Tv.setText(String.format("%.0f", num2));

    }

    public void generateNumberSprintMode() {
        if (!alreadyExecuted) {


            if (difficulty().equals("easy")) {
                countDown = 60;
            } else if (difficulty().equals("medium")) {
                countDown = 30;

            } else if (difficulty().equals("hard")) {
                countDown = 10;
            }

            alreadyExecuted = true;
        }


        switch (difficulty()) {

            case "easy":
                num1 = random.nextInt(100);
                num2 = random.nextInt(100);
                break;

            case "medium":

                num1 = Math.ceil(Math.random() * 100);
                num2 = Math.ceil(Math.random() * 200 - 100);
                break;

            case "hard":

                num1 = Math.ceil(Math.random() * 200 - 100);
                num2 = Math.ceil(Math.random() * 200 - 100);
                break;
        }

        n1Tv.setText(String.format("%.0f", num1));
        n2Tv.setText(String.format("%.0f", num2));



    }

    public void count(boolean correct) {

        if (!isSprint()) {
            if (counter == 10)  // end of activity precision mode
            {
                goToResult();
            }
        }
        if (correct) {
            score++;
        }
        counter++;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        higher = findViewById(R.id.higher);
        lower = findViewById(R.id.lower);
        equal = findViewById(R.id.equal);
        user_choice = findViewById(R.id.user_choice);
        n1Tv = findViewById(R.id.nb1);
        n2Tv = findViewById(R.id.nb2);
        chronometerCountDown = findViewById(R.id.countdown);
        emojiTv = findViewById(R.id.emojiTv);
        Button quitBtn = findViewById(R.id.quitBtn);
        final MediaPlayer playCorrectSound = MediaPlayer.create(this, R.raw.answersound);
        //final MediaPlayer chocolateSong = MediaPlayer.create(this, R.raw.chocolate);
        //styling and display
        if (difficulty().equals("hard") && !isSprint()) {
            emojiTv.setText(getEmoji(lightning));
            chronometerCountDown.setTextSize(1);
            chronometerCountDown.setBackground(null);

            emojiTv.setTextColor(0xff000000);
        }
        //styling and display_
        //chocolateSong.start();
        generateNumber();
        chronometerCountDown.start();

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(compareGame.this,difficultyPage.class);
                i.putExtra("gameName",gameName());
                startActivity(i);
            }
        });
        higher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1 > num2) {
                    playCorrectSound.start();
                    verifier = true;
                    user_choice.setText(">");
                    user_choice.setTextColor(Color.parseColor("#00FF00"));
                } else {
                    user_choice.setText(">");
                    user_choice.setTextColor(Color.parseColor("#FF0000"));
                }
                generateNumber();

            }

        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1 == num2) {
                    playCorrectSound.start();
                    verifier = true;
                    user_choice.setText("=");
                    user_choice.setTextColor(Color.parseColor("#00FF00"));
                } else {
                    user_choice.setText("=");
                    user_choice.setTextColor(Color.parseColor("#FF0000"));
                }
                generateNumber();
            }

        });
        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1 < num2) {
                    playCorrectSound.start();
                    verifier = true;
                    user_choice.setText("<");
                    user_choice.setTextColor(Color.parseColor("#00FF00"));
                } else {
                    user_choice.setText("<");
                    user_choice.setTextColor(Color.parseColor("#FF0000"));
                }
                generateNumber();
            }
        });

    }

    //emoji
    public String getEmoji(int uni) {
        return new String(Character.toChars(uni));
    }

    int lightning = 0x26A1;
    //emojis_

    //getters and methods

    public void goToResult() {

        Intent i = new Intent(compareGame.this, resultPage.class);
        i.putExtra("score", score);
        i.putExtra("difficultyForDb", difficulty());
        i.putExtra("gameName", gameName());
        i.putExtra("counter", counter);
        i.putExtra("isSprint",isSprint());
        finish();
        startActivity(i);
    }

    public String difficulty() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("difficultyOption");
    }

    public String gameName() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("gameName");
    }

    public boolean isSprint() {
        Bundle extras = getIntent().getExtras();
        return extras.getBoolean("isSprint");
    }
    //getters_
}