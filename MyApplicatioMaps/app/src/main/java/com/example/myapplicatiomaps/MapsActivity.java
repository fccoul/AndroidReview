package com.example.myapplicatiomaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private static final String TAG ="mon test" ;
    private static final int TAG_CODE_PERMISSION_LOCATION = 8;
    private GoogleMap mMap;

    private static final LatLng MOUNTAIN_VIEW=new LatLng(46.846606, -71.24163);

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.i(TAG,"okay goes on");
    }

    void getLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED)
        {
            if(mMap !=null)
            {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
            else
            {
                //Permissionto to access the location is missing. Show rationale and request permission
                PermissionUtils.requestPermission(this,LOCATION_PERMISSION_REQUEST_CODE ,
                        Manifest.permission.ACCESS_FINE_LOCATION,true);
            }
        }
        else {
            Toast.makeText(this,"nicht ",Toast.LENGTH_LONG);
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }

    }


    //traite le resultat du retour de la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {


        if(requestCode!=LOCATION_PERMISSION_REQUEST_CODE)
        {
            Toast.makeText(this,"differnet Request Code ",Toast.LENGTH_LONG);
            Log.i(TAG,"different request code :"+requestCode);
           // return;
        }
        if(PermissionUtils.isPermissionGranted(permissions,grantResults,Manifest.permission.ACCESS_FINE_LOCATION))
        {
            //Enable to my Location layer if the permission has been granted
            //enablemyLocation;
            Toast.makeText(this,"Permission OK ",Toast.LENGTH_LONG);
            Log.i(TAG,"Permission OK:"+requestCode);
        }
        else
        {
            //Permission was denied . Display an error message
            Toast.makeText(this,"Failed Permission",Toast.LENGTH_LONG);
            Log.e(TAG,"Failed Permission:"+requestCode);
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.

        getLocationPermission();
        /*
        if(checkPermission())
        {
          mMap.setMyLocationEnabled(true);
           /* mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);*/
        //}
        //else {
         // askPermission();
        //}

        /*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            if(mMap !=null)
            {
                /*
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
                mMap.setOnMyLocationClickListener(this);*/
            //}
            //else
    /*
            {
                //Permissionto to access the location is missing. Show rationale and request permission
                PermissionUtils.requestPermission(this,LOCATION_PERMISSION_REQUEST_CODE ,
                        Manifest.permission.ACCESS_FINE_LOCATION,true);
            }
        }
        */


/*
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
*/



        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

        LatLng piauleQC=new LatLng(46.846920, -71.240128);
        mMap.addMarker(new MarkerOptions().position(piauleQC).title("Marker in QC Charlesbourg"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(piauleQC));
*/
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);
        //CameraUpdate yrLocation=CameraUpdateFactory.newLatLngZoom(piauleQC,10);
       // mMap.animateCamera(yrLocation);

        LatLng piauleQC=new LatLng(46.846920, -71.240128);
        mMap.addMarker(new MarkerOptions().position(piauleQC).title("Marker in QC Charlesbourg"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(piauleQC));

        CameraPosition cameraPosition=new CameraPosition.Builder()
        .target(MOUNTAIN_VIEW)
        .zoom(17)
        .bearing(90)
        .tilt(30)
        .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);

        mMap.setMyLocationEnabled(true);

    }

    private boolean checkPermission() {
        Log.d(TAG,"CheckPermission()");
        //Ask for permission if it wasn't granted yet
        return(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        ==PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.d(TAG,"my Location button clicked !");
        Toast.makeText(this,"my Location button clicked",Toast.LENGTH_LONG).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return true;//false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this,"Current Location :\n"+location,Toast.LENGTH_LONG).show();
    }

}
