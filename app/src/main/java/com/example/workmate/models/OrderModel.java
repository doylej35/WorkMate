package com.example.workmate.models;


public class OrderModel {

    private int OrderId;
    private String ClientEmail;         //from client table
    private String SupplierEmail;          //from supplier table
    private String SupplierService;     //from supplier table
    private String OrderDate;
    private int OrderHours;
    private double OrderCost;
    private String OrderLocation;       //the clients location (address)

    @Override
    public String toString() {
        return "OrderModel{" +
                "OrderId=" + OrderId +
                ", ClientEmail='" + ClientEmail + '\'' +
                ", SupplierEmail='" + SupplierEmail + '\'' +
                ", SupplierService='" + SupplierService + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                ", OrderHours=" + OrderHours +
                ", OrderCost=" + OrderCost +
                ", OrderLocation='" + OrderLocation + '\'' +
                '}';
    }

    public OrderModel(int orderId, String clientEmail, String supplierEmail, String supplierService,
                      String orderDate, int orderHours, double orderCost, String orderLocation) {
        OrderId = orderId;
        ClientEmail = clientEmail;
        SupplierEmail = supplierEmail;
        SupplierService = supplierService;
        OrderDate = orderDate;
        OrderHours = orderHours;
        OrderCost = orderCost;
        OrderLocation = orderLocation;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getClientEmail() {
        return ClientEmail;
    }

    public void setClientEmail(String clientEmail) {
        ClientEmail = clientEmail;
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

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public int getOrderHours() {
        return OrderHours;
    }

    public void setOrderHours(int orderHours) {
        OrderHours = orderHours;
    }

    public double getOrderCost() {
        return OrderCost;
    }

    public void setOrderCost(double orderCost) {
        OrderCost = orderCost;
    }

    public String getOrderLocation() {
        return OrderLocation;
    }

    public void setOrderLocation(String orderLocation) {
        OrderLocation = orderLocation;
    }
}
