package models;

import interfaces.Searchable;

public class Hotel implements Searchable {

    private int id;
    private String name;
    private int stars;

    public Hotel(int id, String name, int stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean matches(String keyword) {
        String normalized = keyword.toLowerCase();
        return name.toLowerCase().contains(normalized) ||
                String.valueOf(stars).contains(normalized);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + stars + " stars";
    }
}