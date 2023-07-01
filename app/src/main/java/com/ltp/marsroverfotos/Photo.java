package com.ltp.marsroverfotos;

public class Photo {
    private String imageUrl;
    private String date;
    private String name;
    private String launchDate;
    private String arrivalDate;
    private String state;

    public Photo(String imageUrl, String date, String name, String launchDate, String arrivalDate, String state) {
        this.imageUrl = imageUrl;
        this.date = date;
        this.name = name;
        this.launchDate = launchDate;
        this.arrivalDate = arrivalDate;
        this.state = state;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
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


