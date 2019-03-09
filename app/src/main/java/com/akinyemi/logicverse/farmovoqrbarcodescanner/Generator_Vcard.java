package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator_Vcard extends AppCompatActivity {
    String vcardname,vcardcontact,vcardemail;
    EditText namevcard,contactvcard,emailvcard;
    String emailpattern="[a-zA-z0-9._-]+@[a-z]+.[a-z]+";
    String phonepattern="^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
    Pattern phonepatterncompile =Pattern.compile(phonepattern);
    String contact;
    Matcher ispohne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__vcard);
        namevcard=(EditText)findViewById(R.id.personnamevcard);
        contactvcard=(EditText)findViewById(R.id.contactnumbervcard);
        emailvcard=(EditText)findViewById(R.id.emailvcard);
        getSupportActionBar().hide();
    }
    //Generating QR code
    public void genvcard(View view) {
        if(valid())
        {
            contact="Name  :"+vcardname+"\n"+"Phone no  :"+vcardcontact+"\n"+"Email  :"+vcardemail;
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",contact);
            startActivity(i);
        }
    }
    //validation
    private boolean valid() {
        details();
        if(vcardname.isEmpty()) {
            namevcard.requestFocus();
            namevcard.setError("Please Enter A Name.");
            return false;
        }
        else if(vcardname.isEmpty()||!(ispohne.matches()))
        {
                    contactvcard.requestFocus();
                    contactvcard.setError("Please Enter Valid Phone Number");
                    return false;
        }
        else if(vcardcontact.isEmpty()) {
            contactvcard.requestFocus();
            contactvcard.setError("Please Enter Valid Phone Number");
            return false;
        }
        else if(!(vcardemail.matches(emailpattern))||vcardemail.isEmpty()) {
            emailvcard.requestFocus();
            emailvcard.setError("Please Enter Valid Phone Number");
            return false;
        }
        return true;
    }
    //Retriving Details
    private void details() {
        vcardname=namevcard.getText().toString().trim();
        vcardcontact=contactvcard.getText().toString().trim();
        ispohne=phonepatterncompile.matcher(vcardcontact);
        vcardemail=emailvcard.getText().toString().trim();

    }
}
