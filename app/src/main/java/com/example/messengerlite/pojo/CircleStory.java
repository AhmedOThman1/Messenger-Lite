package com.example.messengerlite.pojo;

public class CircleStory {
    private String profile_img;
    private String profile_name;
    private boolean online;
    private boolean story;


    public CircleStory(String profile_img, String profile_name, boolean online, boolean story) {
        this.profile_img = profile_img;
        this.profile_name = profile_name;
        this.online = online;
        this.story = story;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isStory() {
        return story;
    }

    public void setStory(boolean story) {
        this.story = story;
    }
}
