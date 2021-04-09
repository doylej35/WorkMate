package com.example.workmate;

//this defines a supplier class with its data, construtors, a print function, and getters and setters
//most of the warnings are about functions not being used.
//*****need to figure out + fix the override warning*****
//**fix the constructor to accept null values

public class SupplierModel {

    private int SupplierId;
    private String SupplierFname;
    private String SupplierLname;
    private String SupplierEmail;
    private String SupplierPhone;
    private String SupplierService;
    private String SupplierAddr;
    private int SupplierRating;
    private String SupplierLatitude;
    private String SupplierLongitude;


    //constructors
    public SupplierModel(int supplierId, String supplierFname,
                         String supplierLname,  String supplierPhone, String supplierEmail,
                         String supplierAddr,String supplierService, int supplierRating, String supplierLatitude, String supplierLongitude) {
        SupplierId = supplierId;
        SupplierFname = supplierFname;
        SupplierLname = supplierLname;
        SupplierEmail = supplierEmail;
        SupplierPhone = supplierPhone;
        SupplierService = supplierService;
        SupplierAddr = supplierAddr;
        SupplierRating = supplierRating;
        SupplierLatitude = supplierLatitude;
        SupplierLongitude = supplierLongitude;
    }

    //public SupplierModel() {
    //}

    //toString for printing data
    @Override
    public String toString() {
        return "SupplierModel{" +
                "SupplierId=" + SupplierId +
                ", SupplierFname='" + SupplierFname + '\'' +
                ", SupplierLname='" + SupplierLname + '\'' +
                ", SupplierEmail='" + SupplierEmail + '\'' +
                ", SupplierPhone='" + SupplierPhone + '\'' +
                ", SupplierService='" + SupplierService + '\'' +
                ", SupplierAddr='" + SupplierAddr + '\'' +
                ", SupplierRating='" + SupplierRating + '\'' +
                ", SupplierLatitude='" + SupplierLatitude + '\'' +
                ", SupplierLongitude='" + SupplierLongitude + '\'' +
                '}';
    }

    //getters and setters
    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public String getSupplierPhone() {
        return SupplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        SupplierPhone = supplierPhone;
    }

    public String getSupplierFname() {
        return SupplierFname;
    }

    public void setSupplierFname(String supplierFname) {
        SupplierFname = supplierFname;
    }

    public String getSupplierLname() {
        return SupplierLname;
    }

    public void setSupplierLname(String supplierLname) {
        SupplierLname = supplierLname;
    }

    public String getSupplierEmail() {
        return SupplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        SupplierEmail = supplierEmail;
    }

    public String getSupplierService() {
        return SupplierService;
    }

    public void setSupplierService(String supplierService) {
        SupplierService = supplierService;
    }

    public String getSupplierAddr() {
        return SupplierAddr;
    }

    public void setSupplierAddr(String supplierAddr) {
        SupplierAddr = supplierAddr;
    }

    public int getSupplierRating() {
        return SupplierRating;
    }

    public void setSupplierRating(int supplierRating) {
        SupplierRating = supplierRating;
    }

    public String getSupplierLatitude() {return SupplierLatitude; }

    public void setSupplierLatitude(String supplierLatitude) {
        //nb this will come from the google geo stuff. NEEDS TO BE SET BEFORE ENTERING IN MYSQL
        SupplierLatitude = supplierLatitude;
    }

    public String getSupplierLongitude() {return SupplierLongitude; }

    public void setSupplierLongitude(String supplierLongitude) {
        //nb this will come from the google geo stuff. NEEDS TO BE SET BEFORE ENTERING IN MYSQL
        SupplierLongitude = supplierLongitude;
    }


}
