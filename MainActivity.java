package com.myappcompany.keka.barcodescaner;

/**
 * Created by Keka on 01-09-2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class MainActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting barcode instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcodeFragment);
    }

    @Override
    public void onScanned(final Barcode barcode) {
        // single barcode scanned

        Log.i("value", "onScanned: DisplayValue: " +barcode.displayValue);
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               /* TextView scannedResultTextView = (TextView) findViewById(R.id.scannedResultTextView);
                scannedResultTextView.setText("Barcode: " +barcode.displayValue);*/

                Toast.makeText(getApplicationContext(), "Barcode: " +barcode.displayValue,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        // multiple barcodes scanned
        Log.e("info", "onScannedMultiple: " + barcodes.size());

        String codes = "";
        for (Barcode barcode : barcodes) {
            codes += barcode.displayValue + ", ";
        }

        final String finalCodes = codes;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();

                TextView scannedResultTextView = (TextView) findViewById(R.id.scannedResultTextView);
                scannedResultTextView.setText( "Barcodes: " + finalCodes);
            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        Log.i("info", "onBitmapScanned: ");
        // barcode scanned from bitmap image
    }

    @Override
    public void onScanError(String s) {
        Log.i("info", "onScanError: ");
        // scan error
    }

    @Override
    public void onCameraPermissionDenied() {
        // camera permission denied
        Toast.makeText(getApplicationContext(), "Camera permission denied",
                Toast.LENGTH_SHORT).show();
    }

}
