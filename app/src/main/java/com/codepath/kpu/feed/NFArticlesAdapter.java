package com.codepath.kpu.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.kpu.feed.models.NFArticle;

import java.util.List;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticlesAdapter extends ArrayAdapter<NFArticle> {

    private static class NFViewHolder {
        ImageView ivImage;
        TextView tvTitle;
    }

    public NFArticlesAdapter(Context context, List<NFArticle> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        NFArticle article = getItem(position);

        NFViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new NFViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article_result, parent, false);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (NFViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        String imageURL = article.getImageURL();
        if (imageURL != null) {
            Glide.with(getContext()).load(imageURL).placeholder(R.drawable.news_placeholder).into(viewHolder.ivImage);
        }

        String headline = article.getHeadline();
        if (headline != null) {
            viewHolder.tvTitle.setText(headline);
        }

        return convertView;
    }
}
