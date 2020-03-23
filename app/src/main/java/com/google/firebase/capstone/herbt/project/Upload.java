package com.google.firebase.capstone.herbt.project;

public class Upload {
    private String HerbalDescription;
    private String engname;
    private String bisname;
    private String imageUrl;

    public Upload() {
    }

    public Upload(String bisname, String imageUrl) {
        if (bisname.trim().equals("")) {
            bisname = "No name";
        }
        this.bisname = bisname;
        this.imageUrl = imageUrl;
    }

    public String getHerbalDescription() {
        return HerbalDescription;
    }

    public void setHerbalDescription(String herbalDescription) {
        HerbalDescription = herbalDescription;
    }

    public String getBisname() {
        return bisname;
    }

    public void setBisname(String bisname) {
        this.bisname = bisname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }
}