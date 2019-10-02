package com.example.android.mypetline.Api;

import com.example.android.mypetline.Model.Rss.Rss;

import retrofit2.http.GET;

public interface rssApi {
    @GET("rss/search?q=בעלי+חיים&hl=he&gl=IL&ceid=IL:he")
    Rss getRss();
}