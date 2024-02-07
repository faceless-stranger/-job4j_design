package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;
import ru.job4j.serialization.json.B;

public class A {
    private B b;

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

}