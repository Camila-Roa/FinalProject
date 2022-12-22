package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private TextView redirectReg;

    private String email = "";
    private String passwordv = "";

    private FirebaseAuth mAuth;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            username = (EditText) findViewById(R.id.usernamer);
            password = (EditText) findViewById(R.id.passwordr);
            btnLogin = (Button) findViewById(R.id.btnLogin);
            redirectReg = (TextView) findViewById(R.id.ingresReg);

            redirectReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(activity_login.this, RegisterActivity.class));
                    finish();
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email = username.getText().toString();
                    passwordv  = password.getText().toString();
                    mAuth = FirebaseAuth.getInstance();

                    if(!email.isEmpty() && !passwordv.isEmpty()){
                        loginUser();
                    }else{
                        Toast.makeText(activity_login.this,"Complete los campos",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    public void loginUser(){
        mAuth.signInWithEmailAndPassword(email,passwordv).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent =  new Intent(getApplicationContext(), Catalogo.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(activity_login.this,"No se ha iniciado sesión",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}