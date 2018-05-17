package com.qrscanner.zxing;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseQRScanner extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Toolbar
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
