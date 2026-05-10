package models;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    private int id;
    private List<String> activities = new ArrayList<>();

    public Itinerary(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addActivity(String activity) {
        activities.add(activity);
    }

    public List<String> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return id + " | " + activities.toString();
    }
}