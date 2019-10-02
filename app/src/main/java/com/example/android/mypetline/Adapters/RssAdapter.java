package com.example.android.mypetline.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.mypetline.R;
import com.example.android.mypetline.Model.Rss.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyViewHolder> {

    private ArrayList<Item> dataSet;
    private Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }


    public RssAdapter(ArrayList<Item> data, OnItemClickListener listener) {
        this.dataSet = data;
        this.listener = listener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rss_item, parent, false);
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

        Glide.with(context).load(dataSet.get(listPosition).getContent()).into(featureImg);

        titleTV.setText(removeTags(dataSet.get(listPosition).getTitle()));
        descriptionTV.setText(removeTags(dataSet.get(listPosition).getDescription().replaceAll("\\<[^>]*>","")));
        pubDate.setText(formatDate(dataSet.get(listPosition).getPubDate()));
        holder.link=(dataSet.get(listPosition).getLink());
        holder.bind(dataSet.get(listPosition), listener);


    }
    private String removeTags(String str){
        String res=str.replaceAll("\\<[^>]*>\\&","");
        return res.replaceAll("&nbsp","");
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
        String link;
        ImageView featureImg;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.titleTV = (TextView) itemView.findViewById(R.id.titleTextView);
            this.descriptionTV = (TextView) itemView.findViewById(R.id.descriptionTextView);
            this.pubDate = (TextView) itemView.findViewById(R.id.publishDate);
            this.featureImg = (ImageView) itemView.findViewById(R.id.featureImg);

        }

        public void bind(final Item item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }







}
