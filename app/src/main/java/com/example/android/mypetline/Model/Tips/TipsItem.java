package com.example.android.mypetline.Model.adoption;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;



public  class TipsItem {
    @SerializedName( "tips")
    public List<items> tips = new ArrayList();

    public class items {

        @SerializedName("tid")
        public Integer tid;
        @SerializedName("title")
        public String title;
        @SerializedName("summary")
        public String summary;
        @SerializedName("date")
        public String date;

        @SerializedName("img_src")
        public String img_src;

        @SerializedName("type")
        public String type;

    }

}