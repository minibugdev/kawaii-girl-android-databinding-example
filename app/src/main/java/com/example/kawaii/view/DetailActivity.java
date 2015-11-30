package com.example.kawaii.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.kawaii.R;
import com.example.kawaii.databinding.ActivityDetailBinding;
import com.example.kawaii.model.Kawaii;
import com.example.kawaii.viewmodel.KawaiiViewModel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY = "DetailActivity:extra";
    public static final String SAVE_KEY  = "DetailActivity:save";

    private ActivityDetailBinding mBinding;

    public static void launch(Activity activity, Kawaii item, View... views) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_KEY, item);

        Pair<View, String> imageTransitionView = Pair.create(views[0], activity.getString(R.string.transition_grid_image));
        Pair<View, String> textTransitionView = Pair.create(views[1], activity.getString(R.string.transition_grid_name));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imageTransitionView, textTransitionView);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Kawaii kawaii = restoreInstanceState(savedInstanceState);
        initBinding(kawaii);
    }

    private Kawaii restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return getIntent().getParcelableExtra(EXTRA_KEY);
        }
        else {
            return savedInstanceState.getParcelable(SAVE_KEY);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SAVE_KEY, mBinding.getKawaiiViewModel().getModel());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
            .load(imageUrl)
            .fit()
            .noFade()
            .centerInside()
            .into(view);
    }

    private void initBinding(Kawaii kawaii) {
        KawaiiViewModel kawaiiViewModel = new KawaiiViewModel(kawaii);
        mBinding.setKawaiiViewModel(kawaiiViewModel);
    }
}
