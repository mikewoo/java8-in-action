package com.mikewoo.study.java8.domain;

/**
 * @author : Eric Gui
 * @date 2018/7/16 11:25
 */
public class Apple {

    private String color;

    private long weight;

    private String origin;

    public Apple() {
    }

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
        this.origin = "anhui";
    }

    public Apple(String color, long weight, String origin) {
        this.color = color;
        this.weight = weight;
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", origin='" + origin + '\'' +
                '}';
    }
}
