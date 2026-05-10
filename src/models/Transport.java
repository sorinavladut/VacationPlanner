package models;

import enums.TransportType;
import interfaces.Searchable;

public class Transport implements Searchable {

    private int id;
    private TransportType type;
    private double price;

    public Transport(int id, TransportType type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public TransportType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean matches(String keyword) {
        String normalized = keyword.toLowerCase();
        return type.name().toLowerCase().contains(normalized) ||
                String.valueOf(price).contains(normalized);
    }

    @Override
    public String toString() {
        return id + " | " + type + " | " + price;
    }
}