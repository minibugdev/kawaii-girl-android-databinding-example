package com.example.word.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kawaii implements Parcelable {

    private String  who;
    private String  publishedAt;
    private String  desc;
    private String  type;
    private String  url;
    private boolean used;
    private String  objectId;
    private String  createdAt;
    private String  updatedAt;

    public Kawaii(String who, String url) {
        this.who = who;
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.who);
        dest.writeString(this.publishedAt);
        dest.writeString(this.desc);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(used ? (byte) 1 : (byte) 0);
        dest.writeString(this.objectId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    protected Kawaii(Parcel in) {
        this.who = in.readString();
        this.publishedAt = in.readString();
        this.desc = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.objectId = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Kawaii> CREATOR = new Parcelable.Creator<Kawaii>() {
        public Kawaii createFromParcel(Parcel source) {
            return new Kawaii(source);
        }

        public Kawaii[] newArray(int size) {
            return new Kawaii[size];
        }
    };
}
