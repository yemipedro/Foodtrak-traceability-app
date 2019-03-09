package com.akinyemi.logicverse.farmovoqrbarcodescanner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScannedDetails extends AppCompatActivity {
    TextView tv;
    String a,aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scannee_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Result");
        Bundle bd=getIntent().getExtras();
        tv=(TextView)findViewById(R.id.deailts);
        a=(bd.getString("hi"));
        char h[]=a.toCharArray();
        int index=0;
        char g[]=new char[h.length];
        for(int i=0;i<a.length()-1;i++)
        {
            if(h[i]==';'){
                g[index++]='\n';
                while(true){
                    if(h[i++]==' ');
                    else
                    {
                        if(h[i]!=' ')
                            i--;
                        break;
                    }

                }
            }
            else g[index++]=h[i];
        }
        g[index++]='\0';
        //Displaing the QRCODE Details
        aa=new String(g);
        tv.setText(a);
    }

    public void copymanager(View view) {
        //Copying the details for further use
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText("text",aa);
        clipboardManager.setPrimaryClip(clipData);
    }
}
