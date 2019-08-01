package com.example.ecom_app.models.home.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Home  {
    @SerializedName("new_arrival")
    @Expose
    private List<NewArrival> newArrival = null;
    @SerializedName("top_categories")
    @Expose
    private List<TopCategory> topCategories = null;
    @SerializedName("best_seller")
    @Expose
    private List<BestSeller> bestSeller = null;
    @SerializedName("hot_deals")
    @Expose
    private List<Object> hotDeals = null;
    @SerializedName("side_menu_categories")
    @Expose
    private List<SideMenuCategory> sideMenuCategories = null;

    public List<NewArrival> getNewArrival() {
        return newArrival;
    }

    public void setNewArrival(List<NewArrival> newArrival) {
        this.newArrival = newArrival;
    }

    public List<TopCategory> getTopCategories() {
        return topCategories;
    }

    public void setTopCategories(List<TopCategory> topCategories) {
        this.topCategories = topCategories;
    }



    public List<BestSeller> getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(List<BestSeller> bestSeller) {
        this.bestSeller = bestSeller;
    }

    public List<Object> getHotDeals() {
        return hotDeals;
    }

    public void setHotDeals(List<Object> hotDeals) {
        this.hotDeals = hotDeals;
    }

    public List<SideMenuCategory> getSideMenuCategories() {
        return sideMenuCategories;
    }

    public void setSideMenuCategories(List<SideMenuCategory> sideMenuCategories) {
        this.sideMenuCategories = sideMenuCategories;
    }

}
