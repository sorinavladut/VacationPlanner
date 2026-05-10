package services;

import exceptions.*;
import models.*;

import java.util.*;

public class TravelAgencyService {

    private List<TravelPackage> packages = new ArrayList<>();
    private List<Reservation> reservations = new LinkedList<>();

    private Map<Integer, Tourist> tourists = new HashMap<>();
    private Map<Integer, Destination> destinations = new TreeMap<>();

    private AuditService audit = AuditService.getInstance();

    public void addTourist(Tourist tourist) {
        tourists.put(tourist.getId(), tourist);
        audit.log("add_tourist");
    }

    public void showTourists() {
        tourists.values().forEach(System.out::println);
    }

    public void addDestination(Destination destination) {
        destinations.put(destination.getId(), destination);
        audit.log("add_destination");
    }

    public void showDestinations() {
        destinations.values().forEach(System.out::println);
    }

    public void addPackage(TravelPackage travelPackage) {
        packages.add(travelPackage);
        audit.log("add_package");
    }

    public void showPackages() {
        packages.forEach(System.out::println);
    }

    public void reservePackage(int packageId, Tourist tourist)
            throws PackageNotFoundException, NoAvailableSpotsException {

        TravelPackage found = null;

        for(TravelPackage p : packages) {
            if(p.getId() == packageId) {
                found = p;
            }
        }

        if(found == null) {
            throw new PackageNotFoundException("Package not found");
        }

        if(found.getAvailableSpots() <= 0) {
            throw new NoAvailableSpotsException("No spots available");
        }

        found.decreaseSpots();

        Reservation reservation = new Reservation(
                reservations.size() + 1,
                tourist,
                found
        );

        reservation.reserve();
        reservations.add(reservation);

        audit.log("reserve_package");
    }

    public void showReservations() {
        reservations.forEach(System.out::println);
    }

    public void sortPackagesByPrice() {
        packages.sort(Comparator.comparingDouble(TravelPackage::getPrice));
        packages.forEach(System.out::println);
    }
}