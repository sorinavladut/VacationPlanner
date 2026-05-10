package models;

import enums.ReservationStatus;
import interfaces.Bookable;

public class Reservation implements Bookable {

    private int id;
    private Tourist tourist;
    private TravelPackage travelPackage;
    private ReservationStatus status;

    public Reservation(int id, Tourist tourist, TravelPackage travelPackage) {
        this(id, tourist, travelPackage, ReservationStatus.PENDING);
    }

    public Reservation(int id, Tourist tourist, TravelPackage travelPackage, ReservationStatus status) {
        this.id = id;
        this.tourist = tourist;
        this.travelPackage = travelPackage;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    @Override
    public void reserve() {
        status = ReservationStatus.CONFIRMED;
    }

    @Override
    public void cancel() {
        status = ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return id + " | " + tourist.getName() + " | " + travelPackage.getTitle() + " | " + status;
    }
}
