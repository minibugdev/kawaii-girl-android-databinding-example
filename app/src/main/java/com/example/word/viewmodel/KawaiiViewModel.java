package com.example.word.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.word.model.Kawaii;

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