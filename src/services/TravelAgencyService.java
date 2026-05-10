package services;

import enums.ReservationStatus;
import enums.TransportType;
import exceptions.*;
import models.*;

import java.util.*;

public class TravelAgencyService {

    private List<TravelPackage> packages = new ArrayList<>();
    private List<Reservation> reservations = new LinkedList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<Transport> transports = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<Itinerary> itineraries = new ArrayList<>();

    private Map<Integer, Tourist> tourists = new HashMap<>();
    private Map<Integer, Destination> destinations = new TreeMap<>();

    private AuditService audit = AuditService.getInstance();
    private CsvService csv = CsvService.getInstance();

    public TravelAgencyService() {
        loadData();
    }

    public void reloadData() {
        loadData();
        audit.log("reload_data");
    }

    public void saveData() {
        saveUsers();
        saveDestinations();
        savePackages();
        saveReservations();
        saveHotels();
        saveTransports();
        saveReviews();
        saveItineraries();
        audit.log("save_data");
    }

    private void loadData() {
        tourists.clear();
        destinations.clear();
        packages.clear();
        reservations.clear();
        hotels.clear();
        transports.clear();
        reviews.clear();
        itineraries.clear();

        loadTourists();
        loadDestinations();
        loadPackages();
        loadReservations();
        loadHotels();
        loadTransports();
        loadReviews();
        loadItineraries();
    }

    private void loadTourists() {
        List<String> lines = csv.read("data/users.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 4) {
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String email = tokens[2];
                String passport = tokens[3];
                tourists.put(id, new Tourist(id, name, email, passport));
            }
        }
    }

    private void loadDestinations() {
        List<String> lines = csv.read("data/destinations.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 4) {
                int id = Integer.parseInt(tokens[0]);
                String country = tokens[1];
                String city = tokens[2];
                String description = tokens[3];
                destinations.put(id, new Destination(id, country, city, description));
            }
        }
    }

    private void loadPackages() {
        List<String> lines = csv.read("data/packages.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 5) {
                int id = Integer.parseInt(tokens[0]);
                String title = tokens[1];
                int destinationId = Integer.parseInt(tokens[2]);
                double price = Double.parseDouble(tokens[3]);
                int spots = Integer.parseInt(tokens[4]);
                Destination destination = destinations.get(destinationId);
                if (destination != null) {
                    packages.add(new TravelPackage(id, title, destination, price, spots));
                }
            }
        }
    }

    private void loadReservations() {
        List<String> lines = csv.read("data/reservations.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 4) {
                int id = Integer.parseInt(tokens[0]);
                int touristId = Integer.parseInt(tokens[1]);
                int packageId = Integer.parseInt(tokens[2]);
                String statusToken = tokens[3];

                Tourist tourist = tourists.get(touristId);
                TravelPackage travelPackage = getPackageById(packageId);
                if (tourist != null && travelPackage != null) {
                    ReservationStatus status;
                    try {
                        status = ReservationStatus.valueOf(statusToken);
                    } catch (IllegalArgumentException ex) {
                        status = ReservationStatus.PENDING;
                    }
                    reservations.add(new Reservation(id, tourist, travelPackage, status));
                }
            }
        }
    }

    private void loadHotels() {
        List<String> lines = csv.read("data/hotels.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 3) {
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                int stars = Integer.parseInt(tokens[2]);
                hotels.add(new Hotel(id, name, stars));
            }
        }
    }

    private void loadTransports() {
        List<String> lines = csv.read("data/transports.csv");
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length >= 3) {
                int id = Integer.parseInt(tokens[0]);
                try {
                    TransportType type = TransportType.valueOf(tokens[1]);
                    double price = Double.parseDouble(tokens[2]);
                    transports.add(new Transport(id, type, price));
                } catch (IllegalArgumentException ex) {
                    // skip invalid transport entry
                }
            }
        }
    }

    private void loadReviews() {
        List<String> lines = csv.read("data/reviews.csv");
        for (String line : lines) {
            String[] tokens = line.split(",", 3);
            if (tokens.length >= 3) {
                int touristId = Integer.parseInt(tokens[0]);
                int rating = Integer.parseInt(tokens[1]);
                String comment = tokens[2];
                Tourist tourist = tourists.get(touristId);
                if (tourist != null) {
                    reviews.add(new Review(tourist, rating, comment));
                }
            }
        }
    }

    private void loadItineraries() {
        List<String> lines = csv.read("data/itineraries.csv");
        for (String line : lines) {
            String[] tokens = line.split(",", 2);
            if (tokens.length >= 2) {
                int id = Integer.parseInt(tokens[0]);
                Itinerary itinerary = new Itinerary(id);
                String[] activities = tokens[1].split(";");
                for (String activity : activities) {
                    if (!activity.isBlank()) {
                        itinerary.addActivity(activity.trim());
                    }
                }
                itineraries.add(itinerary);
            }
        }
    }

    public void addTourist(Tourist tourist) {
        tourists.put(tourist.getId(), tourist);
        audit.log("add_tourist");
    }

    public void showTourists() {
        tourists.values().forEach(System.out::println);
    }

    public Tourist getTouristById(int id) {
        return tourists.get(id);
    }

    public void addDestination(Destination destination) {
        destinations.put(destination.getId(), destination);
        audit.log("add_destination");
    }

    public void showDestinations() {
        destinations.values().forEach(System.out::println);
    }

    public Destination getDestinationById(int id) {
        return destinations.get(id);
    }

    public void addPackage(TravelPackage travelPackage) {
        packages.add(travelPackage);
        audit.log("add_package");
    }

    public void showPackages() {
        packages.forEach(System.out::println);
    }

    public TravelPackage getPackageById(int id) {
        for (TravelPackage travelPackage : packages) {
            if (travelPackage.getId() == id) {
                return travelPackage;
            }
        }
        return null;
    }

    public void reservePackage(int packageId, int touristId)
            throws PackageNotFoundException, NoAvailableSpotsException {
        Tourist tourist = tourists.get(touristId);
        if (tourist == null) {
            throw new PackageNotFoundException("Tourist not found");
        }
        reservePackage(packageId, tourist);
    }

    public void reservePackage(int packageId, Tourist tourist)
            throws PackageNotFoundException, NoAvailableSpotsException {
        TravelPackage found = getPackageById(packageId);
        if (found == null) {
            throw new PackageNotFoundException("Package not found");
        }
        if (found.getAvailableSpots() <= 0) {
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

    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public void cancelReservation(int reservationId)
            throws ReservationNotFoundException {
        Reservation reservation = getReservationById(reservationId);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found");
        }
        if (reservation.getStatus() != ReservationStatus.CANCELLED) {
            reservation.cancel();
            reservation.getTravelPackage().increaseSpots();
            audit.log("cancel_reservation");
        }
    }

    public void sortPackagesByPrice() {
        packages.sort(Comparator.comparingDouble(TravelPackage::getPrice));
        packages.forEach(System.out::println);
        audit.log("sort_packages");
    }

    public void searchPackages(String keyword) {
        boolean found = false;
        for (TravelPackage travelPackage : packages) {
            if (travelPackage.matches(keyword)) {
                travelPackage.search(keyword);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No packages found for '" + keyword + "'");
        }
        audit.log("search_packages");
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        audit.log("add_hotel");
    }

    public void showHotels() {
        hotels.forEach(System.out::println);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
        audit.log("add_transport");
    }

    public void showTransports() {
        transports.forEach(System.out::println);
    }

    public void addReview(Review review) {
        reviews.add(review);
        audit.log("add_review");
    }

    public void showReviews() {
        reviews.forEach(System.out::println);
    }

    public void addItinerary(Itinerary itinerary) {
        itineraries.add(itinerary);
        audit.log("add_itinerary");
    }

    public void showItineraries() {
        itineraries.forEach(System.out::println);
    }

    private void saveUsers() {
        List<String> lines = new ArrayList<>();
        for (Tourist tourist : tourists.values()) {
            lines.add(tourist.getId() + "," + tourist.getName() + "," + tourist.getEmail() + "," + tourist.getPassportNumber());
        }
        csv.overwrite("data/users.csv", lines);
    }

    private void saveDestinations() {
        List<String> lines = new ArrayList<>();
        for (Destination destination : destinations.values()) {
            lines.add(destination.getId() + "," + destination.getCountry() + "," + destination.getCity() + "," + destination.getDescription());
        }
        csv.overwrite("data/destinations.csv", lines);
    }

    private void savePackages() {
        List<String> lines = new ArrayList<>();
        for (TravelPackage travelPackage : packages) {
            lines.add(travelPackage.getId() + "," + travelPackage.getTitle() + "," + travelPackage.getDestination().getId() + "," + travelPackage.getPrice() + "," + travelPackage.getAvailableSpots());
        }
        csv.overwrite("data/packages.csv", lines);
    }

    private void saveReservations() {
        List<String> lines = new ArrayList<>();
        for (Reservation reservation : reservations) {
            lines.add(reservation.getId() + "," + reservation.getTourist().getId() + "," + reservation.getTravelPackage().getId() + "," + reservation.getStatus());
        }
        csv.overwrite("data/reservations.csv", lines);
    }

    private void saveHotels() {
        List<String> lines = new ArrayList<>();
        for (Hotel hotel : hotels) {
            lines.add(hotel.getId() + "," + hotel.getName() + "," + hotel.getStars());
        }
        csv.overwrite("data/hotels.csv", lines);
    }

    private void saveTransports() {
        List<String> lines = new ArrayList<>();
        for (Transport transport : transports) {
            lines.add(transport.getId() + "," + transport.getType() + "," + transport.getPrice());
        }
        csv.overwrite("data/transports.csv", lines);
    }

    private void saveReviews() {
        List<String> lines = new ArrayList<>();
        for (Review review : reviews) {
            String comment = review.getComment().replace(",", " ");
            lines.add(review.getTourist().getId() + "," + review.getRating() + "," + comment);
        }
        csv.overwrite("data/reviews.csv", lines);
    }

    private void saveItineraries() {
        List<String> lines = new ArrayList<>();
        for (Itinerary itinerary : itineraries) {
            lines.add(itinerary.getId() + "," + String.join(";", itinerary.getActivities()));
        }
        csv.overwrite("data/itineraries.csv", lines);
    }
}
