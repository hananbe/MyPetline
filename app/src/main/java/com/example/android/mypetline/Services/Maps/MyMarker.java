package com.example.android.mypetline.Services.Maps;

public class MyMarker {
    private String mIcon;
    private String description;
    private String phone;
    private Double mLatitude;
    private Double mLongitude;
    private String address;
    private String name;
    private String openHours;
    private String img_src;

    public MyMarker(String name, String icon, Double latitude, Double longitude, String phone,
                    String description, String address, String openHours, String img_src) {
        this.name = name;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mIcon = icon;
        this.phone = phone;
        this.description = description;
        this.address = address;
        this.openHours = openHours;
        this.img_src = img_src;


    }


    public String getmIcon() {
        return mIcon;
    }

    public void setmIcon(String icon) {
        this.mIcon = icon;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getOpenHours() {
        return openHours;
    }

    public String getImg_src() {
        return img_src;
    }


}

