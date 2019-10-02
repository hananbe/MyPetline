package com.example.android.mypetline.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.mypetline.Fragments.mapsFragment;
import com.example.android.mypetline.OnInfoWindowElemTouchListener;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Services.Maps.MyMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private HashMap<Marker, MyMarker> mMarkersHashMap;
    private Button DialButton;
    private OnInfoWindowElemTouchListener infoButtonListener;
    private String TAG = mapsFragment.class.getSimpleName();
    private ViewGroup infoWindow;
    Context context;

    public MarkerInfoWindowAdapter( HashMap<Marker, MyMarker> mMarkersHashMap ,Context context) {
        this.context=context;
        this.mMarkersHashMap=mMarkersHashMap;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        //   View v = getLayoutInflater().inflate(R.layout.infowindow_layout, null);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        infoWindow = (ViewGroup) inflater.inflate(R.layout.infowindow_layout, null);
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
        infoButtonListener = new OnInfoWindowElemTouchListener(DialButton, context.getResources().getDrawable(R.drawable.btn_bg), context.getResources().getDrawable(R.drawable.btn_bg)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                //open dial service
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneAddrss));
                context.startActivity(intent);
            }

        };

        DialButton.setOnTouchListener(infoButtonListener);

        //mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);

        return infoWindow;
    }
}
