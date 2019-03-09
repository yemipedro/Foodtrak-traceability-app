package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator_PersonalDetails extends AppCompatActivity {
    String dataacrenumber,datafarmlocation,datacropname,datadateplanted,datapackUniNum,datadateharvested,datanotes;
    EditText acrenumberfd,farmlocfd,cropnamefd,dateplantedfd,packuninumfm,dateharvestedfd,notesfd;
    Pattern web= Patterns.EMAIL_ADDRESS;
    Pattern net= Patterns.WEB_URL;
    String alldata;
    Matcher m,m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__personal_details);

        acrenumberfd=(EditText)findViewById(R.id.acreNumber);
        getSupportActionBar().hide();
        farmlocfd=(EditText)findViewById(R.id.farmLocation);
        cropnamefd=(EditText)findViewById(R.id.cropName);
        dateplantedfd=(EditText)findViewById(R.id.datePlanted);
        packuninumfm=(EditText)findViewById(R.id.puNumber);
        dateharvestedfd=(EditText)findViewById(R.id.dateHarvested);
        notesfd=(EditText)findViewById(R.id.notes);
    }

    //Generationg QR Code
    public void genpersonaldetails(View view) {
        if(valid())
        {
            alldata="Acre Number :"+dataacrenumber+"\n"+"Farm Location :"+datafarmlocation+"\n"+"Crop Name :"+datacropname+"\n"+"Date Planted :"+datadateplanted+"\n"
            +"Package Unique Number :"+datapackUniNum+"\n"+"Date Harvested :"+datadateharvested+"\n"+"Notes :"+datanotes;
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",alldata);
            startActivity(i);
        }
    }
    //Validation
    private boolean valid() {
        details();
        /*m=web.matcher(datacropname);
        m2=net.matcher(datadateharvested);*/
        if(datafarmlocation.isEmpty())
        {
            farmlocfd.requestFocus();
            farmlocfd.setError("Please Enter Farm Location");
            return false;
        }
        else if(datacropname.isEmpty())
        {
            cropnamefd.requestFocus();
            cropnamefd.setError("please Enter Crop Nmae !");
            return false;
        }
        else if(datadateplanted.isEmpty()||datadateplanted.length()<4)
        {
            dateplantedfd.requestFocus();
            dateplantedfd.setError("Enter Valid Date Planted");
            return false;
        }
        else if(datapackUniNum.isEmpty()||datapackUniNum.length()<4)
        {
            packuninumfm.requestFocus();
            packuninumfm.setError("Please Enter Valid Package Unique Number !");
            return false;
        }
        else if (datadateharvested.isEmpty())
        {
            dateharvestedfd.requestFocus();
            dateharvestedfd.setError("Please Enter Date Harvested");
            return false;
        }
        return true;
    }
    //Retriving
    private void details() {
        dataacrenumber=acrenumberfd.getText().toString().trim();
        datafarmlocation=farmlocfd.getText().toString().trim();
        datacropname=cropnamefd.getText().toString().trim();
        datadateplanted=dateplantedfd.getText().toString().trim();
        datapackUniNum=packuninumfm.getText().toString().trim();
        datadateharvested=dateharvestedfd.getText().toString().trim();
        datanotes=notesfd.getText().toString().trim();
    }
}
