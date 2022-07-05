package com.example.projectm;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Hashtable;

public class scoresPage extends AppCompatActivity {
    ArrayList<Hashtable<String, String>> L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_page);

        ListView ScoresList = findViewById(R.id.ScoresList);
        Button quitBtn = findViewById(R.id.quitBtn);

        databaseClass db = new databaseClass(this);
        L = db.getScoreInfos();
        SimpleAdapter ad = new SimpleAdapter(this,
                L,
                R.layout.lstv_layout,
                new String[]{"score", "game", "difficulty"},
                new int[]{R.id.ScoreInList, R.id.NameInList, R.id.DifficultyInList});


        ScoresList.setAdapter(ad);


        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}


