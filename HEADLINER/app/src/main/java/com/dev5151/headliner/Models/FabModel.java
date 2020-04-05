package com.dev5151.headliner.Models;

public class FabModel {
    private String title;
    private int img;

    public FabModel() {
    }

    public FabModel(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

