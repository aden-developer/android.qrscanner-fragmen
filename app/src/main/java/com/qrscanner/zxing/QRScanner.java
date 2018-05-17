package com.qrscanner.zxing;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.Result;
import com.qrscanner.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScanner extends BaseQRScanner implements ZXingScannerView.ResultHandler {
    //Inisialisasi
    private static final String FLASH_STATE = "FLASH_STATE";

    //Deklarasi
    private ZXingScannerView scan;
    private boolean status_penerangan;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.scanner);
        //setupToolbar();
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        scan = new ZXingScannerView(this);
        contentFrame.addView(scan);
    }

    @Override
    public void onResume() {
        super.onResume();
        scan.setResultHandler(this);
        scan.setAspectTolerance(0.2f);
        scan.startCamera();
        scan.setFlash(status_penerangan);
    }

    @Override
    public void onPause() {
        super.onPause();
        scan.stopCamera();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLASH_STATE, status_penerangan);
    }

    //Pengeluaran
    @Override
    public void handleResult(Result rawResult) {
        TextView hasil = (TextView) findViewById(R.id.hasil);
        //Tampilkan hasil
        hasil.setText(rawResult.getText());
        //Toast.makeText(this, "Contents = " + rawResult.getText()", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scan.resumeCameraPreview(ScalingScannerActivity.this);
            }
        }, 2000);*/
    }

    //Hidup/Mati lampu penerangan
    public void penerangan(View v) {
        status_penerangan = !status_penerangan;
        scan.setFlash(status_penerangan);
    }
}