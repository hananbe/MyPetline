package com.example.android.mypetline.Services.Maps;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MarkerItem {
    @SerializedName("markers")
    public List<items> markers = new ArrayList();

    public class items {
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("address")
        public String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOpenHours() {
            return openHours;
        }

        public void setOpenHours(String openHours) {
            this.openHours = openHours;
        }

        public String getImg_src() {
            return img_src;
        }

        public void setImg_src(String img_src) {
            this.img_src = img_src;
        }

        @SerializedName("lat")
        public String lat;

        @SerializedName("lng")
        public String lng;

        @SerializedName("type")
        public String type;

        @SerializedName("description")
        public String description;

        @SerializedName("phone")
        public String phone;

        @SerializedName("openHours")
        public String openHours;

        @SerializedName("img_src")
        public String img_src;

    }

}