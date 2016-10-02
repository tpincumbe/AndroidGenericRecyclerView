package com.v0rt3x.genericrecyclerview.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.v0rt3x.genericrecyclerview.R;
import com.v0rt3x.simplerecyclerview.BaseViewHolder;
import com.v0rt3x.simplerecyclerview.Constants;
import com.v0rt3x.simplerecyclerview.OnItemClickListener;
import com.v0rt3x.simplerecyclerview.RecyclerViewViewHolderAdapter;

/**
 * Created by v533448 on 9/14/16.
 */
public class ProfilesListViewHolder extends BaseViewHolder<Profile> {
    private ImageView profileImageView, statusImageView, homeStatusImageView;
    private TextView nameTextView, accessStatusTextView;

    ProfilesListViewHolder() {
        super();
    }

    ProfilesListViewHolder(Constants.LayoutType layoutType) {
        super(layoutType);
    }

    @Override
    public RecyclerViewViewHolderAdapter createViewHolder(ViewGroup viewGroup, OnItemClickListener listener) {
        View view = createItemView(viewGroup);
        setSpringListener(view);

        profileImageView = (ImageView) view.findViewById(R.id.image_profile);
        nameTextView = (TextView) view.findViewById(R.id.text_name);
        homeStatusImageView = (ImageView) view.findViewById(R.id.image_home_status);
        statusImageView = (ImageView) view.findViewById(R.id.image_status);
        accessStatusTextView = (TextView) view.findViewById(R.id.text_access_status);

        return new RecyclerViewViewHolderAdapter<>(this, view, listener);
    }

    @Override
    public void configureViewHolder(Profile mData) {
        this.mData = mData;
        Context context = profileImageView.getContext();

        int id = context.getResources().getIdentifier(mData.picture, "drawable", context.getPackageName());
        profileImageView.setImageResource(id);
        nameTextView.setText(mData.full_name);

        if (mData.status) {
            statusImageView.setImageResource(R.drawable.ic_ok_32);
            accessStatusTextView.setText("Active");
        }else {
            statusImageView.setImageResource(R.drawable.ic_x_mark_32);
            accessStatusTextView.setText("Blocked");
        }

        if (mData.home_status) {
            homeStatusImageView.setImageResource(R.drawable.ic_home_sky);
        }else {
            homeStatusImageView.setImageResource(R.drawable.ic_home_gray);
        }
    }

    @Override
    protected int getLayoutId() {
        switch (layoutType) {
            case LINEAR:
                return R.layout.profile_card;
            case GRID:
            case STAGGERED:
                return R.layout.profile_grid_card;
            default:
                return R.layout.profile_card;
        }
    }
}
