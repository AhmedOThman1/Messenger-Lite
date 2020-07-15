package com.example.messengerlite.pojo;

import java.util.List;

public class message {

    private String message_type;
    private boolean me_or_him;
    private String message_text;
    private int his_profile_img;
    private String message_photo;
    private String message_video;
    private int voice_time;
    private boolean voice_message_running;
    private boolean video_message_running;
    private int video_position;
    private long baseoffset;
    private List<String> photos;
    private int icon;
    private int iconSize;
    private String message_time;
    private String userID;
    private boolean image_visabilty;
    private List<String> PeopleCanSeeMessage;
    private  boolean love = false, haha  = false, wow  = false, sad = false , angry  = false, like = false , dislike = false ;
    private int [] reacts = new int[] {0,0,0,0,0,0,0};
    private int replay_postion;

    // Text message
    public message(String message_type,String userID, String message_text , String message_time , int replay_postion) {
        this.message_type = message_type;
        this.message_text = message_text;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion = replay_postion;
        me_or_him = true;
    }

    public message(String message_type,String userID, String message_text, int his_profile_img, String message_time,int replay_postion) {
        this.message_type = message_type;
        this.message_text = message_text;
        this.his_profile_img = his_profile_img;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion = replay_postion;
        image_visabilty = true;
        me_or_him = false;

    }

    // Voice message

    public message(String message_type,String userID, int voice_time, long baseoffset, String message_time,int replay_postion) {
        this.message_type = message_type;
        this.voice_time = voice_time * 1000;
        this.voice_message_running = false;
        this.baseoffset = baseoffset;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        me_or_him = true;
    }

    public message(String message_type,String userID, int voice_time, long baseoffset, int his_profile_img, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.voice_time = voice_time * 1000;
        this.voice_message_running = false;
        this.baseoffset = baseoffset;
        this.his_profile_img = his_profile_img;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        image_visabilty = true;
        me_or_him = false;
    }

    // one photo message

    public message(String message_type,String userID, int a, String message_photo, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.message_photo = message_photo;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        me_or_him = true;
    }


    public message(String message_type,String userID, int a, String message_photo, int his_profile_img, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.message_photo = message_photo;
        this.his_profile_img = his_profile_img;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        image_visabilty = true;
        me_or_him = false;
    }


    // multi photo message


    public message(String message_type,String userID, List<String> photos, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.photos = photos;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        me_or_him = true;
    }

    public message(String message_type,String userID, List<String> photos , int his_profile_img, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.his_profile_img = his_profile_img;
        this.photos = photos;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        image_visabilty = true;
        me_or_him = false;
    }

    // video message

    public message( String message_type,String userID,boolean video_message_running , int video_position, String message_video, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.message_video = message_video;
        this.video_message_running = video_message_running;
        this.video_position = video_position;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        me_or_him = true;
    }

    public message( String message_type,String userID, String message_video, boolean video_message_running, int video_position , int his_profile_img, String message_time, int replay_postion) {
        this.message_type = message_type;
        this.message_video = message_video;
        this.his_profile_img = his_profile_img;
        this.video_message_running = video_message_running;
        this.video_position = video_position;
        this.message_time = message_time;
        this.userID = userID;
        this.replay_postion=replay_postion;
        image_visabilty = true;
        me_or_him = false;
    }

    // icon

    public message(String message_type,String userID, int icon , int iconSize, int replay_postion) {
        this.message_type = message_type;
        this.icon = icon;
        this.iconSize = iconSize;
        this.userID = userID;
        this.replay_postion=replay_postion;
        me_or_him = true;
    }

    public boolean isImage_visabilty() {
        return image_visabilty;
    }

    public void setImage_visabilty(boolean image_visabilty) {
        this.image_visabilty = image_visabilty;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public int getIcon() {
        return icon;
    }

    public int getIconSize() {
        return iconSize;
    }

    public int getVideo_position() {
        return video_position;
    }

    public String getMessage_type() {
        return message_type;
    }

    public boolean isMe_or_him() {
        return me_or_him;
    }

    public String getMessage_text() {
        return message_text;
    }

    public String getMessage_photo() {
        return message_photo;
    }

    public String getMessage_video() {
        return message_video;
    }

    public int getVoice_time() {
        return voice_time;
    }

    public boolean isVoice_message_running() {
        return voice_message_running;
    }

    public void setVoice_message_running(boolean voice_message_running) {
        this.voice_message_running = voice_message_running;
    }

    public long getBaseoffset() {
        return baseoffset;
    }

    public void setBaseoffset(long baseoffset) {
        this.baseoffset = baseoffset;
    }

    public int getHis_profile_img() {
        return his_profile_img;
    }


    public List<String> getPhotos() {
        return photos;
    }

    public List<String> getPeopleCanSeeMessage() {
        return PeopleCanSeeMessage;
    }

    public String getUserID() {
        return userID;
    }

    public void setPeopleCanSeeMessage(List<String> peopleCanSeeMessage) {
        PeopleCanSeeMessage = peopleCanSeeMessage;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public int getReplay_postion() {
        return replay_postion;
    }

    public void setReplay_postion(int replay_postion) {
        this.replay_postion = replay_postion;
    }

    public int[] getReacts() {
        return reacts;
    }

    public void setReacts(int[] reacts) {
        this.reacts = reacts;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public boolean isHaha() {
        return haha;
    }

    public void setHaha(boolean haha) {
        this.haha = haha;
    }

    public boolean isWow() {
        return wow;
    }

    public void setWow(boolean wow) {
        this.wow = wow;
    }

    public boolean isSad() {
        return sad;
    }

    public void setSad(boolean sad) {
        this.sad = sad;
    }

    public boolean isAngry() {
        return angry;
    }

    public void setAngry(boolean angry) {
        this.angry = angry;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }
}
