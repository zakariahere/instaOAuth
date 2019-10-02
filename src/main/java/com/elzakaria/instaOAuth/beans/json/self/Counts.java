package com.elzakaria.instaOAuth.beans.json.self;

import com.google.gson.annotations.SerializedName;

public class Counts {
    private int media;
    private int follows;
    private int followed_by;

    @SerializedName("media")
    public int getMedia() {
        return media;
    }

    @SerializedName("media")
    public void setMedia(int media) {
        this.media = media;
    }

    @SerializedName("follows")
    public int getFollows() {
        return follows;
    }

    @SerializedName("follows")
    public void setFollows(int follows) {
        this.follows = follows;
    }

    @SerializedName("followed_by")
    public int getFollowedBy() {
        return followed_by;
    }

    @SerializedName("followed_by")
    public void setFollowedBy(int followed_by) {
        this.followed_by = followed_by;
    }
}