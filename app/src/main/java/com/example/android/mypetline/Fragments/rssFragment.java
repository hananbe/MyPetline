package com.example.android.mypetline.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.mypetline.Adapters.RssAdapter;
import com.example.android.mypetline.MainActivity;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Services.DataPresenter;
import com.example.android.mypetline.Model.Rss.Item;
import com.example.android.mypetline.Model.Rss.Rss;


import java.util.ArrayList;
import java.util.List;

public class rssFragment extends Fragment{


    private String TAG = MainActivity.class.getSimpleName();
    private  ViewGroup parent;
    private  FragmentTransaction fragmentTransaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        this.parent=parent;
        return inflater.inflate(R.layout.rss_fragment, parent, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        super.onCreate(savedInstanceState);
        new RssFeedTask().execute();
    }



    class RssFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        private RssAdapter adapter;
        private RecyclerView recycler;
        private LinearLayoutManager lManager;
        private List<Item> items ;

        @Override
        protected String doInBackground(String... urls) {
            try {


                DataPresenter presenter= new DataPresenter();
                Rss rss = presenter.getRssService().getRss();
                items = rss.getChannel().getItemList();
                // Obtener el Recycler


                adapter = new RssAdapter( new ArrayList<Item>(items),
                        new RssAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Item item) {
                                Bundle bundle= new Bundle();
                                bundle.putString("url", item.getLink());
                                Fragment fragment=new webViewFragment();
                                fragment.setArguments(bundle);

                             //  ((MainActivity)getActivity()).setWebViewFragment(item.getLink());
                                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.add(R.id.fragmentPlaceHolder, fragment);
                                fragmentTransaction.addToBackStack(null);

                                fragmentTransaction.commit();
                            }
                        });


            } catch (Exception e) {
                this.exception = e;

            }
            return null;

        }
        @Override
        protected void onPostExecute (String param) {
            recycler = parent.findViewById(R.id.rss_recycle_view);
            recycler.setHasFixedSize(true);
            lManager = new LinearLayoutManager( getActivity().getApplicationContext());
            recycler.setLayoutManager(lManager);
            recycler.setAdapter(adapter );

        }


    }

}
