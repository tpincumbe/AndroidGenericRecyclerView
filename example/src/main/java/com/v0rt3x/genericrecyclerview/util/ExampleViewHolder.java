package com.v0rt3x.genericrecyclerview.util;

import android.graphics.Color;
import android.graphics.PorterDuff;
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
 * Created by Tim Pincumbe on 9/1/16.
 * View holder for the Base Tile. It provides a method to populate the tile based on the supplied Base Tile.
 */
public class ExampleViewHolder extends BaseViewHolder<Example> {
    private ImageView iconBGView, iconImageView, actionImageView;
    private TextView iconNumberTextView, titleTextView, messageTextView;

    ExampleViewHolder() {
       super();
    }

    ExampleViewHolder(Constants.LayoutType layoutType) {
        super(layoutType);
    }

    @Override
    public RecyclerViewViewHolderAdapter createViewHolder(ViewGroup viewGroup, OnItemClickListener listener) {
        View view = createItemView(viewGroup);
        setSpringListener(view);

        iconBGView = (ImageView) view.findViewById(R.id.view_icon_bg);
        iconImageView = (ImageView) view.findViewById(R.id.image_icon);
        iconNumberTextView = (TextView) view.findViewById(R.id.text_icon_number);
        titleTextView = (TextView) view.findViewById(R.id.text_title);
        messageTextView = (TextView) view.findViewById(R.id.text_message);
        actionImageView = (ImageView) view.findViewById(R.id.image_action);

        return new RecyclerViewViewHolderAdapter<>(this, view, listener);
    }

    /**
     * Populates the tile based on the supplied Base Tile.
     * @param mData - the data model used to populate the item view
     */
    public void configureViewHolder(Example mData) {
        this.mData = mData;
        iconBGView.setColorFilter(Color.parseColor(mData.getImageBackgroundColor()), PorterDuff.Mode.SRC_ATOP);
        iconImageView.setImageResource(mData.getImage());
        String numberHits = mData.getNumberHits();
        if (numberHits != null && !numberHits.isEmpty()) {
            iconNumberTextView.setVisibility(View.VISIBLE);
            iconNumberTextView.setText(mData.getNumberHits());
        }
        titleTextView.setText(mData.getTitle());
        messageTextView.setText(mData.getMessage());
        int actionImage = mData.getActionImage();
        if (actionImageView != null && actionImage != 0) {
            actionImageView.setImageResource(mData.getActionImage());
            actionImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getLayoutId() {
        switch (layoutType) {
           case LINEAR:
               return R.layout.card_example;
            case GRID:
            case STAGGERED:
                return R.layout.card_grid_example;
            default:
                return R.layout.card_example;
        }
    }
}
