package com.v0rt3x.genericrecyclerview.util;

import com.v0rt3x.simplerecyclerview.BaseViewHolder;
import com.v0rt3x.simplerecyclerview.RecyclerViewDataModel;

/**
 * Created by Tim Pincumbe on 8/29/16.
 * Base tile type that is just a simple tile. Examples are "Email", "Phone", "Internet", and "TV".
 * They all have a basic icon, badge, title, message, and "action icon" (chevron)
 * All other tiles should extend this POJO.
 */
public class Example extends RecyclerViewDataModel{
    private String title;
    private String message;
    private int image;
    private String imageBackgroundColor;
    private String action;
    private int actionImage;
    private String numberHits;
    private int tileType;

    public Example(String title, String message, int image, String imageBackgroundColor, String action, int actionImage, String numberHits, int tileType) {
        this.title = title;
        this.message = message;
        this.image = image;
        this.imageBackgroundColor = imageBackgroundColor;
        this.action = action;
        this.actionImage = actionImage;
        this.numberHits = numberHits;
        this.tileType = tileType;
    }

    public String getDisplayName() {
        return title;
    }

    @Override
    public Class<? extends BaseViewHolder> getViewHolderClass() {
        return ExampleViewHolder.class;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageBackgroundColor() {
        return imageBackgroundColor;
    }

    public void setImageBackgroundColor(String imageBackgroundColor) {
        this.imageBackgroundColor = imageBackgroundColor;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getActionImage() {
        return actionImage;
    }

    public void setActionImage(int actionImage) {
        this.actionImage = actionImage;
    }

    public String getNumberHits() {
        return numberHits;
    }

    public void setNumberHits(String numberHits) {
        this.numberHits = numberHits;
    }

    public int getTileType() {
        return tileType;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
    }
}
