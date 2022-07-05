package com.example.projectm;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class gamesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_games_page);
        ImageButton compareGameBtn = findViewById(R.id.compareGameBtn);
        ImageButton equationsGameBtn = findViewById(R.id.equationsGameBtn);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView9 = findViewById(R.id.textView9);
        //final MediaPlayer balloonSong = MediaPlayer.create(this, R.raw.balloon);
        /*if(!balloonSong.isPlaying()) {
            balloonSong.start();

        }*/

//emojis
        int pointUp = 0x1F446;
        int pointDown = 0x1F447;
        textView9.setText(getEmoji(pointDown));
        textView9.setTextColor(0xff000000);
        textView2.setText(getEmoji(pointUp));
        textView2.setTextColor(0xff000000);
//_emojis
        compareGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(gamesPage.this, difficultyPage.class);
                j.putExtra("gameName", "compare");
                startActivity(j);
            }
        });
        equationsGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(gamesPage.this, difficultyPage.class);
                i.putExtra("gameName", "equations");
                startActivity(i);
                //balloonSong.stop();
                //balloonSong.release();

            }
        });


    }
    public String getEmoji(int uni) {
        return new String(Character.toChars(uni));
    }

}

