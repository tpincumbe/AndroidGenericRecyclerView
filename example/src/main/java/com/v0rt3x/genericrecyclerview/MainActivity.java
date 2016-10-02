package com.v0rt3x.genericrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.v0rt3x.genericrecyclerview.util.Example;
import com.v0rt3x.genericrecyclerview.util.Profile;
import com.v0rt3x.simplerecyclerview.Constants;
import com.v0rt3x.simplerecyclerview.OnItemClickListener;
import com.v0rt3x.simplerecyclerview.RecyclerViewAdapter;
import com.v0rt3x.simplerecyclerview.RecyclerViewDataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<RecyclerViewDataModel> mData;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter<RecyclerViewDataModel> recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_dashboard);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(Constants.DEFAULT_GRID_COLUMNS, Constants.DEFAULT_GRID_ORIENTATION));
        recyclerViewAdapter = getRecyclerViewAdapter(Constants.LayoutType.STAGGERED);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initializeData() {
        mData = new ArrayList<>();

        mData.add(new Profile(0, "Man", "", "profile_image_man"));
        mData.add(new Profile(1, "Woman", "", "profile_image_woman"));
        mData.add(new Profile(2, "Boy", "", "profile_image_boy"));
        mData.add(new Profile(3, "Girl", "", "profile_image_girl"));
        mData.add(new Example("Phone", "You have 3 new voicemails and 6 missed calls", R.drawable.ic_call_black_36dp, "#81CCAB", "action", R.drawable.ic_chevron_right_white_36dp, "9", 0));
        mData.add(new Example("Email", "You have 7 unread emails", R.drawable.ic_email_white_36dp, "#CBCCCC", "action", R.drawable.ic_chevron_right_white_36dp, "7", 0));
        mData.add(new Example("Internet", "You have 4 connected devices", R.drawable.ic_wifi_white_36dp, "#FBAE96", "action", R.drawable.ic_chevron_right_white_36dp, "", 0));
        mData.add(new Profile(4, "Man", "", "profile_image_man"));
        mData.add(new Profile(5, "Woman", "", "profile_image_woman"));
        mData.add(new Profile(6, "Boy", "", "profile_image_boy"));
        mData.add(new Profile(7, "Girl", "", "profile_image_girl"));
        mData.add(new Example("Phone", "You have 3 new voicemails and 6 missed calls", R.drawable.ic_call_black_36dp, "#81CCAB", "action", R.drawable.ic_chevron_right_white_36dp, "9", 0));
        mData.add(new Example("Email", "You have 7 unread emails", R.drawable.ic_email_white_36dp, "#CBCCCC", "action", R.drawable.ic_chevron_right_white_36dp, "7", 0));
        mData.add(new Example("Internet", "You have 4 connected devices", R.drawable.ic_wifi_white_36dp, "#FBAE96", "action", R.drawable.ic_chevron_right_white_36dp, "", 0));
    }

    private RecyclerViewAdapter<RecyclerViewDataModel> getRecyclerViewAdapter(Constants.LayoutType layoutType) {
        RecyclerViewAdapter<RecyclerViewDataModel> adapter = new RecyclerViewAdapter<>(mData, layoutType);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, mData.get(position).getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        });

        return adapter;
    }
}
