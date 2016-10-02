package com.v0rt3x.genericrecyclerview.util;

import com.v0rt3x.simplerecyclerview.BaseViewHolder;
import com.v0rt3x.simplerecyclerview.RecyclerViewDataModel;

/**
 * Created by Tim Pincumbe on 9/13/16.
 */
public class Profile extends RecyclerViewDataModel {
    public int id;
    public String full_name;
    public String email;
    public String picture;
    public boolean status;
    public boolean home_status;

    public Profile() {
    }

    public Profile(int id, String full_name, String email, String picture) {
        this(id, full_name, email, picture, true);
    }

    public Profile(int id, String full_name, String email, String picture, boolean status) {
        this(id, full_name, email, picture, status, true);
    }

    public Profile(int id, String full_name, String email, String picture, boolean status, boolean home_status) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.picture = picture;
        this.status = status;
        this.home_status = home_status;
    }

    public String getDisplayName() {
        return full_name;
    }

    @Override
    public Class<? extends BaseViewHolder> getViewHolderClass() {
        return ProfilesListViewHolder.class;
    }
}
