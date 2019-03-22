package com.example.hiennv.demo.dynamicstyling;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hiennv.demo.R;
import com.example.hiennv.demo.base.BaseActivity;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.RasterDemSource;

import butterknife.BindView;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.rasterOpacity;

public class AdjustLayerOpacityActivity extends BaseActivity {
    /**
     * Use a seekbar to adjust the opacity of a raster layer on top of a map.
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_just_layer_opacity;
    }

    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.sb_opacity)
    SeekBar sbOpacity;

    private Layer chicago;

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {
        sbOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (chicago != null) {
                    chicago.setProperties(
                            rasterOpacity((float) progress / 100)
                    );
                }
                String value = progress + "%";
                tvProgress.setText(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onMapCallBack(final MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                style.addSource(new RasterDemSource("chicago-source", "mapbox://mapbox.u8yyzaor"));
                style.addLayer(new RasterLayer("chicago", "chicago-source"));
                chicago = mapboxMap.getStyle().getLayer("chicago");
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
