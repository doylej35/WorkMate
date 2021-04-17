package com.example.workmate;

public class FriendlyMessage {
    private String id;
    private String text;
    private String name;
    private String photoUrl;
    private String imageUrl;
    private String senderEmail;
    private String recipientEmail;
    private String chatID;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl, String imageUrl, String senderEmail, String recipientEmail, String chatID) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSender() {
        return senderEmail;
    }

    public void setSender(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getRecipient() {
        return recipientEmail;
    }

    public void setRecipient(String receipEmail) {
        this.recipientEmail = recipientEmail;
    }
}