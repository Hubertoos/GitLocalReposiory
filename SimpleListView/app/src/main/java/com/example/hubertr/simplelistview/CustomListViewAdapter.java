package com.example.hubertr.simplelistview;

/**
 * Created by hubertr on 2015-01-22.
 */
import java.util.ArrayList;
import java.util.List;
import com.example.hubertr.simplelistview.R;
import com.example.hubertr.simplelistview.ImageHolder;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.hubertr.simplelistview.R.drawable.ic_launcher;

public class CustomListViewAdapter extends ArrayAdapter<ImageHolder> {

    Context context;
    int check = 0;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<ImageHolder> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView txtTytułObrazka;
        TextView txtDataObrazka;
        TextView txtURLObrazka;
        ImageView picIcon;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ImageHolder rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtTytułObrazka = (TextView) convertView.findViewById(R.id.tytulObrazka);
            holder.txtDataObrazka = (TextView) convertView.findViewById(R.id.dataObrazka);
            holder.txtURLObrazka = (TextView) convertView.findViewById(R.id.urlObrazka);
            holder.picIcon = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTytułObrazka.setText(rowItem.getImageTitle());
        holder.txtDataObrazka.setText(rowItem.getImageDate());
        holder.txtURLObrazka.setText(rowItem.getImageUrl());
        holder.picIcon.setImageResource(rowItem.getIcon());

        if (check == 0) {check = 1;}


            return convertView;

    }
}
