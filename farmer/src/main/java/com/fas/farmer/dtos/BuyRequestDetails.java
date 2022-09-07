package com.fas.farmer.dtos;

public class BuyRequestDetails {
    private Long productId;
    private String productName;
    private Long productQuantity;
    private double productPrice;

    private Long farmerId;
    private String farmerFirstName;
    private String farmerLastName;
    private Long farmerPincode;
    private String farmerPhnNumber;

    private Long buyRequestId;
    private Long supplierId;
    private Double askedPrice;
    private String requestStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerFirstName() {
        return farmerFirstName;
    }

    public void setFarmerFirstName(String farmerFirstName) {
        this.farmerFirstName = farmerFirstName;
    }

    public String getFarmerLastName() {
        return farmerLastName;
    }

    public void setFarmerLastName(String farmerLastName) {
        this.farmerLastName = farmerLastName;
    }

    public Long getFarmerPincode() {
        return farmerPincode;
    }

    public void setFarmerPincode(Long farmerPincode) {
        this.farmerPincode = farmerPincode;
    }

    public String getFarmerPhnNumber() {
        return farmerPhnNumber;
    }

    public void setFarmerPhnNumber(String farmerPhnNumber) {
        this.farmerPhnNumber = farmerPhnNumber;
    }

    public Long getBuyRequestId() {
        return buyRequestId;
    }

    public void setBuyRequestId(Long buyRequestId) {
        this.buyRequestId = buyRequestId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getAskedPrice() {
        return askedPrice;
    }

    public void setAskedPrice(Double askedPrice) {
        this.askedPrice = askedPrice;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
