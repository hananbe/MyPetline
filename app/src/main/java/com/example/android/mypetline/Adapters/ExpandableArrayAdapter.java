package com.example.android.mypetline.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.mypetline.R;
import com.example.android.mypetline.Utilities.UtilitiesClass;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class ExpandableArrayAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> CategoryTitles;
    private final List<String> img_urls;

    //child
    private HashMap<String, List<String>> CategoriesData;

    public ExpandableArrayAdapter(Context context, List<String> CategoryTitles,
                                  HashMap<String, List<String>> CategoriesData,
                                  List<String> img_urls) {
        this.context = context;
        this.CategoryTitles = CategoryTitles;
        this.img_urls = img_urls;

        this.CategoriesData = CategoriesData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.CategoriesData.get(this.CategoryTitles.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        // final String childText = (String) getChild(groupPosition, childPosition);
        final String Title = (String) getChild(groupPosition, 1);
        final String Date = (String) getChild(groupPosition, 3);
        final String Summary = (String) getChild(groupPosition, 2);

        final String img_src = UtilitiesClass.removeUrlTags((String) getChild(groupPosition, 4));

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.tips_list_item, null);
        }


        TextView titleView = (TextView) convertView
                .findViewById(R.id.titleText);

        TextView dateView = (TextView) convertView
                .findViewById(R.id.date);


        TextView summary = (TextView) convertView
                .findViewById(R.id.summary);

        ImageView imageSrc = (ImageView) convertView.findViewById(R.id.image_src);
        //  summary.setCompoundDrawables(null,null,R.drawable.adoption,null);
         Picasso.get().load(img_src).into(imageSrc);


        //txtListChild.setText(childText);
        titleView.setText(Title);
        dateView.setText(Date);
        //  txtactivityHours.setText(activityHours);
        summary.setText(Summary);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //  return this.CategoriesData.get(this.CategoryTitles.get(groupPosition))
        //   .size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.CategoryTitles.get(groupPosition);
    }

    public String getGroupImgUrl(int groupPosition){
        return  this.img_urls.get(groupPosition);
    }
    @Override
    public int getGroupCount() {
        return this.CategoryTitles.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String img_url = UtilitiesClass.removeUrlTags(getGroupImgUrl(groupPosition));

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.tips_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        ImageView img_src = (ImageView) convertView
                .findViewById(R.id.group_img_src);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        Picasso.get().load(img_url).into(img_src);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}