package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Generator_PlainText extends AppCompatActivity {
    EditText plain;
    String finalplain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator__plain_text);
        plain=(EditText)findViewById(R.id.plaintextinput);
        getSupportActionBar().hide();

    }
    //Generatin QR code
    public void genplaintext(View view) {
        if(valid())
        {
            Intent i=new Intent(this,GeneratedDetails.class);
            i.putExtra("value",finalplain);
            startActivity(i);
        }
    }
    //Validation
    public boolean valid()
    {
        inputplain();
        if(finalplain.isEmpty()) {
            plain.requestFocus();
            plain.setError("Please Enter Text !");
            return false;
        }
        return true;
    }
    //Retrive the details
    private void inputplain() {
        finalplain=plain.getText().toString();
    }
}
