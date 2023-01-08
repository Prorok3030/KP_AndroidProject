package com.example.testappdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BeginActivity extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener{

    private Switch switchMode;
    private TextView tvDescription;
    public boolean childMode = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_layout);
        init();

        if (switchMode != null){
            switchMode.setOnCheckedChangeListener(this);
        }
    }

    public void init(){
        switchMode = findViewById(R.id.switchMode);
        tvDescription = findViewById(R.id.tvDescription);
    }

    public void onClickStart(View view){
        Intent i = new Intent(BeginActivity.this,ReadActivity.class);
        i.putExtra("mode", childMode);
        startActivity(i);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            tvDescription.setText("Тебе предлагается ответить на ряд вопросов, касающихся различных сторон твоей личности.\n" +
                    "Все ответы равноценны, правильных или неправильных среди них нет.\n" +
                    "Отвечай быстро, долго не задумывайся.");
            childMode = true;
        }
        else{
            tvDescription.setText("Вам предлагается ответить на ряд вопросов, касающихся различных сторон вашей личности.\n" +
                            "Если вы согласны с утверждением, отвечайте «да». Если вы не согласны, отвечайте «нет» \n" +
                    "Не раздумывайте над вопросами долго, отвечайте так, как вам кажется в настоящий момент.");
            childMode = false;
        }
    }
}
