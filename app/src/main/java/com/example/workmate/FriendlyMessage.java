package com.example.workmate;

public class FriendlyMessage {
    private String id;
    private String text;
    private String sender;
    private String recipient;
    private String photoUrl;
    private String imageUrl;
    private String chatID;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String sender, String recipient, String photoUrl, String imageUrl, String chatID) {
        this.text = text;
        this.sender = sender;
        this.recipient = recipient;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
        this.chatID = chatID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderName() {
        return sender;
    }

    public void setName(String name) {
        this.sender = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}