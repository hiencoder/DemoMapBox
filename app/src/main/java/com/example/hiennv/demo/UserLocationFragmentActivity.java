package com.example.hiennv.demo;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

import java.util.List;

public class UserLocationFragmentActivity extends AppCompatActivity implements PermissionsListener {
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getResources().getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_user_location_fragment);

        //Create support fragment manager
        SupportMapFragment mapFragment;
        if (savedInstanceState == null) {
            //Create fragment
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            //Build Mapboxmap
            MapboxMapOptions options = new MapboxMapOptions();
            options.camera(new CameraPosition.Builder()
                    .target(new LatLng(38.899895, -77.03401))
                    .zoom(9)
                    .build());
            //Create mapfragment
            mapFragment = SupportMapFragment.newInstance(options);

            //Add mapfragment to parent container
            fragmentTransaction.add(R.id.fl_location_container, mapFragment, "location");
            fragmentTransaction.commit();
        } else {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("location");
        }
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                UserLocationFragmentActivity.this.mapboxMap = mapboxMap;
                mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);
                    }
                });
            }
        });
    }

    /**
     * @param style
     */
    @SuppressLint("MissingPermission")
    private void enableLocationComponent(Style style) {
        //Check permissions
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            //Nếu đã cấp quyền
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, style);
            locationComponent.setLocationComponentEnabled(false);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.NORMAL);
        } else {
            //Chưa cấp quyền
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "OnExplanationNeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted){
            //Nếu đồng ý
            mapboxMap.getStyle(new Style.OnStyleLoaded(){

                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        }else {
            //Nếu từ chối
            Toast.makeText(this, "Tu choi", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
