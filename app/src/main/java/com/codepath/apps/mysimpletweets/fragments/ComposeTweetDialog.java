package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.network.TwitterApplication;
import com.codepath.apps.mysimpletweets.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

public class ComposeTweetDialog extends DialogFragment {
    private EditText etComposeTweet;
    private TextView tvCharCount;
    private Button btnTweet;
    private final int MAX_CHAR_COUNT = 140;
    private TextWatcher charCountWatcher;
    private TwitterClient client;

    public ComposeTweetDialog() {
    }

    public static ComposeTweetDialog newInstance(String title) {
        ComposeTweetDialog frag = new ComposeTweetDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_compose_tweet, container);
        setupViews(view);
        return view;
    }

    public void setupViews (View view) {
        etComposeTweet = (EditText) view.findViewById(R.id.etComposeTweet);
        tvCharCount = (TextView) view.findViewById(R.id.tvCharCount);
        btnTweet = (Button) view.findViewById(R.id.btnTweet);
        client = TwitterApplication.getRestClient();

        charCountWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This sets a textview to the current length
                tvCharCount.setText(String.valueOf(MAX_CHAR_COUNT - s.length()) + " chars left");
            }

            public void afterTextChanged(Editable s) {
            }
        };
        etComposeTweet.addTextChangedListener(charCountWatcher);

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweet = etComposeTweet.getText().toString();
                client.composeTweet(tweet, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                });
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
