package models;

import interfaces.Searchable;

public class TravelPackage implements Searchable {

    private int id;
    private String title;
    private Destination destination;
    private double price;
    private int availableSpots;

    public TravelPackage(int id, String title, Destination destination,
                         double price, int availableSpots) {
        this.id = id;
        this.title = title;
        this.destination = destination;
        this.price = price;
        this.availableSpots = availableSpots;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Destination getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void decreaseSpots() {
        if (availableSpots > 0) {
            availableSpots--;
        }
    }

    public void increaseSpots() {
        availableSpots++;
    }

    @Override
    public boolean matches(String keyword) {
        String normalized = keyword.toLowerCase();
        return title.toLowerCase().contains(normalized) ||
                destination.toString().toLowerCase().contains(normalized);
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + destination.getCity() + ", " + destination.getCountry() +
                " | Price: " + price + " | Spots: " + availableSpots;
    }
}
