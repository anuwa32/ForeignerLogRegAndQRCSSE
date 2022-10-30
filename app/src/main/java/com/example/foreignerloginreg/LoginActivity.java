package com.example.foreignerloginreg;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText ForeignerEmail;
    TextInputEditText ForeignerPassword;
    TextView ForeignerRegister;
    Button btnLogin;
    FirebaseAuth fBAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ForeignerEmail = findViewById(R.id.ForeignerEmail);
        ForeignerPassword = findViewById(R.id.ForeignerPassword);
        ForeignerRegister = findViewById(R.id.ForeignerRegister);
        btnLogin = findViewById(R.id.btnLogin);

        fBAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        ForeignerRegister.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String email = ForeignerEmail.getText().toString();
        String password = ForeignerPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            ForeignerEmail.setError("Email cannot be empty field");
            ForeignerEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            ForeignerPassword.setError("Password cannot be empty field");
            ForeignerPassword.requestFocus();

        }else{
            fBAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "You are logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}