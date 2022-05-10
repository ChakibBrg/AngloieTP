package com.tp.angloie;

abstract public class Case {
    protected String color;

    public Case(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    abstract void action ();
}
