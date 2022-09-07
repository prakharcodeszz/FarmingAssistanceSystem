package com.fas.farmer.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddProductRequest{

    @NotBlank
    private String name;
    @Min(1)
    private Long quantity;
    @Min(1)
    private double price;
    @NotNull
    @Min(1)
    private Long farmerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

}