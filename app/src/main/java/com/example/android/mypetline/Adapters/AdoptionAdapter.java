package com.example.android.mypetline.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Model.adoption.AdoptionObj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdoptionAdapter extends RecyclerView.Adapter<AdoptionAdapter.MyViewHolder> {

    private ArrayList<AdoptionObj> dataSet;
    private Context context;


    public AdoptionAdapter(ArrayList<AdoptionObj> data) {
        this.dataSet = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adoption_item, parent, false);
        context = parent.getContext();


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView titleTV = holder.titleTV;
        TextView descriptionTV = holder.descriptionTV;
        TextView pubDate = holder.pubDate;
        ImageView featureImg = holder.featureImg;
        Button dialButton= holder.dialbutton;

        titleTV.setText(removeTags(dataSet.get(listPosition).getName()));
        descriptionTV.setText(removeTags(dataSet.get(listPosition).getDescription()));
        pubDate.setText(dataSet.get(listPosition).getPubdate());

        //loadGlideImg(removeTags(dataSet.get(listPosition).getImg_url()),featureImg);
        String imgUrl=removeTags(dataSet.get(listPosition).getImg_url()).trim();

        Glide.with(context).load(imgUrl)
                .into(featureImg);

       /* dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setData(Uri.parse("tel:"+dataSet.get(listPosition).getPhone()));
                startActivity(intent);

            }
        }
    }*/

    }
    private String removeTags(String str){
        Log.i("remove","str is " +str);
       String s=str.replaceAll("<[^>]*>","").
               replaceAll("&#39;","'").
               replaceAll("&quot;","\"").
               replaceAll("&nbsp","");
        return  s;
    }

    private void loadGlideImg(String imgUrl,ImageView featureImg){

        try {
            String url = imgUrl /* URL of Image */;

            if (url.startsWith("http://"))
                url = url.replace("http://", "https://");

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.logo);
            requestOptions.error(R.mipmap.petshop);
            Glide
                    .with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(featureImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String formatDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM--yyyy HH:mm");

        return format.format(new Date(date));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;
        TextView descriptionTV;
        TextView pubDate;
        ImageView featureImg;
        Button dialbutton;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.titleTV = (TextView) itemView.findViewById(R.id.titleTextView);
            this.descriptionTV = (TextView) itemView.findViewById(R.id.descriptionTextView);
            this.pubDate = (TextView) itemView.findViewById(R.id.publishDate);
            this.featureImg = (ImageView) itemView.findViewById(R.id.featureImg);
            this.dialbutton=itemView.findViewById(R.id.dialbutton);

        }

    }







}
