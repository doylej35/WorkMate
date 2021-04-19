package com.example.workmate.models;


//this class defines the data type client, lists its data, and provides constructors, a print function, and getters and setters
//most warnings are cause the functions arent used
//**need to fix the override warning**
public class ClientModel {

    private int ClientId;
    private String ClientFname;
    private String ClientLname;
    private String ClientPhone;
    private String ClientEmail;
    private String ClientAddr;
    private String ClientLatitude;
    private String ClientLongitude;



    //constructors
    public ClientModel(int clientId, String clientFname, String clientLname, String clientPhone, String clientEmail,  String clientAddr, String clientLatitude, String clientLongitude) {
        ClientId = clientId;
        ClientFname = clientFname;
        ClientLname = clientLname;
        ClientPhone = clientPhone;
        ClientEmail = clientEmail;
        //   OrderID = orderID;
        ClientAddr = clientAddr;
        ClientLongitude = clientLongitude;
        ClientLatitude = clientLatitude;
    }

    public ClientModel() {

    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "ClientId=" + ClientId +
                ", ClientFname='" + ClientFname + '\'' +
                ", ClientLname='" + ClientLname + '\'' +
                ", ClientPhone='" + ClientPhone + '\'' +
                ", ClientEmail='" + ClientEmail + '\'' +
                ", ClientAddr='" + ClientAddr + '\'' +
                ", ClientLatitude='" + ClientLatitude + '\'' +
                ", ClientLongitude='" + ClientLongitude + '\'' +
                '}';
    }

    //getters and setters
    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public String getClientFname() {
        return ClientFname;
    }

    public void setClientFname(String clientFname) {
        ClientFname = clientFname;
    }

    public String getClientLname() {
        return ClientLname;
    }

    public void setClientLname(String clientLname) {
        ClientLname = clientLname;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public void setClientPhone(String clientPhone) {
        ClientPhone = clientPhone;
    }

    public String getClientEmail() {
        return ClientEmail;
    }

    public void setClientEmail(String clientEmail) {
        ClientEmail = clientEmail;
    }

    public String getClientAddr() {
        return ClientAddr;
    }

    public void setClientAddr(String clientAddr) {
        ClientAddr = clientAddr;
    }

    public String getClientLatitude() {return ClientLatitude; }

    public void setClientLatitude(String clientLatitude) {
        //nb this needs to be taken from the google api BEFORE ENTERING IN SQL
        ClientLatitude = clientLatitude;
    }

    public String getClientLongitude() {return ClientLongitude; }

    public void setClientLongitude(String clientLongitude) {
        //nb this needs to be taken from the google api BEFORE ENTERING IN SQL
        ClientLongitude = clientLongitude;
    }
}