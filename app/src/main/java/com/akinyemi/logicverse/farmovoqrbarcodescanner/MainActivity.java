package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Action bar hide
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
    }

    public void scanning(View view) {
        //Scanning QR CODE Activity
        Intent i=new Intent(MainActivity.this,QrCodeScan.class);
        startActivity(i);
    }

    public void generating(View view) {
        //Generating QR CODE Activity
        Intent ii=new Intent(MainActivity.this,Generator_PersonalDetails.class);
        startActivity(ii);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Are you sure want to Exit the App?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Launch.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        }).setNegativeButton("No",null).show();
    }
}