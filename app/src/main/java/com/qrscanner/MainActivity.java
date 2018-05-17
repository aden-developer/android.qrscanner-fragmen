package com.qrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qrscanner.zxing.QRScanner;

public class MainActivity extends AppCompatActivity {
    //Inisialisasi
    final int ZXING_KAMERA_AKTIVITAS = 1;

    //Deklarasi
    Button scan;
    Class<?> ruang_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Inisialisasi
        scan = (Button) findViewById(R.id.scan);

        //Perintahkan untuk memulai menscan
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zxing_aktivitas(QRScanner.class);
            }
        });
    }

    private void zxing_aktivitas(Class<?> kelas) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ruang_kelas = kelas;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, ZXING_KAMERA_AKTIVITAS);
        } else {
            Intent intent = new Intent(this, kelas);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == ZXING_KAMERA_AKTIVITAS) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(ruang_kelas != null) {
                    Intent intent = new Intent(this, ruang_kelas);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Aktivitas tidak terhubung", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
