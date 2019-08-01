package com.example.ecom_app.models.home.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
