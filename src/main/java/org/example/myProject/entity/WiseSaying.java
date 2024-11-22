package org.example.myProject.entity;

public class WiseSaying {
    private final int num;
    private String author;
    private String saying;

    public WiseSaying(int num, String author, String saying) {
        this.num = num;
        this.author = author;
        this.saying = saying;
    }

    public int getNum() {
        return this.num;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getSaying() {
        return this.saying;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    @Override
    public String toString() {
        return this.num + " / " + this.author + " / " + this.saying;
    }
}
