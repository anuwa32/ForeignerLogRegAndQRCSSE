package com.example.foreignerloginreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth fBAuth;
    Button btnQrGen;
    private Button genQRCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genQRCode = findViewById(R.id.idBtnGenerate);
        btnLogOut = findViewById(R.id.btnLogout);
       // btnQrGen = findViewById(R.id.btnQrGen);
        fBAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            fBAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

//        btnQrGen.setOnClickListener(view ->{
//            fBAuth.signOut();
//            startActivity(new Intent(MainActivity.this, HomeActivity.class));
//        });

        genQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,GenerateQRCodeActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fBAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}