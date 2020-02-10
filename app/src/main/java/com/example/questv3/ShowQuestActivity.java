package com.example.questv3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowQuestActivity extends AppCompatActivity {
    private static final String TAG = "ShowQuestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quest);

        TextView title,description,date;
        title = findViewById(R.id.questTitle);
        description = findViewById(R.id.questDescription);
        date = findViewById(R.id.questDate);

        Log.d(TAG, "onCreate: started.");
        if(getIntent().hasExtra("selected_quest")){
            QuestItem quest = getIntent().getParcelableExtra("selected_quest");
            title.setText(quest.getTitle());
            description.setText(quest.getContent());
            date.setText(quest.getDate());
            Log.d(TAG, "onCreate: " + quest.toString());
        }
    }
}
