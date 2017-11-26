package com.hms.mohamedseliem.httpclient.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import com.hms.mohamedseliem.httpclient.fragment.GetNearbyPlacesData;
import com.hms.mohamedseliem.httpclient.GetPlace;
import com.hms.mohamedseliem.httpclient.LoginActivity;
import com.hms.mohamedseliem.httpclient.R;
import com.hms.mohamedseliem.httpclient.TokenManager;

import java.io.IOException;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,

        GoogleApiClient.OnConnectionFailedListener,

        LocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }
    private GoogleMap mMap;

    private GoogleApiClient client;

    private LocationRequest locationRequest;

    private Location lastlocation;

    private Marker currentLocationmMarker;

    public static final int REQUEST_LOCATION_CODE = 99;

    int PROXIMITY_RADIUS = 10000;
    SupportMapFragment mapFragment;
    EditText tf_location;

    double latitude,longitude;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        {

            checkLocationPermission();



        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tf_location =  container.findViewById(R.id.TF_location);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode)

        {

            case REQUEST_LOCATION_CODE:

                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                {

                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)

                    {

                        if(client == null)

                        {

                            bulidGoogleApiClient();

                        }

                        mMap.setMyLocationEnabled(true);

                    }

                }

                else

                {

                    Toast.makeText(getContext(),"Permission Denied" , Toast.LENGTH_LONG).show();

                }

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



        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            bulidGoogleApiClient();

            mMap.setMyLocationEnabled(true);

        }

    }





    protected synchronized void bulidGoogleApiClient() {

        client = new GoogleApiClient.Builder(getContext()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        client.connect();



    }



    @Override

    public void onLocationChanged(Location location) {



        latitude = location.getLatitude();

        longitude = location.getLongitude();

        lastlocation = location;

        if(currentLocationmMarker != null)

        {

            currentLocationmMarker.remove();



        }

        Log.d("lat = ",""+latitude);

        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);

        markerOptions.title("Current Location");

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        currentLocationmMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));



        if(client != null)

        {

            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);

        }

    }



    public void onClick(View v)

    {

        Object dataTransfer[] = new Object[2];

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();



        switch(v.getId())

        {

            case R.id.B_search:



                String location = tf_location.getText().toString();

                List<Address> addressList;





                if(!location.equals(""))

                {

                    Geocoder geocoder = new Geocoder(getContext());



                    try {

                        addressList = geocoder.getFromLocationName(location, 5);



                        if(addressList != null)

                        {

                            for(int i = 0;i<addressList.size();i++)

                            {

                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());

                                MarkerOptions markerOptions = new MarkerOptions();

                                markerOptions.position(latLng);

                                markerOptions.title(location);

                                mMap.addMarker(markerOptions);

                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                            }

                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }

                break;

            case R.id.B_hopistals:

                mMap.clear();

                String hospital = "hospital";

                String url = getUrl(latitude, longitude, hospital);

                dataTransfer[0] = mMap;

                dataTransfer[1] = url;



                getNearbyPlacesData.execute(dataTransfer);

                Toast.makeText(getContext(), "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();

                break;





            case R.id.B_schools:

                mMap.clear();

                String school = "school";

                url = getUrl(latitude, longitude, school);

                dataTransfer[0] = mMap;

                dataTransfer[1] = url;



                getNearbyPlacesData.execute(dataTransfer);

                Toast.makeText(getContext(), "Showing Nearby Schools", Toast.LENGTH_SHORT).show();

                break;

            case R.id.B_restaurants:

                mMap.clear();

                String resturant = "restuarant";

                url = getUrl(latitude, longitude, resturant);

                dataTransfer[0] = mMap;

                dataTransfer[1] = url;



                getNearbyPlacesData.execute(dataTransfer);

                Toast.makeText(getContext(), "Showing Nearby Restaurants", Toast.LENGTH_SHORT).show();

                break;


        }

    }





    private String getUrl(double latitude , double longitude , String nearbyPlace)

    {



        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

        googlePlaceUrl.append("location="+latitude+","+longitude);

        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);

        googlePlaceUrl.append("&type="+nearbyPlace);

        googlePlaceUrl.append("&sensor=true");

        googlePlaceUrl.append("&key="+"AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");



        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());



        return googlePlaceUrl.toString();

    }



    @Override

    public void onConnected(@Nullable Bundle bundle) {



        locationRequest = new LocationRequest();

        locationRequest.setInterval(100);

        locationRequest.setFastestInterval(1000);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);





        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)

        {

            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);

        }

    }





    public boolean checkLocationPermission()

    {

        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )

        {



            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION))

            {

                ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);

            }

            else

            {

                ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);

            }

            return false;



        }

        else

            return true;

    }





    @Override

    public void onConnectionSuspended(int i) {

    }



    @Override

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
