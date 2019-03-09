package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator_Weblink extends AppCompatActivity {
    EditText qrweblink;
    Pattern web= Patterns.WEB_URL;
    String qrlink;
    Matcher m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__weblink);
        qrweblink= (EditText) findViewById(R.id.weblinkdata);
        getSupportActionBar().hide();
    }
    //Generating QR code
    public void genweblink(View view) {
        if(validate())
        {
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",qrlink);
            startActivity(i);
        }
    }
    //Validation
    private boolean validate() {
        details();
        m=web.matcher(qrlink.toLowerCase());
        if(m.matches())
            return true;
        else {
            qrweblink.requestFocus();
            qrweblink.setError("Please Enter Valid weblink");
            return false;
        }
    }
    //Retriving the data
    private void details() {
        qrlink=qrweblink.getText().toString().trim();

    }
}
