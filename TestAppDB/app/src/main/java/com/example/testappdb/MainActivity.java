package com.example.testappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDataBase; //ссылка на БД
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
    }

    public void onClickStart(View view){
        Intent i = new Intent(MainActivity.this, BeginActivity.class);
        startActivity(i);
    }

    public void onClickAbout(View view){
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    public void onClickAboutTypes(View view){
        Intent i = new Intent(MainActivity.this, AboutTypesActivity.class);
        startActivity(i);
    }

    public void onClickAboutApp(View view){
        Intent i = new Intent(MainActivity.this, AboutAppActivity.class);
        startActivity(i);
    }
}