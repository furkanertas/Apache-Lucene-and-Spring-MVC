package com.lucene.mvc.data.entities;

/**
 * Created by Toshiba on 20.7.2016.
 */
public class Text {
    private String text;
    private int id;

    public Text(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
