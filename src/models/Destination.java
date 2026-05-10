package models;

import interfaces.Searchable;

public class Destination implements Searchable {

    private int id;
    private String country;
    private String city;
    private String description;

    public Destination(int id, String country, String city, String description) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean matches(String keyword) {
        String normalized = keyword.toLowerCase();
        return country.toLowerCase().contains(normalized) ||
                city.toLowerCase().contains(normalized) ||
                description.toLowerCase().contains(normalized);
    }

    @Override
    public String toString() {
        return id + " | " + country + " | " + city + " | " + description;
    }
}
