package ru.kpfu.itis.archgis.screen.map;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.List;

import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.response.ChsResponse;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.RadiocarbonResponse;
import ru.kpfu.itis.archgis.model.response.ReportResponse;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;
import ru.kpfu.itis.archgis.screen.common.ListItem;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.screen.quick.authorlist.AuthorListActivity;
import ru.kpfu.itis.archgis.screen.quick.chslist.ChsListActivity;
import ru.kpfu.itis.archgis.screen.quick.excavationlist.ExcavationListActivity;
import ru.kpfu.itis.archgis.screen.quick.monumentlist.MonumentListActivity;
import ru.kpfu.itis.archgis.screen.quick.radiocarbonlist.RadiocarbonListActivity;
import ru.kpfu.itis.archgis.screen.quick.reportlist.ReportListActivity;
import ru.kpfu.itis.archgis.screen.quick.researchlist.ResearchListActivity;
import ru.kpfu.itis.archgis.utils.Constants;

/**
 * Created by DNS1 on 31.05.2017.
 */

public class SearchMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener, LocationListener,
        SearchMapView {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;

    private SearchMapPresenter presenter;
    private LoadingView mLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        initPresenter();
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mMap.setMyLocationEnabled(true);
                configMap();
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            mMap.setMyLocationEnabled(true);
            configMap();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(100000);
        mLocationRequest.setFastestInterval(100000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(SearchMapActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION );
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }



    private void initPresenter() {
        String activity = getIntent().getExtras().getString(Constants.CALLING_ACTIVITY);
        if (activity != null || !activity.equals("") || !activity.isEmpty()) {
            if (activity.equals(ResearchListActivity.class.getSimpleName())) {
                presenter = new SearchMapResearchPresenter(this);
            }
            if (activity.equals(ChsListActivity.class.getSimpleName())) {
                presenter = new SearchMapChsPresenter(this);
            }
            if (activity.equals(ExcavationListActivity.class.getSimpleName())) {
                presenter = new SearchMapExcavationPresenter(this);
            }
            if (activity.equals(MonumentListActivity.class.getSimpleName())) {
                presenter = new SearchMapMonumentPresenter(this);
            }
            if (activity.equals(RadiocarbonListActivity.class.getSimpleName())) {
                presenter = new SearchMapRadiocarbonPresenter(this);
            }
        }
    }

    private void configMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setOnMarkerClickListener(this);
        presenter.init();
    }

    private int setMarker(ListItem marker) {
        int count = 0;
            for (int j = 0; j < marker.getX().size(); j++) {
                if (marker.getX().get(j) != null || !marker.getX().get(j).equals("")) {
                    double lat = Double.parseDouble(marker.getX().get(j));
                    if (marker.getY().get(j) != null || !marker.getX().get(j).equals("")) {
                        double lan = Double.parseDouble(marker.getY().get(j));
                        placeMarkerOnMap(lat, lan, marker.getName());
                        count++;
                    }
                }
            }
        return count;
    }

    private void placeMarkerOnMap(double latitude, double longitude, String name) {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(name)
        );
    }


    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showLoading(Disposable disposable) {
        mLoadingView.showLoading(disposable);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadChsResponse(List<ChsResponse> responses) {
        int count = 0;
        for (int i = 0; i < responses.size(); i++) {
            count += setMarker(responses.get(i));
        }
        if (count == 0){
            Toast.makeText(this, R.string.marker_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadMonumentResponse(List<MonumentResponse> responses) {
        int count = 0;
        for (int i = 0; i < responses.size(); i++) {
            count += setMarker(responses.get(i));
        }
        if (count == 0){
            Toast.makeText(this, R.string.marker_error, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void loadExcavationResponse(List<ExcavationResponse> responses) {
        int count = 0;
        for (int i = 0; i < responses.size(); i++) {
            count += setMarker(responses.get(i));
        }
        if (count == 0){
            Toast.makeText(this, R.string.marker_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadRadiocarbonResponse(List<RadiocarbonDate> responses) {
        int count = 0;
        for (int i = 0; i < responses.size(); i++) {
            RadiocarbonResponse response = responses.get(i).getRadiocarbonResponse();
            count += setMarker(response);
        }
        if (count == 0){
            Toast.makeText(this, R.string.marker_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void loadResearchResponse(@NonNull List<ResearchResponse> responses) {
        int count = 0;
        for (int i = 0; i < responses.size(); i++) {
            count += setMarker(responses.get(i));
        }
        if (count == 0){
            Toast.makeText(this, R.string.marker_error, Toast.LENGTH_LONG).show();
        }
    }
}
