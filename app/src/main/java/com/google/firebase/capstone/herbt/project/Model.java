package com.google.firebase.capstone.herbt.project;

public class Model {
    String Bisname;
    String Engname;
    String imageUrl;

    public Model()

    {
    }

    public String getBisname() {
        return Bisname;
    }

    public void setBisname(String bisname) {
        Bisname = bisname;
    }

    public String getEngname() {
        return Engname;
    }

    public void setEngname(String engname) {
        Engname = engname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
