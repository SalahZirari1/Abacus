package com.example.projectm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;


public class resultPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);


        TextView scoreResult = findViewById(R.id.scoreResult);
        TextView difficultyTv = findViewById(R.id.difficultyTv);
        databaseClass db = new databaseClass(this);
        ImageButton equationLogo = findViewById(R.id.equationsGameBtn);
        ImageButton compareLogo = findViewById(R.id.compareGameBtn);
        TextView gameNameHeaderTv = findViewById(R.id.gameNameHeaderTv);
        TextView topScoreTv = findViewById(R.id.topScoreTv);
        TextView precisionTv=findViewById(R.id.precisionTv);
        FloatingActionButton replayBtn = findViewById(R.id.replayBtn);
        FloatingActionButton menuFBtn = findViewById(R.id.menuFbutton);
        TextView accuracyTitleTv=findViewById(R.id.accuracyTitleTv);
        //final MediaPlayer breadSong = MediaPlayer.create(this, R.raw.bread);



        //get this one into a method
        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");

        //Styling
/*
        breadSong.start();
*/

        scoreResult.setText(String.valueOf(score));
        topScoreTv.setText(db.getHighestScore());
        difficultyTv.setText(difficulty());
        replayBtn.setColorFilter(Color.WHITE);
        menuFBtn.setColorFilter(Color.WHITE);
        if (isSprint()) {
            float precision =((float) score) / counter()*100;
            precisionTv.setText((int) precision + "%");
        }
        else{
            precisionTv.setVisibility(View.GONE);
            accuracyTitleTv.setVisibility(View.GONE);
        }

        if (gameName().equals("compare")) {
            gameNameHeaderTv.setText("Comparing Game");
            equationLogo.setVisibility(View.GONE);

        } else if (gameName().equals("equations")) {
            compareLogo.setVisibility(View.GONE);
        }


        //add score to db
        db.AddScore(String.valueOf(score), gameName(), "holder", difficulty());

        topScoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resultPage.this, scoresPage.class);
                startActivity(i);
            }
        });

    }

    // methods

    public String gameName() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("gameName");
    }

    public String difficulty() {
        Bundle extras = getIntent().getExtras();
        return extras.getString("difficultyForDb");
    }
    public boolean isSprint() {
        Bundle extras = getIntent().getExtras();
        return extras.getBoolean("isSprint",false);
    }
    public int counter() {
        Bundle extras = getIntent().getExtras();
        return extras.getInt("counter",0);
    }

    public void goToDifficulty(View view) {
        Intent i = new Intent(resultPage.this, difficultyPage.class);
        i.putExtra("gameName", gameName());
        startActivity(i);
    }

    public void goToGamesPage(View view) {
        Intent i = new Intent(resultPage.this, gamesPage.class);
        finish();
        startActivity(i);
    }

    public void StartGame(View view) {

        if (gameName().equals("compare")) {
            Intent i = new Intent(resultPage.this, compareGame.class);
            i.putExtra("difficultyOption", difficulty());
            i.putExtra("gameName", gameName());
            i.putExtra("isSprint",isSprint());
            startActivity(i);

        } else if (gameName().equals("equations")) {
            Intent i = new Intent(resultPage.this, equationsGame.class);
            i.putExtra("difficultyOption", difficulty());
            i.putExtra("gameName", gameName());
            i.putExtra("isSprint",isSprint());

            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}