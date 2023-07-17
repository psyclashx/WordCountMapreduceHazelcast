package entity;

import java.io.Serializable;

public class Buch implements Serializable {

    private int id;
    private String text;
    private String title;

    public Buch() {

    }

    public Buch(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Buch{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
