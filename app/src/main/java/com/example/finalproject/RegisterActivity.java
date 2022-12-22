package com.example.finalproject;

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
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password, repassword;
    private EditText email;
    private TextView ingresoLog;
    private Button btnRegister;

    private String name = "";
    private String passwo = "";
    private String repasswo = "";
    private String mail = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        username = (EditText) findViewById(R.id.usernamer);
        password = (EditText) findViewById(R.id.passwordr);
        email = (EditText) findViewById(R.id.emailr);
        ingresoLog = (TextView) findViewById(R.id.ingresoLog);
        repassword = (EditText) findViewById(R.id.repasswordr);
        btnRegister = (Button) findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                passwo = password.getText().toString();
                repasswo = repassword.getText().toString();
                mail = email.getText().toString();

                if(!name.isEmpty() && !passwo.isEmpty() && !repasswo.isEmpty() && !mail.isEmpty()){
                    if( !passwo.equals(repasswo)){
                        Toast.makeText(RegisterActivity.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    }else{
                        registerUser();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ingresoLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,activity_login.class));
                finish();
            }
        });


    }
    public void registerUser (){
        mAuth.createUserWithEmailAndPassword(mail,passwo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",mail);
                    map.put("password",passwo);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(RegisterActivity.this,activity_login.class));
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"No se pudieron crear los datos correctamente",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }else{
                    Toast.makeText(RegisterActivity.this,"No se puedo registrar este usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}