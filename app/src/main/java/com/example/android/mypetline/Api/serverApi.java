package com.example.android.mypetline.Api;

import com.example.android.mypetline.Services.Maps.MarkerItem;
import com.example.android.mypetline.Model.adoption.AdoptionItem;
import com.example.android.mypetline.Model.adoption.TipsItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface serverApi {
    @GET("getData.php?catType=adoption")
    Call<AdoptionItem> getItems();

    @GET("getData.php?catType=tips")
    Call<TipsItem> getTipsItems();

    @GET("getData.php?catType=markers")
    Call<MarkerItem> getMapsItems();

}