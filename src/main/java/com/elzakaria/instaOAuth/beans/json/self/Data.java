package com.elzakaria.instaOAuth.beans.json.self;

import com.google.gson.annotations.SerializedName;

public class Data {
    private String id;
    private String username;
    private String full_name;
    private String profile_picture;
    private String bio;
    private String website;
    private boolean is_business;
    private Counts counts;

    @SerializedName("id")
    public String getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("username")
    public String getUsername() {
        return username;
    }

    @SerializedName("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("full_name")
    public String getFullName() {
        return full_name;
    }

    @SerializedName("full_name")
    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    @SerializedName("profile_picture")
    public String getProfilePicture() {
        return profile_picture;
    }

    @SerializedName("profile_picture")
    public void setProfilePicture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    @SerializedName("bio")
    public String getBio() {
        return bio;
    }

    @SerializedName("bio")
    public void setBio(String bio) {
        this.bio = bio;
    }

    @SerializedName("website")
    public String getWebsite() {
        return website;
    }

    @SerializedName("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @SerializedName("is_business")
    public boolean getIsBusiness() {
        return is_business;
    }

    @SerializedName("is_business")
    public void setIsBusiness(boolean is_business) {
        this.is_business = is_business;
    }

    @SerializedName("counts")
    public Counts getCounts() {
        return counts;
    }

    @SerializedName("counts")
    public void setCounts(Counts counts) {
        this.counts = counts;
    }
}