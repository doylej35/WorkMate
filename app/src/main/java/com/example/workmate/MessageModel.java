package com.example.workmate;

public class MessageModel {

    private int ID;
    private String Message;
    private String Title;
    private int Synchstatus;

    public MessageModel(int ID, String message, String title, int synchstatus) {
        this.ID = ID;
        Message = message;
        Title = title;
        Synchstatus = synchstatus;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "ID=" + ID +
                ", Message='" + Message + '\'' +
                ", Title='" + Title + '\'' +
                ", Synchstatus=" + Synchstatus +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getSynchstatus() {
        return Synchstatus;
    }

    public void setSynchstatus(int synchstatus) {
        Synchstatus = synchstatus;
    }
}
