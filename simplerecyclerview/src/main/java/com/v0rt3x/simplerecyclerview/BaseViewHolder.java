package com.v0rt3x.simplerecyclerview;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/**
 * Created by Tim Pincumbe on 9/13/16.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Parent class for generic RecyclerView View Holders. Provides method definitions to create the View Holder
 * elements then configure and populate them based on the provided data model of generic type.
 * The data model should be set during the configuration method.
 */
public abstract class BaseViewHolder<T extends RecyclerViewDataModel> {
    protected T mData;
    protected SimpleSpringListener viewSpringListener;
    protected Constants.LayoutType layoutType;

    /**
     * Default constructor. Not needed as the children will use their own constructors.
     */
    public BaseViewHolder() {
        layoutType =  Constants.LayoutType.LINEAR;
    }

    public BaseViewHolder (Constants.LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    /**
     * Adapter to create and instantiate the view's elements. Similar to Google's createViewHolder
     * in it's RecyclerView.ViewHolder.
     * This needs to be overridden to work properly.
     * @param viewGroup - The ViewGroup used to inflate the layout
     * @param listener - The click listener for the ViewHolder
     * @return A RecyclerViewViewHolderAdapter object as this will adapt the generic
     * BaseViewHolder parent class to Google's RecyclerView.ViewHolder
     */
    public abstract RecyclerViewViewHolderAdapter createViewHolder(ViewGroup viewGroup, OnItemClickListener listener);

    /**
     * Populates the tile based on the supplied Base Tile.
     * @param mData - the data model used to populate the item view
     */
    public abstract void configureViewHolder(T mData);

    /**
     * Gets the layout resource.
     * @return - id of layout resource
     */
    protected abstract int getLayoutId();

    /**
     * Creates and inflates the layout as set by the method getLayoutId;
     * @param viewGroup
     * @return
     */
    protected View createItemView(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        return LayoutInflater.from(context).inflate(getLayoutId(), viewGroup, false);
    }

    /**
     * Sets the Rebound Spring Listener to be used in a Layout type that's been set
     * @param view - The view to set the spring listener for. Provided by the View Holder extending this class
     */
    protected void setSpringListener(final View view) {
        switch (layoutType) {
            case LINEAR:
                viewSpringListener = new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        view.setTranslationX(value);
                    }
                };
                break;
            case GRID:
            case STAGGERED:
                viewSpringListener = new SimpleSpringListener() {
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        view.setScaleX(value);
                        view.setScaleY(value);
                        view.setAlpha(value);
                    }
                };
                break;
        }
    }

    public Constants.LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(Constants.LayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public SimpleSpringListener getViewSpringListener() {
        return viewSpringListener;
    }
}