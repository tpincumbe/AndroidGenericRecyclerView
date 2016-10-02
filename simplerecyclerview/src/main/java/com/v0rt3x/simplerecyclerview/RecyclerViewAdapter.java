package com.v0rt3x.simplerecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


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
 * Generic RecyclerView Adapter that can be used with many types of View Holders
 */
public class RecyclerViewAdapter<T extends RecyclerViewDataModel> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<T> dataList;
    private OnItemClickListener listener;
    private SpringChain mSpringChain;
    private Constants.LayoutType layoutType;

    public RecyclerViewAdapter(List<T> dataList) {
        this(dataList, Constants.LayoutType.LINEAR);
    }

    public RecyclerViewAdapter(List<T> dataList, Constants.LayoutType layoutType) {
        this.dataList = dataList;
        this.layoutType = layoutType;
        mSpringChain = SpringChain.create();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    /**
     * This will create the proper view holder based on the view holder type.
     * Can return null if there is an exception creating the view holder by reflection.
     * @param viewGroup
     * @param viewType
     * @return
     */
    public RecyclerViewViewHolderAdapter onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        try {
            Constructor<? extends BaseViewHolder> ctor = getViewHolderClass(viewType).getDeclaredConstructor(Constants.LayoutType.class);
            BaseViewHolder baseViewHolder = ctor.newInstance(layoutType);
            RecyclerViewViewHolderAdapter vh = baseViewHolder.createViewHolder(viewGroup, listener);
            SimpleSpringListener springListener = baseViewHolder.getViewSpringListener();
            if (springListener != null) {
                mSpringChain.addSpring(springListener);
            }else {
                Log.e("RecyclerViewAdapter", "Please set the appropriate spring listener in your View Holder(s)");
            }

            return vh;
        } catch (NoSuchMethodException e) {
            Log.e("RecyclerViewAdapter", e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e("RecyclerViewAdapter", e.getMessage());
        } catch (InstantiationException e) {
            Log.e("RecyclerViewAdapter", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("RecyclerViewAdapter", e.getMessage());
        }

        return null;
    }

    /**
     * Gets the class of the ViewHolder for the RecyclerViewDataModel object in the given position
     * @param position - position of the RecyclerViewDataModel object in the data list.
     * @return The Class of the RecyclerViewDataModel object at the given position
     */
    public Class<? extends BaseViewHolder> getViewHolderClass(int position) {
        Class<? extends BaseViewHolder> clazz = BaseViewHolder.class;
        if (dataList != null && !dataList.isEmpty()) {
            clazz = dataList.get(position).getViewHolderClass();
        }

        return clazz;
    }

    /**
     * This will configure/populate the tile based on the title type.
     * @param viewHolder
     * @param position
     */
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewDataModel mData = dataList.get(position);
        RecyclerViewViewHolderAdapter vh = (RecyclerViewViewHolderAdapter) viewHolder;

        vh.configureViewHolder(mData);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        //Run Facebook Rebound animations
        final ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                List<Spring> springs = mSpringChain.getAllSprings();
                int width = recyclerView.getWidth();

                for (int i = 0; i < springs.size(); i++) {
                    springs.get(i).setCurrentValue(-width);
                }

                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mSpringChain.getAllSprings().size() > 0) {
                            mSpringChain.setControlSpringIndex(0)
                                    .getControlSpring()
                                    .setEndValue(0);
                        }else {
                            Log.e("RecyclerViewAdapter", "Please set the appropriate spring listener in your View Holder(s)");
                        }
                    }
                }, 500);
            }
        });
    }

    /**
     * Returns just the position in order to grab the class type from the list of data.
     * @param position
     * @return
     */
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Returns the number of tiles in the list
     * @return
     */
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Removed the object at the given position.
     * Notifies the RecyclerView Item Animator
     * @param position
     */
    public void remove(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Adds a new object to the end of the data list.
     * Notifies the RecyclerView Item Animator
     * @param mData
     */
    public void add(T mData) {
        dataList.add(mData);
        notifyItemInserted(dataList.size() - 1);
    }

    /**
     * Adds a new object at the given position to the data list
     * Notifies the RecyclerView Item Animator
     * @param mData
     * @param position
     */
    public void add(T mData, int position) {
        dataList.add(position, mData);
        notifyItemInserted(position);
    }

    /**
     * Adds a set of objects to the end of the list
     * Notifies the RecyclerView Item Animator
     * @param mData
     */
    public void add(List<T> mData) {
        int startingSize = dataList.size();
        dataList.addAll(mData);
        notifyItemRangeChanged(startingSize, mData.size());
    }
}