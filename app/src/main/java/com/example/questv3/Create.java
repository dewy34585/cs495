package com.example.questv3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Create extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "Create";
    private EditText title, description;
    private TextView date;
    int icon;
    QuestBase mData;
    DatabaseReference databaseQuests;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        title = findViewById(R.id.editTitle);
        description = findViewById(R.id.editDescription);
        date = findViewById(R.id.date2);
        mData = QuestBase.getInstance();
        icon = R.mipmap.ic_launcher_round;

        databaseQuests = FirebaseDatabase.getInstance().getReference("questLists");

        Calendar c = Calendar.getInstance();
        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
        date.setText(currentDateString);

        Button button = findViewById(R.id.btnChooseDate);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.radioBtnRed:
                if (checked)
                icon = R.drawable.ic_ball_red;
                break;
            case R.id.radioBtnBlue:
                if (checked)
                icon = R.drawable.ic_ball_blue;
                break;
            case R.id.radioBtnGreen:
                if (checked)
                icon = R.drawable.ic_ball_green;
                break;
            default:
                break;
        }
    }

    public void submitQuest(View view) {
        if(title.getText().toString().isEmpty()) {
            title.setError("Required Field");
        }
        else {
            Log.d(TAG, "submitQuest: clicked");
            String qTitle, qDesc, qDate;
            qTitle = title.getText().toString();
            qDesc = description.getText().toString();
            qDate = date.getText().toString();
            QuestItem sending = new QuestItem(qTitle, qDesc, qDate, icon);
            mData.add(sending);

            //if(mData.getIdentification() != null){
                databaseQuests.child(mData.getIdentification()).setValue(mData.getData());
            //}

            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("created_quest", sending); //don't worry about this
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
        date.setText(currentDateString);
    }
}
