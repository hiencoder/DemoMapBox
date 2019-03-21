package com.example.hiennv.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vietbando.vietbandosdk.Vietbando;
import com.vietbando.vietbandosdk.maps.MapView;
import com.vietbando.vietbandosdk.maps.OnMapReadyCallback;
import com.vietbando.vietbandosdk.maps.VietbandoMap;

public class Main2Activity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Vietbando.getInstance(this, getResources().getString(R.string.access_token));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(VietbandoMap VietbandoMap) {

                // Customize map with markers, polylines, etc.
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
