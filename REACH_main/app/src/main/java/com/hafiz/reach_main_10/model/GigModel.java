package com.hafiz.reach_main_10.model;

public class GigModel {

    int pic ;
    String text;

    public GigModel(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public String getText() {
        return text;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setText(String text) {
        this.text = text;
    }
}
