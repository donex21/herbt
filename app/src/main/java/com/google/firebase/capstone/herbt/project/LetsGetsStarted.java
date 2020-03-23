package com.google.firebase.capstone.herbt.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetsGetsStarted extends AppCompatActivity {
    private Button skipbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_gets_started);
        skipbtn1 = (Button)findViewById(R.id.skipbtn);

        skipbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LetsGetsStarted.this, HomeActivity.class);
                startActivity(intent);

            }
        });
    }
}
