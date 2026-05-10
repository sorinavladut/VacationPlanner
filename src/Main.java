import enums.TransportType;
import exceptions.*;
import models.*;
import services.TravelAgencyService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelAgencyService service = new TravelAgencyService();

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== VACATION PLANNER =====");
            System.out.println("1. Show tourists");
            System.out.println("2. Show destinations");
            System.out.println("3. Show packages");
            System.out.println("4. Reserve package");
            System.out.println("5. Cancel reservation");
            System.out.println("6. Search packages");
            System.out.println("7. Add tourist");
            System.out.println("8. Add destination");
            System.out.println("9. Add package");
            System.out.println("10. Show hotels");
            System.out.println("11. Add hotel");
            System.out.println("12. Show transports");
            System.out.println("13. Add transport");
            System.out.println("14. Show reviews");
            System.out.println("15. Add review");
            System.out.println("16. Show itineraries");
            System.out.println("17. Add itinerary");
            System.out.println("18. Reload CSV data");
            System.out.println("19. Save data to CSV");
            System.out.println("20. Sort packages by price");
            System.out.println("0. Exit");
            System.out.print("Option: ");

            int option = readInteger(scanner);
            switch (option) {
                case 1:
                    service.showTourists();
                    break;
                case 2:
                    service.showDestinations();
                    break;
                case 3:
                    service.showPackages();
                    break;
                case 4:
                    reservePackage(scanner, service);
                    break;
                case 5:
                    cancelReservation(scanner, service);
                    break;
                case 6:
                    searchPackages(scanner, service);
                    break;
                case 7:
                    addTourist(scanner, service);
                    break;
                case 8:
                    addDestination(scanner, service);
                    break;
                case 9:
                    addPackage(scanner, service);
                    break;
                case 10:
                    service.showHotels();
                    break;
                case 11:
                    addHotel(scanner, service);
                    break;
                case 12:
                    service.showTransports();
                    break;
                case 13:
                    addTransport(scanner, service);
                    break;
                case 14:
                    service.showReviews();
                    break;
                case 15:
                    addReview(scanner, service);
                    break;
                case 16:
                    service.showItineraries();
                    break;
                case 17:
                    addItinerary(scanner, service);
                    break;
                case 18:
                    service.reloadData();
                    System.out.println("CSV data reloaded.");
                    break;
                case 19:
                    service.saveData();
                    System.out.println("Data saved to CSV.");
                    break;
                case 20:
                    service.sortPackagesByPrice();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

        scanner.close();
    }

    private static int readInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter a valid number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Please enter a valid decimal number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static void reservePackage(Scanner scanner, TravelAgencyService service) {
        System.out.print("Package ID: ");
        int packageId = readInteger(scanner);
        System.out.print("Tourist ID: ");
        int touristId = readInteger(scanner);
        try {
            service.reservePackage(packageId, touristId);
            System.out.println("Reservation successful");
        } catch (PackageNotFoundException | NoAvailableSpotsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cancelReservation(Scanner scanner, TravelAgencyService service) {
        System.out.print("Reservation ID: ");
        int reservationId = readInteger(scanner);
        try {
            service.cancelReservation(reservationId);
            System.out.println("Reservation cancelled.");
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchPackages(Scanner scanner, TravelAgencyService service) {
        System.out.print("Search keyword: ");
        String keyword = scanner.nextLine();
        service.searchPackages(keyword);
    }

    private static void addTourist(Scanner scanner, TravelAgencyService service) {
        System.out.print("Tourist ID: ");
        int id = readInteger(scanner);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Passport number: ");
        String passport = scanner.nextLine();
        service.addTourist(new Tourist(id, name, email, passport));
        System.out.println("Tourist added.");
    }

    private static void addDestination(Scanner scanner, TravelAgencyService service) {
        System.out.print("Destination ID: ");
        int id = readInteger(scanner);
        System.out.print("Country: ");
        String country = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        service.addDestination(new Destination(id, country, city, description));
        System.out.println("Destination added.");
    }

    private static void addPackage(Scanner scanner, TravelAgencyService service) {
        System.out.print("Package ID: ");
        int id = readInteger(scanner);
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Destination ID: ");
        int destinationId = readInteger(scanner);
        Destination destination = service.getDestinationById(destinationId);
        if (destination == null) {
            System.out.println("Destination not found.");
            return;
        }
        System.out.print("Price: ");
        double price = readDouble(scanner);
        System.out.print("Available spots: ");
        int spots = readInteger(scanner);
        service.addPackage(new TravelPackage(id, title, destination, price, spots));
        System.out.println("Package added.");
    }

    private static void addHotel(Scanner scanner, TravelAgencyService service) {
        System.out.print("Hotel ID: ");
        int id = readInteger(scanner);
        System.out.print("Hotel name: ");
        String name = scanner.nextLine();
        System.out.print("Stars: ");
        int stars = readInteger(scanner);
        service.addHotel(new Hotel(id, name, stars));
        System.out.println("Hotel added.");
    }

    private static void addTransport(Scanner scanner, TravelAgencyService service) {
        System.out.print("Transport ID: ");
        int id = readInteger(scanner);
        System.out.print("Transport type (PLANE, TRAIN, BUS): ");
        String typeInput = scanner.nextLine().trim().toUpperCase();
        try {
            TransportType type = TransportType.valueOf(typeInput);
            System.out.print("Price: ");
            double price = readDouble(scanner);
            service.addTransport(new Transport(id, type, price));
            System.out.println("Transport added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid transport type.");
        }
    }

    private static void addReview(Scanner scanner, TravelAgencyService service) {
        System.out.print("Tourist ID: ");
        int touristId = readInteger(scanner);
        Tourist tourist = service.getTouristById(touristId);
        if (tourist == null) {
            System.out.println("Tourist not found.");
            return;
        }
        System.out.print("Rating (1-5): ");
        int rating = readInteger(scanner);
        System.out.print("Comment: ");
        String comment = scanner.nextLine();
        service.addReview(new Review(tourist, rating, comment));
        System.out.println("Review added.");
    }

    private static void addItinerary(Scanner scanner, TravelAgencyService service) {
        System.out.print("Itinerary ID: ");
        int id = readInteger(scanner);
        System.out.print("Activities (semicolon separated): ");
        String line = scanner.nextLine();
        Itinerary itinerary = new Itinerary(id);
        for (String activity : line.split(";")) {
            if (!activity.isBlank()) {
                itinerary.addActivity(activity.trim());
            }
        }
        service.addItinerary(itinerary);
        System.out.println("Itinerary added.");
    }
}
