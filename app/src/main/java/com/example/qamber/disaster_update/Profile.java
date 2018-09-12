package com.example.qamber.disaster_update;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qamber.disaster_update.ModelClass.SharedPrefManager;

public class Profile extends AppCompatActivity {

    ImageView backBtn;
    ImageView logOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logOut  = (ImageView) findViewById(R.id.logout);
        backBtn = (ImageView) findViewById(R.id.backpress);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }


    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, Login.class));
    }
}
