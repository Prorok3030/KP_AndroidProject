package com.example.testappdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private final KeyService keyService = new KeyService();

    //private int points = 0;
    private boolean choose;
    private String question;
    public static List<String> list;
    private int index = 0;
    private int count = 1;

    private TextView tvQuestion,tvCount;
    private DatabaseReference mDataBase; //ссылка на БД
    private String DB_KEY = "Question";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        list = new ArrayList<>();
        init();
        getDataFromDB();
    }

    private void init(){
        tvQuestion = findViewById(R.id.tvQuestion);
        tvCount = findViewById(R.id.tvCount);

        Bundle mode = getIntent().getExtras();
        if(mode.getBoolean("mode")){
            DB_KEY = "QuestionChild";
        }
        mDataBase = FirebaseDatabase.getInstance().getReference(DB_KEY);
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        question = datasnapshot.getValue(String.class);
                        list.add(question);
                    }
                    tvQuestion.setText(list.get(index));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReadActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
            }
        };

        mDataBase.addValueEventListener(vListener);
    }

    public void onClickYes(View view){
        choose = true;
        keyService.AddPoints(index,choose);
        if (index == 87){
            Intent i = new Intent(ReadActivity.this, ResultActivity.class);
            startActivity(i);
        }
        else {
            index = index + 1;
            count = count +1;
            tvCount.setText(count + "/88");
            tvQuestion.setText(list.get(index));
        }
    }
    public void onClickNo(View view){
        choose = false;
        keyService.AddPoints(index,choose);
        if (index == 87){
            Intent i = new Intent(ReadActivity.this, ResultActivity.class);
            startActivity(i);
        }
        else {
            index = index + 1;
            count = count +1;
            tvCount.setText(count + "/88");
            tvQuestion.setText(list.get(index));
        }
    }

}
