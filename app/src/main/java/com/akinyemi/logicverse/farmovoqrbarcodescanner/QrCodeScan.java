package com.akinyemi.logicverse.farmovoqrbarcodescanner;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class QrCodeScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public static final int requestcamera=1;
    Menu mOptionsmenu;
    public boolean isflash;
    private List<BarcodeFormat> formats;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            //Checking camera access permission
            if(checkpermission());
            else{
                //Requesting camera access permission
                requestpermission();
            }
        }
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        mScannerView.getBackgroundTintMode();
        formats = new ArrayList<BarcodeFormat>();
        //setting the types barcode formats
        formats.add(BarcodeFormat.UPC_A);
        formats.add(BarcodeFormat.UPC_E);
        formats.add(BarcodeFormat.EAN_8);
        formats.add(BarcodeFormat.EAN_13);
        formats.add(BarcodeFormat.RSS_14);
        formats.add(BarcodeFormat.CODE_39);
        formats.add(BarcodeFormat.CODE_93);
        formats.add(BarcodeFormat.CODE_128);
        formats.add(BarcodeFormat.ITF);
        mScannerView.setBackground(getDrawable(R.drawable.acv));
        mScannerView.getMatrix();
        //setting the all types of qr code formats
        mScannerView.setFormats(ZXingScannerView.ALL_FORMATS);
        isflash=false;
        getSupportActionBar().setTitle("   Place code inside the frame");
    }

    private void requestpermission() {
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},requestcamera);
    }

    private boolean checkpermission() {
        return (ContextCompat.checkSelfPermission(QrCodeScan.this, CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case requestcamera :
                if(grantResults.length>0)
                {
                    boolean cameraac=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    //grantResult[0] stores the the boolean value if permission is granted
                    if(cameraac)
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(this, "Permission Denided", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)//Permissions for Marshmallow and higher versions
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                requestalert("You need to allow the camera access",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},requestcamera);
                                                }
                                            }
                                        });
                                break;
                            }
                        }
                    }
                }
                break;

        }
    }
    public void requestalert(String message, DialogInterface.OnClickListener listnser)
    {
        new AlertDialog.Builder(QrCodeScan.this)
                .setMessage(message)
                .setPositiveButton("OK",listnser)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsmenu = menu;
        getMenuInflater().inflate(R.menu.flashoptop,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.flashbutton)
        {
            if(!isflash)
            {
                //Enabling flash
                mScannerView.setFlash(true);
                mScannerView.requestFocus();
                isflash=true;
                mOptionsmenu.findItem(R.id.flashbutton).setIcon(R.drawable.ic_flash);
            }
            else if(isflash){
                //Disabling flash
                mScannerView.setFlash(false);
                mScannerView.clearFocus();
                isflash=false;
                mOptionsmenu.findItem(R.id.flashbutton).setIcon(R.drawable.ic_action_flashoff);
            }
        }
        return true;
    }

    @Override
    public void onPause() {
        //turn off flash when paused
        super.onPause();
        if(isflash)
                mScannerView.setFlash(false);
    }
    @Override

    public void onResume()
    {
        //Resuming the scanner and flash
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkpermission())
            {

            }
            else{
                requestpermission();
            }
        }
        super.onResume();
        if(isflash)
            mScannerView.setFlash(true);
        mScannerView.resumeCameraPreview(this);
    }
    @Override
    public void onDestroy()
    {
        //Destroying the camera when appllication is Destroyed
        super.onDestroy();
        mScannerView.stopCamera();
    }
    @Override
    public void handleResult(Result rawResult)
    {
        //Scanned Result of Code
        Intent i=new Intent(QrCodeScan.this,ScannedDetails.class);
        if(rawResult !=null && !rawResult.getText().toString().isEmpty()) {

            i.putExtra("hi", rawResult.getText());
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Scanning Calceled", Toast.LENGTH_SHORT).show();
        }
    }
}
