package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.User;
import com.codepath.apps.mysimpletweets.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProfileHeaderFragment extends Fragment {
    private User user;

    private ImageView ivProfileImage;
    private TextView tvName;
    private TextView tvUserName;
    private TextView tvTagline;
    private TextView tvTweetsCount;
    private TextView tvFollowersCount;
    private TextView tvFriendsCount;

    public static ProfileHeaderFragment newInstance(User user) {
        ProfileHeaderFragment profileHeaderFragment = new ProfileHeaderFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", user);
        profileHeaderFragment.setArguments(args);
        return profileHeaderFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user_header, parent, false);
        setupViews(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupViews(View view) {
        user = getArguments().getParcelable("user");

        ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvUserName = (TextView) view.findViewById(R.id.tvUserName);
        tvTagline = (TextView) view.findViewById(R.id.tvTagline);
        tvTweetsCount = (TextView) view.findViewById(R.id.tvTweetsCount);
        tvFollowersCount = (TextView) view.findViewById(R.id.tvFollowersCount);
        tvFriendsCount = (TextView) view.findViewById(R.id.tvFriendsCount);

        Picasso.with(getActivity()).load(user.getProfileImageUrl()).transform(new CircleTransform()).into(ivProfileImage);
        tvName.setText(user.getName());
        tvUserName.setText(user.getScreenName());
        tvTagline.setText(user.getTagLine());
        tvTweetsCount.setText(user.getTweetsCount());
        tvFollowersCount.setText(user.getFollowersCount());
        tvFriendsCount.setText(user.getFriendsCount());
    }
}
