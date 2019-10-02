package com.example.android.mypetline.Utilities;

import android.util.Log;

public class UtilitiesClass {
    public static String removeTags(String str){
        Log.i("remove","str is " +str);
        String s=str.replaceAll("<[^>]*>","").
                replaceAll("&#39;","'").
                replaceAll("&quot;","\"").
                replaceAll("&nbsp","");
        return  s;
    }

    public static String removeUrlTags(String str){
        Log.i("remove","str is " +str);
        String s=str.replaceAll("<[^>]*>","").
                replaceAll("&#39;","'").
                replaceAll("&quot;","\"").
                replaceAll("&nbsp","").
                replaceAll("\n","").replaceAll("\t","");
        return  s;
    }
}
