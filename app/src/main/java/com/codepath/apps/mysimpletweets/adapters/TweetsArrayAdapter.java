package com.codepath.apps.mysimpletweets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    private class ViewHolder {
        ImageView ivProfileImage;
        TextView tvName;
        TextView tvUserName;
        TextView tvBody;
        TextView tvTimestamp;
    }

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
            viewHolder.tvTimestamp = (TextView) convertView.findViewById(R.id.tvTimestamp);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.ivProfileImage.setImageResource(0);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).transform(new CircleTransform()).into(viewHolder.ivProfileImage);
        viewHolder.tvName.setText(tweet.getUser().getName());
        viewHolder.tvUserName.setText(tweet.getUser().getScreenName());
        viewHolder.tvBody.setText(tweet.getBody());
        viewHolder.tvTimestamp.setText(tweet.getCreatedAt());
        return convertView;
    }
}
