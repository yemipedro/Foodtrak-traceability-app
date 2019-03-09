package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Generator_ProductNumber extends AppCompatActivity {
    EditText ed;
    String getprod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__product_number);
        ed=(EditText)findViewById(R.id.productgeneratednumber);
        getSupportActionBar().hide();
    }
    //Generating QR code
    public void genproductqr(View view) {
        if(valid())
        {
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",getprod);
            startActivity(i);
        }
    }
    //Validation
    private boolean valid() {
        details();
        if(getprod.isEmpty())
        {
            return false;
        }
        return true;
    }
    //Retring details
    private void details()
    {
        getprod=ed.getText().toString().trim();
    }
}
