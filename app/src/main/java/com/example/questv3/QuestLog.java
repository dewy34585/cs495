package com.example.questv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

public class QuestLog extends AppCompatActivity implements QuestAdapter.OnQuestListener{
    private static final String TAG = "QuestLog";
    RecyclerView questRecyclerView;
    QuestAdapter questAdapter;
    QuestBase mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        android.util.Log.d(TAG, "onCreate: Started");
        questRecyclerView = findViewById(R.id.log_rv);
        android.util.Log.d(TAG, "onCreate: Got RV");
        mData = QuestBase.getInstance();
        android.util.Log.d(TAG, "onCreate: Got QuestBase");
        questAdapter = new QuestAdapter(this,mData.getLogData(),this);
        questRecyclerView.setAdapter(questAdapter);
        questRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onQuestClick(int position) {
        android.util.Log.d(TAG, "onQuestClick: clicked");
        mData.getLog(position);
        Intent intent = new Intent(this, ShowQuestActivity.class);
        intent.putExtra("selected_quest",mData.getLog(position));
        startActivity(intent);
    }
}
