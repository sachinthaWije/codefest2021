package com.lec.customerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.lec.customerapp.directionsLib.FetchURL;
import com.lec.customerapp.mdel.MapDiration;
import com.lec.customerapp.mdel.MapTime;

public class MapsFragment extends Fragment {
    public GoogleMap currentGoogleMap;
    FusedLocationProviderClient fusedLocationProviderClient;

    String TAG = "location";
    private int LOCATION_PERMISSION = 100;
    private LatLng customerLocation;
    private LatLng dropLocation;
    private Polyline currentPolyLine;

    public Marker riderMaker;
    public Marker currentMarker;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsFragment.super.getContext());
            currentGoogleMap = googleMap;
            updateCustomerLocation();
        }
    };
    public MarkerOptions currentLocation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void updateCustomerLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //Toast.makeText(MapsFragment.super.getContext(), "latitude"+latitude+"----longitude"+longitude, Toast.LENGTH_SHORT).show();
                    customerLocation = new LatLng(latitude, longitude);


                    BitmapDescriptor ico_current = getBitmapDesc(getActivity(), R.drawable.ic_baseline_person_pin_circle_24);
                    BitmapDescriptor ico_dest = getBitmapDesc(getActivity(), R.drawable.ic_baseline_location_on_24);

                    currentLocation = new MarkerOptions().icon(ico_current).draggable(false).position(customerLocation).title("I am Here");
                    final MarkerOptions destinationLocation = new MarkerOptions().icon(ico_dest).draggable(true).position(customerLocation).title("I want to go to");


                    currentMarker = currentGoogleMap.addMarker(currentLocation);
                    currentGoogleMap.addMarker(destinationLocation);
                    currentGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(customerLocation));
                    currentGoogleMap.moveCamera(CameraUpdateFactory.zoomTo(10));


                    currentGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker marker) {
                            Log.d(TAG, "Drag start");
                        }

                        @Override
                        public void onMarkerDrag(Marker marker) {
                            Log.d(TAG, "Drag on");
                        }

                        @Override
                        public void onMarkerDragEnd(Marker marker) {
                            Log.d(TAG, "Drag end");
                            dropLocation = marker.getPosition();
//                            currentGoogleMap.addPolyline(new PolylineOptions().add(customerLocation,dropLocation));
                            HomeActvity.job.setEnabled(true);
                            ((HomeActvity) getActivity()).setJobLatlang(customerLocation, dropLocation);

                            new FetchURL() {
                                @Override
                                public void onTaskDone(Object... values) {
                                    if (currentPolyLine != null) {
                                        currentPolyLine.remove();
                                    }
                                    currentPolyLine = currentGoogleMap.addPolyline((PolylineOptions) values[0]);
                                }

                                @Override
                                public void onDistance(MapDiration distance) {
                                    //Toast.makeText(getActivity(), "Distance="+distance.getMinDuration(), Toast.LENGTH_SHORT).show();
                                    double startPrice = 50;
                                    double aditionalPricePerKm = 40;
                                    double aditionalm = distance.getMinDuration() - 1000;
                                    double aditionalPrice = ((int) (aditionalm / 1000)) * aditionalPricePerKm;
                                    double estimatedTotalPrice = startPrice + aditionalPrice;

                                    ((HomeActvity) getActivity()).setEstimateValue(estimatedTotalPrice, distance.getTextDuration());


                                }

                                @Override
                                public void onTIme(MapTime time) {
                                    //Toast.makeText(getActivity(), "Time="+time.getMinTime(), Toast.LENGTH_SHORT).show();

                                }


                            }.execute(getUrl(customerLocation, dropLocation, "driving"), "driving");
                        }
                    });


                } else {
                    Toast.makeText(MapsFragment.super.getContext(), "Location Not Found", Toast.LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MapsFragment.super.getContext(), "Location Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private BitmapDescriptor getBitmapDesc(FragmentActivity activity, int ic_tracking) {
        Drawable LAYER_1 = ContextCompat.getDrawable(activity, ic_tracking);
        LAYER_1.setBounds(0, 0, LAYER_1.getIntrinsicWidth(), LAYER_1.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(LAYER_1.getIntrinsicWidth(), LAYER_1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        LAYER_1.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        Log.d(TAG, "URL:" + url);
        return url;
    }
    public DocumentReference getCurrentJobHomeActivity(){
        return ((HomeActvity)getActivity()).getJobReference();
    }

    public void showRiderLocation(double lat, double log) {
        Toast.makeText(getActivity(), "Rider Location updating....", Toast.LENGTH_SHORT).show();
        if (currentGoogleMap != null) {
            if (riderMaker == null) {
                BitmapDescriptor ico_rider = getBitmapDesc(getActivity(), R.drawable.ic_baseline_directions_car_24);
                riderMaker = currentGoogleMap.addMarker(new MarkerOptions().icon(ico_rider).position(new LatLng(lat, log)).title("Rider"));
            } else {
                riderMaker.setPosition(new LatLng(lat, log));
            }

        } else {
            Toast.makeText(getActivity(), "Map not Ready!", Toast.LENGTH_SHORT).show();
        }


    }
}