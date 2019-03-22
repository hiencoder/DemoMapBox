package com.example.hiennv.demo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements OnMapReadyCallback {
    protected MapboxMap mMap;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initEvents();

    protected abstract void onMapCallBack(MapboxMap mapboxMap);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initEvents();
    }

    /**
     * @param mapboxMap
     */
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mMap = mapboxMap;
        onMapCallBack(mMap);
    }

}
