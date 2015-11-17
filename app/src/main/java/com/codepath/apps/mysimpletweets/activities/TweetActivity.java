package com.codepath.apps.mysimpletweets.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.codepath.apps.mysimpletweets.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class TweetActivity extends AppCompatActivity {
    private User user;

    private ImageView ivProfileImage;
    private TextView tvName;
    private TextView tvUserName;
    private TextView tvBody;
    private TextView tvTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        Tweet tweet = (Tweet) getIntent().getParcelableExtra("tweet");
        user = (User) getIntent().getParcelableExtra("user");

        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvName = (TextView) findViewById(R.id.tvName);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvTimestamp = (TextView) findViewById(R.id.tvTimestamp);

        Picasso.with(TweetActivity.this).load(user.getProfileImageUrl()).transform(new CircleTransform()).into(ivProfileImage);
        tvName.setText(user.getName());
        tvUserName.setText(user.getScreenName());
        tvBody.setText(tweet.getBody());
        tvTimestamp.setText(tweet.getCreatedAt());

        setupListeners();
    }

    public void setupListeners() {
        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TweetActivity.this, ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
