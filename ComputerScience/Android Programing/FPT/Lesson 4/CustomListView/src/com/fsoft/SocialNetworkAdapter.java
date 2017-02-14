package com.fsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

public class SocialNetworkAdapter extends ArrayAdapter<SocialNetwork> {
    Context context;
    int layoutResourceId;   
    SocialNetwork data[] = null;
   
    public SocialNetworkAdapter(Context context, int layoutResourceId, SocialNetwork[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SocialNetworkHolder holder = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new SocialNetworkHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
           
            row.setTag(holder);
        }
        else
        {
            holder = (SocialNetworkHolder)row.getTag();
        }
       
        SocialNetwork weather = data[position];
        holder.txtTitle.setText(weather.title);
        holder.imgIcon.setImageResource(weather.icon);
       
        return row;
    }
   
    static class SocialNetworkHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
