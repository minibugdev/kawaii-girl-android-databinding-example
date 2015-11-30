package com.example.word.view.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.word.BR;
import com.example.word.R;
import com.example.word.databinding.ListItemBinding;
import com.example.word.model.Kawaii;
import com.example.word.viewmodel.KawaiiViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KawaiiAdapter extends RecyclerView.Adapter<KawaiiAdapter.ViewHolder> implements View.OnClickListener {

    private List<Kawaii>        mItems;
    private OnItemClickListener mListener;

    public KawaiiAdapter() {
        mItems = new ArrayList<>();
    }

    public void addItems(List<Kawaii> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Kawaii getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.kawaiiViewModel, new KawaiiViewModel(getItem(position)));
        binding.getRoot().setTag(R.id.tag_key, position);
        binding.getRoot().setOnClickListener(this);
        binding.executePendingBindings();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
            .load(url)
            .fit()
            .centerCrop()
            .into(imageView);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            int position = (int) v.getTag(R.id.tag_key);
            mListener.onItemClick(v, getItem(position), position);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, Kawaii item, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
