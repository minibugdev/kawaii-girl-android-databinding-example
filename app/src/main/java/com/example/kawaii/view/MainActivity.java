package com.example.kawaii.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kawaii.R;
import com.example.kawaii.databinding.ActivityMainBinding;
import com.example.kawaii.model.Kawaii;
import com.example.kawaii.service.base.Result;
import com.example.kawaii.service.base.Service;
import com.example.kawaii.service.callback.ResultCallback;
import com.example.kawaii.view.adapter.KawaiiAdapter;

import java.util.List;

import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements KawaiiAdapter.OnItemClickListener {

    private ActivityMainBinding mBinding;
    private KawaiiAdapter       mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);

        initRecyclerView();
        getData();
    }

    private void initRecyclerView() {
        mAdapter = new KawaiiAdapter();
        mAdapter.setOnItemClickListener(this);

        final int colSpan = getResources().getInteger(R.integer.grid_column_span);
        RecyclerView recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, colSpan));
        recyclerView.setAdapter(mAdapter);
    }

    private void getData() {
        Service.create()
            .getList("2")
            .enqueue(new ResultCallback<Result<List<Kawaii>>>() {
                @Override
                public void onResult(Result<List<Kawaii>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        mAdapter.addItems(response.getResults());
                    }
                }
            });
    }

    @Override
    public void onItemClick(View view, Kawaii item, int position) {
        View itemImageView = view.findViewById(R.id.item_image_view);
        View itemTextView = view.findViewById(R.id.item_name_view);
        DetailActivity.launch(MainActivity.this, item, itemImageView, itemTextView);
    }
}
