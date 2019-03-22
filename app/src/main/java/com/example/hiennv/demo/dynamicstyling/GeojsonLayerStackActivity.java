package com.example.hiennv.demo.dynamicstyling;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hiennv.demo.R;
import com.example.hiennv.demo.base.BaseActivity;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;

public class GeojsonLayerStackActivity extends BaseActivity {

    /**
     * Using the second argument of addLayer, you can add a layer below existing one
     * Add Layer from geoJson link
     */
    @BindView(R.id.map_view)
    MapView mapView;
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
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                try {
                    //Create url from link geoJson
                    URL geoJsonUrl = new URL("https://d2ad6b4ur7yvpq.cloudfront.net/naturalearth-3.3.0/ne_50m_urban_areas.geojson");
                    GeoJsonSource geoJsonSource = new GeoJsonSource("urban-areas", geoJsonUrl);
                    style.addSource(geoJsonSource);

                    //Create FillLayer
                    FillLayer urbanArea = new FillLayer("urban-areas-fill","urban-areas");
                    urbanArea.setProperties(
                            fillColor(Color.parseColor("#ff0088")), //Địa điểm có trong link sẽ đc tô màu
                            fillOpacity(0.4f)
                    );
                    style.addLayerBelow(urbanArea,"water");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
