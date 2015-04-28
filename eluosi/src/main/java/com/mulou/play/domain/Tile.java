package com.mulou.play.domain;

/**
 * Created by huamulou on 14-6-19.
 */
public class Tile {

    int x ;
    int y;
    int top;
    int left;
    int height;
    int width;

    public Tile(int left, int top, int x, int y) {
        this.left = left;
        this.top = top;
        this.y = y;
        this.x = x;
    }


    public Tile(Tile t) {
        this.left = t.getLeft();
        this.top = t.getTop();
        this.y = t.getY();
        this.x = t.getX();
        this.height = t.getHeight();
        this.width = t.getWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
