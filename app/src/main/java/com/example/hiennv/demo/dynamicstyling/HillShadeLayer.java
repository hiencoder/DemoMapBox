package com.example.hiennv.demo.dynamicstyling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.hiennv.demo.R;
import com.example.hiennv.demo.base.BaseActivity;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.HillshadeLayer;
import com.mapbox.mapboxsdk.style.sources.RasterDemSource;

import butterknife.BindView;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.hillshadeHighlightColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.hillshadeShadowColor;

public class HillShadeLayer extends BaseActivity {
    //Set hillShadelayer for map
    @BindView(R.id.map_view)
    MapView mapView;
    private static final String LAYER_ID = "hillshade-layer";
    private static final String SOURCE_ID = "hillshade-source";
    private static final String SOURCE_URL = "mapbox://mapbox.terrain-rgb";
    private static final String HILLSHADE_HIGHLIGHT_COLOR = "#008924";

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
        mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                // Add hillshade data source to map
                RasterDemSource rasterDemSource = new RasterDemSource(SOURCE_ID, SOURCE_URL);
                style.addSource(rasterDemSource);

                // Create and style a hillshade layer to add to the map
                HillshadeLayer hillshadeLayer = new HillshadeLayer(LAYER_ID, SOURCE_ID).withProperties(
                        hillshadeHighlightColor(Color.parseColor(HILLSHADE_HIGHLIGHT_COLOR)),
                        hillshadeShadowColor(Color.BLACK)
                );

                // Add the hillshade layer to the map
                style.addLayerBelow(hillshadeLayer, "aerialway");
            }
        });
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
