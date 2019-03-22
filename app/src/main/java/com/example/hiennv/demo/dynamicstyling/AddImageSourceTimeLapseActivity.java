package com.example.hiennv.demo.dynamicstyling;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.hiennv.demo.R;
import com.example.hiennv.demo.base.BaseActivity;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngQuad;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.ImageSource;

import butterknife.BindView;

public class AddImageSourceTimeLapseActivity extends BaseActivity {
    @BindView(R.id.map_view)
    MapView mapView;
    private Handler handler;
    private Runnable runnable;
    private static final String ID_IMAGE_SOURCE = "animated_image_source";
    private static final String ID_IMAGE_LAYER = "animated_image_layer";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hill_shade_layer;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onMapCallBack(MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.DARK, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                style.addSource(new ImageSource(ID_IMAGE_SOURCE,
                        new LatLngQuad(
                                new LatLng(46.437, -80.425),
                                new LatLng(46.437, -71.516),
                                new LatLng(37.936, -71.516),
                                new LatLng(37.936, -80.425)),
                        R.drawable.southeast_radar_0));

                // Add layer
                style.addLayer(new RasterLayer(ID_IMAGE_LAYER, ID_IMAGE_SOURCE));

                // Loop the GeoJSON refreshing
                handler = new Handler();
                runnable = new RefreshImageRunnable(handler, style);
                handler.postDelayed(runnable, 100);
            }
        });
    }

    private static class RefreshImageRunnable implements Runnable {
        private final Style loadedMapStyle;
        private final Handler handler;
        private int[] drawables;
        private int drawableIndex;

        RefreshImageRunnable(Handler handler, Style loadedMapStyle) {
            this.handler = handler;
            this.loadedMapStyle = loadedMapStyle;
            drawables = new int[4];
            drawables[0] = R.drawable.southeast_radar_0;
            drawables[1] = R.drawable.southeast_radar_1;
            drawables[2] = R.drawable.southeast_radar_2;
            drawables[3] = R.drawable.southeast_radar_3;
            drawableIndex = 1;
        }

        @Override
        public void run() {
            ((ImageSource) loadedMapStyle.getSource(ID_IMAGE_SOURCE)).setImage(drawables[drawableIndex++]);
            if (drawableIndex > 3) {
                drawableIndex = 0;
            }
            handler.postDelayed(this, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) {
            mapView.onStart();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mapView != null) {
            mapView.onStop();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null) {
            mapView.onSaveInstanceState(outState);
        }
    }
}
