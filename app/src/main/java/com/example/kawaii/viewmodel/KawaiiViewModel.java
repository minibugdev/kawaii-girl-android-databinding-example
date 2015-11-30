package com.example.kawaii.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.kawaii.model.Kawaii;

public class KawaiiViewModel extends BaseObservable {
    private Kawaii model;

    public KawaiiViewModel(Kawaii model) {
        this.model = model;
    }

    public Kawaii getModel() {
        return model;
    }

    @Bindable
    public String getWho() {
        return model.getWho();
    }

    @Bindable
    public String getUrl() {
        return model.getUrl();
    }
}