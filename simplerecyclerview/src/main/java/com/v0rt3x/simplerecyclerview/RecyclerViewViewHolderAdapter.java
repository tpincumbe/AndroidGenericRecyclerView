package com.v0rt3x.simplerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tim Pincumbe on 9/8/16.
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
 * Adapter to be used with the generic RecyclerViewAdapter.
 * This will return the proper View Holder type needed by Google's RecyclerView
 */
public class RecyclerViewViewHolderAdapter<T extends RecyclerViewDataModel> extends RecyclerView.ViewHolder {
    private BaseViewHolder<T> baseViewHolder;

    /**
     * Main constructor.
     * @param baseViewHolder - A BaseViewHolder with generic data type.
     * @param itemView - View of the item that is trying to be rendered.
     * @param listener - Click listener for the item that is trying to be rendered.
     */
    public RecyclerViewViewHolderAdapter(BaseViewHolder<T> baseViewHolder, final View itemView, final OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(itemView, getLayoutPosition());
                }
            }
        });
        this.baseViewHolder = baseViewHolder;
    }

    /**
     * Adapter to call the View Holders configure. This will populate the view based on the provided data
     * @param mData - data model of generic type
     */
    public void configureViewHolder(T mData) {
        baseViewHolder.configureViewHolder(mData);
    }
}

