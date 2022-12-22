package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class info extends AppCompatActivity {
    private Button btnInfo;
    private TextView textNameInfo, descriptionInfo, priceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnInfo = (Button) findViewById(R.id.btnInfo);
        textNameInfo = (TextView) findViewById(R.id.txtNameInfo);
        descriptionInfo = (TextView) findViewById(R.id.descriptionInfo);
        priceInfo = (TextView) findViewById(R.id.priceInfo);

        Intent intentIN = getIntent();
        textNameInfo.setText(intentIN.getStringExtra("name"));
        descriptionInfo.setText(intentIN.getStringExtra("description"));
        priceInfo.setText(intentIN.getStringExtra("price"));

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Catalogo.class);
                startActivity(intent);
            }
        });

    }
}