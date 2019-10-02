package com.example.android.mypetline.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import com.example.android.mypetline.Adapters.ExpandableArrayAdapter;
import com.example.android.mypetline.MainActivity;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Services.DataPresenter;

import java.util.HashMap;
import java.util.List;

public class tipsFragment extends Fragment implements DataPresenter.TipsView {


    ExpandableArrayAdapter listAdapter;
    ExpandableListView expListView;
    private DataPresenter dataService;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.tips, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        super.onCreate(savedInstanceState);
        dataService = new DataPresenter(this);

        dataService.getTipsItems();

        //  new getTips().execute();

        // get the listview
        expListView = view.findViewById(R.id.lvExp);


    }

    @Override
    public void getTipsData(List<String> listDataHeader, HashMap<String, List<String>> listDataChild, List<String> img_urls) {

        listAdapter = new ExpandableArrayAdapter(getContext(), listDataHeader, listDataChild, img_urls);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
/*
    private class getTips extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            DataService service= new DataService();

            Call<TipsItem> call = service.getServerService().getTipsItems();

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
                        listAdapter = new ExpandableArrayAdapter(getContext(), listDataHeader, listDataChild, img_urls);

                        // setting list adapter
                        expListView.setAdapter(listAdapter);

                    }



                }

                @Override
                public void onFailure(Call<TipsItem> call, Throwable t) {
                    Log.i("test", call.toString());

                }
            });

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }


    }
    */
}
