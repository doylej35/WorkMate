package com.example.workmate;

public class RatingsModel {

    private int RatingId;               //id reference number
    private String ClientEmail;        //person who left the review
    private String SupplierEmail;       //person the review is for
    private int RatingNumber;           //number of stars
    private String RatingComment;       //the comment they leave for the rating

    public RatingsModel(int RatingId, String clientEmail, String supplierEmail, int ratingNumber, String ratingComment) {
        ClientEmail = clientEmail;
        SupplierEmail = supplierEmail;
        RatingNumber = ratingNumber;
        RatingComment = ratingComment;
    }

    @Override
    public String toString() {
        return "RatingsModel{" +
                "RatingId=" + RatingId +
                ", ClientEmail='" + ClientEmail + '\'' +
                ", SupplierEmail='" + SupplierEmail + '\'' +
                ", RatingNumber=" + RatingNumber +
                ", RatingComment='" + RatingComment + '\'' +
                '}';
    }

    public int getRatingId() {
        return RatingId;
    }

    public void setRatingId(int ratingId) {
        RatingId = ratingId;
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

    public int getRatingNumber() {
        return RatingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        RatingNumber = ratingNumber;
    }

    public String getRatingComment() {
        return RatingComment;
    }

    public void setRatingComment(String ratingComment) {
        RatingComment = ratingComment;
    }
}
