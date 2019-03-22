package com.example.hiennv.demo.dynamicstyling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hiennv.demo.R;
import com.example.hiennv.demo.base.BaseActivity;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.RasterSource;
import com.mapbox.mapboxsdk.style.sources.TileSet;

import butterknife.BindView;

public class AddWmsSourceActivity extends BaseActivity {
    @BindView(R.id.map_view)
    MapView mapView;
    /**
     * Adding an external Web Map Service layer to the map.
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_hill_shade_layer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onMapCallBack(MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

                style.addSource(new RasterSource(
                        "web-map-source",
                        new TileSet("tileset", "https://geodata.state.nj.us/imagerywms/Natural2015?bbox={"
                                + "bbox-epsg-3857}&format=image/png&service=WMS&version=1.1.1&request=GetMap&"
                                + "srs=EPSG:3857&width=256&height=256&layers=Natural2015"), 256));

// Add the web map source to the map.
                style.addLayerBelow(
                        new RasterLayer("web-map-layer", "web-map-source"), "aeroway-taxiway");

            }
        });
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
