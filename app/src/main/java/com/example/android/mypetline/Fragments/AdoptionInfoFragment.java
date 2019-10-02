package com.example.android.mypetline.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mypetline.Adapters.AdoptionAdapter;
import com.example.android.mypetline.R;

import com.example.android.mypetline.Services.DataPresenter;
import com.example.android.mypetline.Model.adoption.AdoptionItem;
import com.example.android.mypetline.Model.adoption.AdoptionObj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdoptionInfoFragment extends Fragment {
    private  ViewGroup parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        this.parent=parent;
        return inflater.inflate(R.layout.adoption_list, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        new AdoptionTask().execute();
    }

    class AdoptionTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        private AdoptionAdapter adapter;
        private RecyclerView recycler;
        private GridLayoutManager lManager;
        private ArrayList<AdoptionObj> items ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recycler = parent.findViewById(R.id.adoption_recycle_view);
            recycler.setHasFixedSize(true);
            lManager = new GridLayoutManager( getActivity().getApplicationContext(),2);
            recycler.setLayoutManager(lManager);
        }

        @Override
        protected String doInBackground(String... urls) {
            try {

                DataPresenter presenter= new DataPresenter();

                Call<AdoptionItem> call =presenter.getServerService().getItems();
                call.enqueue(new Callback<AdoptionItem>() {
                    @Override
                    public void onResponse(Call<AdoptionItem> call, Response<AdoptionItem> response) {
                        AdoptionItem responseItems = response.body();
                        List<AdoptionItem.items> list = responseItems.adoption;
                        items=new ArrayList<>();
                        for (AdoptionItem.items item : list) {
                            AdoptionObj currentItem = new AdoptionObj(item.type,item.name,item.description,item.img_url,item.phone,
                            item.pubdate,item.location);
                            items.add(currentItem);

                        }
                        Log.i("items", "-------------list size is "+list.size());
                        adapter = new AdoptionAdapter (items);
                        recycler.setAdapter(adapter );

                    }

                    @Override
                    public void onFailure(Call<AdoptionItem> call, Throwable t) {
                        Log.i("test", call.toString());

                    }
                });
/*
                items =apiService.getItem();
                // Obtener el Recycler*/
            } catch (Exception e) {
                this.exception = e;

            }

            return null;

        }



    }

}
