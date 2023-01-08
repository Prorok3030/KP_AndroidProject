package com.example.testappdb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin,edPassword;
    private Button bStart,bSighUp,bSignIn;
    private TextView tvUserEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            showSigned();
            String userEmail = "Вы вошли как: " + currentUser.getEmail();
            tvUserEmail.setText(userEmail);

        }
        else{
            showNotSigned();
        }
    }

    private void init(){
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        bStart = findViewById(R.id.bStart);
        bSighUp = findViewById(R.id.bSignUp);
        bSignIn = findViewById(R.id.bSignIn);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSignUp(View view){
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString()).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                showSigned();
                                sendEmailVerif();
                                Toast.makeText(LoginActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(this, "Введите адес почты и пароль", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickSignIn(View view){
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){
        mAuth.signInWithEmailAndPassword(edLogin.getText().toString(),edPassword.getText().toString()).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showSigned();
                            Toast.makeText(LoginActivity.this, "Успешная авторизация", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Ошибка при авторизации ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
        else{
            Toast.makeText(this, "Введите адес почты и пароль", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickSignOut(View view){
        FirebaseAuth.getInstance().signOut();
        showNotSigned();
    }

    public void onClickStart(View view){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void showSigned(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user.isEmailVerified()) {
            bStart.setVisibility(View.VISIBLE);
            tvUserEmail.setVisibility(View.VISIBLE);
            edLogin.setVisibility(View.GONE);
            edPassword.setVisibility(View.GONE);
            bSignIn.setVisibility(View.GONE);
            bSighUp.setVisibility(View.GONE);
        }
        else{
            Toast.makeText(this, "Для подтверждения адреса, проверьте почту", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNotSigned(){
        bStart.setVisibility(View.GONE);
        tvUserEmail.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
        bSighUp.setVisibility(View.VISIBLE);
    }

    private void sendEmailVerif(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Для подтверждения адреса, проверьте почту", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Ошибка при отправке", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
