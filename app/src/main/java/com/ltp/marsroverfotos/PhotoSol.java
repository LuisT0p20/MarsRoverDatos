package com.ltp.marsroverfotos;

public class PhotoSol {
    private String imageUrl;
    private String sol;
    private String name;
    private String launchDate;
    private String arrivalDate;
    private String state;

    public PhotoSol(String imageUrl, String date, String name, String launchDate, String arrivalDate, String state) {
        this.imageUrl = imageUrl;
        this.sol = date;
        this.name = name;
        this.launchDate = launchDate;
        this.arrivalDate = arrivalDate;
        this.state = state;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSol() {
        return sol;
    }

    public String getName() {
        return name;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getState() {
        return state;
    }
}



