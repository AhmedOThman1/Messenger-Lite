package com.example.messengerlite.pojo;

import java.util.List;

public class ChatsModel {


    private String username;
    private String messageText;
    private String profileMessage;
    private String message_time;
    private List<String> storyProfileImgs;
    private List<String> storyProfileNames;

    public ChatsModel() {
    }

    public ChatsModel(List<String> storyProfileImgs, List<String> storyProfileNames) {
        this.storyProfileImgs = storyProfileImgs;
        this.storyProfileNames = storyProfileNames;
    }

    public ChatsModel(String username, String messageText, String profileMessage, String message_time) {
        this.username = username;
        this.messageText = messageText;
        this.profileMessage = profileMessage;
        this.message_time = message_time;
    }


    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getProfileMessage() {
        return profileMessage;
    }

    public String getMessage_time() {
        return message_time;
    }

    public List<String> getStoryProfileImgs() {
        return storyProfileImgs;
    }

    public List<String> getStoryProfileNames() {
        return storyProfileNames;
    }
}
