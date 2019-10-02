package com.example.android.mypetline.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.mypetline.Adapters.MarkerInfoWindowAdapter;
import com.example.android.mypetline.Services.DataPresenter;
import com.example.android.mypetline.Services.Maps.MapWrapperLayout;
import com.example.android.mypetline.Services.Maps.MarkerItem;
import com.example.android.mypetline.Services.Maps.MyMarker;
import com.example.android.mypetline.OnInfoWindowElemTouchListener;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Utilities.UtilitiesClass;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<MyMarker> mMyMarkersArray = new ArrayList<MyMarker>();
    private HashMap<Marker, MyMarker> mMarkersHashMap;
    String MapType = "";
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private List<MarkerItem.items> items;
    private Button DialButton;
    private OnInfoWindowElemTouchListener infoButtonListener;
    private String TAG = mapsFragment.class.getSimpleName();
    private ViewGroup infoWindow;
    MapWrapperLayout mapWrapperLayout;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.maps_activity, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        // Initialize the HashMap for Markers and MyMarker object
        mMarkersHashMap = new HashMap<Marker, MyMarker>();

        //initiliaze array's to store json data
        listDataHeader = new ArrayList<String>(); //used to store markers titles only to present in list view list
        listDataChild = new HashMap<String, List<String>>();// used to store markers data which prased using json

        // creating Asynctask thread  to download data as JSON Object from the server.
        new GetMaps().execute();
        if (getArguments() != null) {
            MapType = getArguments().getString("mapCategory");
        } else
            MapType = "vet";

        Log.i("test", "mapType " + MapType);
        Log.i("test", "getArguments " + (getArguments() != null));

        // initialize map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
            /*mapWrapperLayout was created inorder to enable an  option to press Dial Button
              on marker window in Google Maps.
            */
        mapWrapperLayout = (MapWrapperLayout) view.findViewById(R.id.map_relative_layout);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Log.i("location", "current location is  lat:" + location.getLatitude() + " long " + location.getLongitude());
                                LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
                                //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 18.0f));
                            }
                        }
                    });

            return;
        }
    }


    //creating markers on map according to the markers which store in hashmap
    private void plotMarkers(ArrayList<MyMarker> markers) {
        Toast.makeText(getContext(),
                markers.size() + "",
                Toast.LENGTH_LONG).show();
        if (markers.size() > 0) {
            for (MyMarker myMarker : markers) {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getmLatitude(), myMarker.getmLongitude()));
                Log.i(TAG, "icon " + myMarker.getmIcon());

                int height = 100;
                int width = 100;
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(manageMarkerIcon(myMarker.getmIcon()));
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                markerOption.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerOption.snippet(myMarker.getAddress() + " " + myMarker.getDescription() + " " + myMarker.getPhone());
                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);

            }
            mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(mMarkersHashMap, getActivity()));
        }
    }


    //general function to return icon according specific category
    private int manageMarkerIcon(String markerIcon) {
        if (markerIcon.trim().equals("petstores"))
            return R.drawable.pet_shop_icon;
        else if (markerIcon.trim().equals("vet"))
            return R.drawable.medical_map_icon;

        return R.drawable.medical_map_icon;
    }


   /* public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        public MarkerInfoWindowAdapter() {
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            //   View v = getLayoutInflater().inflate(R.layout.infowindow_layout, null);
            infoWindow = (ViewGroup) getLayoutInflater().inflate(R.layout.infowindow_layout, null);

            MyMarker myMarker = mMarkersHashMap.get(marker);


            TextView markername = infoWindow.findViewById(R.id.marker_name);
            TextView markerAddress = infoWindow.findViewById(R.id.marker_address);
            TextView markerDescription = infoWindow.findViewById(R.id.marker_description);
            TextView openHours = infoWindow.findViewById(R.id.open_hoursTxt);
            ImageView img_src = infoWindow.findViewById(R.id.imagesrc);

            markername.setText(myMarker.getName());
            markerAddress.setText(myMarker.getAddress());
            //  markerPhone.setText(myMarker.getPhone() + "");
            markerDescription.setText(myMarker.getDescription());
            final String phoneAddrss = myMarker.getPhone() + "";

            //load image to ImageView with picasso library
            Picasso.get().load(myMarker.getImg_src()).into(img_src);

            openHours.setText(myMarker.getOpenHours());

            DialButton = (Button) infoWindow.findViewById(R.id.dialbutton);


            //set button listener
            infoButtonListener = new OnInfoWindowElemTouchListener(DialButton, getResources().getDrawable(R.drawable.btn_bg), getResources().getDrawable(R.drawable.btn_bg)) {
                @Override
                protected void onClickConfirmed(View v, Marker marker) {
                    //open dial service
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneAddrss));
                    startActivity(intent);
                }

            };

            DialButton.setOnTouchListener(infoButtonListener);

            mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);

            return infoWindow;
        }
    }

*/
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        //add markers to map when the user clicks on the map
        mapWrapperLayout.init(mMap, getPixelsFromDp(getContext(), 39 + 20));
        //creating Markers after clicking on map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng l) {

                for (MarkerItem.items item : items) {

                    String name = UtilitiesClass.removeTags(item.getName());
                    Double lat = Double.parseDouble(item.getLat());
                    Double lng = Double.parseDouble(item.getLng());
                    String phone = UtilitiesClass.removeTags(item.getPhone());
                    String description = UtilitiesClass.removeTags(item.getDescription());
                    String address = UtilitiesClass.removeTags(item.getAddress());
                    String type = UtilitiesClass.removeTags(item.getType());
                    String openHours = UtilitiesClass.removeTags(item.getOpenHours());
                    String img_src = item.getImg_src();

                    if (type.trim().equals(MapType))
                        mMyMarkersArray.add(new MyMarker(name, type, lat, lng, phone, description, address, openHours, img_src));

                }
                plotMarkers(mMyMarkersArray);

            }
        });


    }


    // function to calculate button location on marker info window
    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    //async class which is running in the background inorder to get  data from the server using json object.
    private class GetMaps extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //  Toast.makeText(MapsActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            DataPresenter presenter = new DataPresenter();

            Call<MarkerItem> call = presenter.getServerService().getMapsItems();
            call.enqueue(new Callback<MarkerItem>() {
                @Override
                public void onResponse(Call<MarkerItem> call, Response<MarkerItem> response) {
                    MarkerItem responseItems = response.body();
                    items = responseItems.markers;
                }

                @Override
                public void onFailure(Call<MarkerItem> call, Throwable t) {
                    Log.i("test", call.toString());

                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }


    }


}
