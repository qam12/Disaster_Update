package com.example.qamber.disaster_update;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton vitIm, hosP, donAt, settIng,profile,aboutUs;
    Button acc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vitIm = (FloatingActionButton) findViewById(R.id.vic);
        hosP = (FloatingActionButton) findViewById(R.id.hos);
        donAt = (FloatingActionButton) findViewById(R.id.donat);
        settIng = (FloatingActionButton) findViewById(R.id.sett);
        profile = (FloatingActionButton) findViewById(R.id.proID);
        aboutUs = (FloatingActionButton) findViewById(R.id.about);
        acc = (Button)  findViewById(R.id.login);

        vitIm.setOnClickListener(this);
        hosP.setOnClickListener(this);
        donAt.setOnClickListener(this);
        settIng.setOnClickListener(this);
        profile.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        acc.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.vic:
                Intent intent = new Intent(this, Victim.class);
                this.startActivity(intent);
                break;

            case R.id.hos:
                Intent i = new Intent(this, Hospital.class);
                this.startActivity(i);
                break;

            case R.id.donat:
                Intent d = new Intent(this, Donate.class);
                this.startActivity(d);
                break;

            case R.id.sett:
                Intent t = new Intent(this, Settings.class);
                this.startActivity(t);
                break;
            case R.id.proID:
                Intent c = new Intent(this, Profile.class);
                this.startActivity(c);
                break;
            case R.id.about:
                Intent r = new Intent(this, Team.class);
                this.startActivity(r);
                break;
            case R.id.login:
                Intent gh = new Intent(this, Login.class);
                this.startActivity(gh);
                break;
        }


    }
}
