package com.example.qamber.disaster_update;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Choose extends AppCompatActivity {

    Button Infopro, Vol_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        Infopro = (Button) findViewById(R.id.info);
        Vol_ = (Button) findViewById(R.id.vol);

        Infopro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, Victim.class);
                startActivity(intent);
            }
        });

        Vol_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
