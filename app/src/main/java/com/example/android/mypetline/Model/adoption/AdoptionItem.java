package com.example.android.mypetline.Model.adoption;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;



public  class AdoptionItem {
    @SerializedName( "adoption")
    public List<items> adoption = new ArrayList();

    public class items {

        @SerializedName("type")
        public Integer type;
        @SerializedName("name")
        public String name;
        @SerializedName("description")
        public String description;
        @SerializedName("img_url")
        public String img_url;

        @SerializedName("phone")
        public String phone;

        @SerializedName("pubdate")
        public String pubdate;

        @SerializedName("location")
        public String location;

    }

}