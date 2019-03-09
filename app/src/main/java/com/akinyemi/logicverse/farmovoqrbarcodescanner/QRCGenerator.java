package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class QRCGenerator extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_qrcgenerator);
        getSupportActionBar().setTitle("Generator");
    }
    public void plaintextdata(View view) {
        Intent i=new Intent(this,Generator_PlainText.class);
        startActivity(i);
    }

    public void vcarddata(View view) {

        Intent i1 = new Intent(this,Generator_Vcard.class);
        startActivity(i1);
    }

    public void weblnkdata(View view) {
        Intent i2 = new Intent(this,Generator_Weblink.class);
        startActivity(i2);
    }

    public void productnumber(View view) {
        Intent i2 = new Intent(this,Generator_ProductNumber.class);
        startActivity(i2);
    }

    public void personaldetails(View view) {
        Intent i3 = new Intent(this,Generator_PersonalDetails.class);
        startActivity(i3);
    }
}
