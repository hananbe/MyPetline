package com.example.android.mypetline.Network;

import com.example.android.mypetline.Adapters.SynchronousCallAdapterFactory;
import com.example.android.mypetline.Api.rssApi;
import com.example.android.mypetline.Api.serverApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiClient {

    //return RSS api service
    public rssApi getRssService() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://news.google.com/");
        builder.addConverterFactory(SimpleXmlConverterFactory.create());
        builder.addCallAdapterFactory(SynchronousCallAdapterFactory.create());
        Retrofit retrofit = builder.build();


        rssApi apiService = retrofit.create(rssApi.class);

        return apiService;
    }


    public serverApi getServerService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dimonaline.co.il/mypetlineBE/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        serverApi apiService = retrofit.create(serverApi.class);
        return apiService;
    }
}
