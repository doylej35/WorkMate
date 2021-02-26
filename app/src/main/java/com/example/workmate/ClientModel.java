package com.example.workmate;


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

    //constructors
    public ClientModel(int clientId, String clientFname, String clientLname, String clientPhone, String clientEmail,  String clientAddr) {
        ClientId = clientId;
        ClientFname = clientFname;
        ClientLname = clientLname;
        ClientPhone = clientPhone;
        ClientEmail = clientEmail;
     //   OrderID = orderID;
        ClientAddr = clientAddr;
    }

    public ClientModel() {

    }

    //toString is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "ClientModel{" +
                "ClientId=" + ClientId +
                ", ClientFname='" + ClientFname + '\'' +
                ", ClientLname='" + ClientLname + '\'' +
                ", ClientPhone='" + ClientPhone + '\'' +
                ", ClientEmail='" + ClientEmail + '\'' +
             //   ", OrderID=" + OrderID +
                ", ClientAddr=" + ClientAddr +
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
}
