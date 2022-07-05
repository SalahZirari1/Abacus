package com.example.projectm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class equationsGame extends AppCompatActivity {
    TextView n1Tv;
    TextView n2Tv;
    FloatingActionButton validateBtn;
    EditText userChoiceEquation;
    Button quitBtn;
    TextView correctIcon;
    Chronometer chronometerCountDown;


    double num2;
    double num1;
    int counter = 0;
    double countDown;
    public int score = 0;
    public boolean verifier = true;
    Random random = new Random();
    boolean firstExecution = true;
    boolean alreadyExecuted = false;



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
            countDown = 60;
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);
        } else if (difficulty().equals("medium")) {
            countDown = 30;
            num1 = Math.ceil(Math.random() * 100);
            num2 = Math.ceil(Math.random() * 200 - 100);
        } else if (difficulty().equals("hard")) {

            countDown = 10;
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
                countDown = 60;

            } else if (difficulty().equals("hard")) {
                countDown = 40;
            }

            alreadyExecuted = true;
        }


        switch (difficulty()) {

            case "easy":
                num1 = random.nextInt(20);
                num2 = random.nextInt(10);
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

        //serves as the game finisher in sprintMode


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
        setContentView(R.layout.activity_equations_game);


        n1Tv = findViewById(R.id.nb1Equation);
        n2Tv = findViewById(R.id.nb2Equation);
        userChoiceEquation = findViewById(R.id.userChoiceEquation);
        validateBtn = findViewById(R.id.validateBtn);
        correctIcon = findViewById(R.id.correctIcon);
        quitBtn = findViewById(R.id.quitBtn);
        chronometerCountDown =findViewById(R.id.countdown);

        validateBtn.setColorFilter(Color.WHITE);
        final MediaPlayer playCorrectSound = MediaPlayer.create(this, R.raw.answersound);

        userChoiceEquation.setText("");
        generateNumber();
        chronometerCountDown.start();

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(equationsGame.this,difficultyPage.class);
                i.putExtra("gameName",gameName());
                startActivity(i);
            }
        });

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if (num1 + num2 == Double.parseDouble(userChoiceEquation.getText().toString())) {
                        playCorrectSound.start();
                        verifier = true;
                        correctIcon.setText("✓");
                        correctIcon.setTextColor(Color.parseColor("#00FF00"));

                    }
                    if (Double.parseDouble(userChoiceEquation.getText().toString()) != num1 + num2 )
                    {
                        correctIcon.setText("X");
                        correctIcon.setTextColor(Color.parseColor("#FF0000"));
                    }
                }
                catch(NumberFormatException ex) {
                    correctIcon.setText("X");
                    correctIcon.setTextColor(Color.parseColor("#FF0000"));
                }
                finally {
                    count(verifier);
                    userChoiceEquation.setText("");
                    generateNumber();

                }


            }
        });

    }

    //getters and methods

    public void goToResult() {

        Intent i = new Intent(equationsGame.this, resultPage.class);
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}