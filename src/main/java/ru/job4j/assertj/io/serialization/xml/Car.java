package ru.job4j.assertj.io.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean automatic;
    private int power;
    private String name;
    private Client client;
    @XmlElementWrapper(name = "operationses")
    @XmlElement(name = "operation")
    private String[] operation;

    public Car(boolean automatic, int power, String name, Client client, String[] operation) {
        this.automatic = automatic;
        this.power = power;
        this.name = name;
        this.client = client;
        this.operation = operation;
    }

    public Car() {
    }
}
