package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Launch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        else {
        new CountDownTimer(2500,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                final Intent i=new Intent(Launch.this,MainActivity.class);
                startActivity(i);
            }
        }.start();
    }
    }
}
