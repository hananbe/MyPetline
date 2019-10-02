package com.example.android.mypetline.Services;

import android.util.Log;

import com.example.android.mypetline.Adapters.SynchronousCallAdapterFactory;
import com.example.android.mypetline.Api.rssApi;
import com.example.android.mypetline.Api.serverApi;
import com.example.android.mypetline.Model.Tips.TipsObj;
import com.example.android.mypetline.Model.adoption.TipsItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class DataPresenter {

    //tips
    private TipsView tipsView;
    private List<String> listDataHeader = new ArrayList<>();
    private List<String> img_urls = new ArrayList<>();
    private ArrayList<TipsObj> items = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();


    public DataPresenter(TipsView tipsView) {
        this.tipsView = tipsView;
    }

    public DataPresenter() {
    }

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

    /*
    creating a call to server using retrofit inorder to get the tips data as items.
     */

    public void getTipsItems() {

//create item call
        Call<TipsItem> call = getServerService().getTipsItems();

        call.enqueue(new Callback<TipsItem>() {
            @Override
            public void onResponse(Call<TipsItem> call, Response<TipsItem> response) {
                TipsItem responseItems = response.body();
                List<TipsItem.items> list = responseItems.tips;
                items = new ArrayList<>();
                for (TipsItem.items item : list) {
                    TipsObj currentItem = new TipsObj(item.tid, item.title, item.summary, item.date, item.img_src, item.type);
                    items.add(currentItem);

                }

                //iterate the tips which
                for (int i = 0; i < items.size(); i++) {
                    TipsObj item = items.get(i);
                    listDataHeader.add(items.get(i).getTitle());
                    img_urls.add(item.getImg_src());

                    List<String> tip = new ArrayList<>();


                    tip.add(item.getTid().toString());
                    tip.add(item.getTitle());
                    tip.add(item.getSummary());
                    tip.add(item.getDate());
                    tip.add(item.getImg_src());
                    tip.add(item.getType());
                    listDataChild.put(item.getTitle(), tip);

                }

                tipsView.getTipsData(listDataHeader, listDataChild, img_urls);

            }

            @Override
            public void onFailure(Call<TipsItem> call, Throwable t) {
                Log.i("test", call.toString());

            }
        });
    }


    //interface for communication between presernter and the view
    public interface TipsView {
        void getTipsData(List<String> listDataHeader, HashMap<String, List<String>> listDataChild
                , List<String> img_urls);

    }

}
