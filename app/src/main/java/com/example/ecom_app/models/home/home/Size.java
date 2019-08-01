package com.example.ecom_app.models.home.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Size {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
