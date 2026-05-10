import exceptions.*;
import models.*;
import services.TravelAgencyService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TravelAgencyService service = new TravelAgencyService();

        Tourist tourist = new Tourist(
                1,
                "Alex",
                "alex@gmail.com",
                "RO123456"
        );

        service.addTourist(tourist);

        Destination paris = new Destination(
                1,
                "France",
                "Paris",
                "Romantic destination"
        );

        service.addDestination(paris);

        TravelPackage package1 = new TravelPackage(
                1,
                "Paris Holiday",
                paris,
                1200,
                5
        );

        service.addPackage(package1);

        boolean running = true;

        while(running) {

            System.out.println();
            System.out.println("===== VACATION PLANNER =====");
            System.out.println("1. Show tourists");
            System.out.println("2. Show destinations");
            System.out.println("3. Show packages");
            System.out.println("4. Reserve package");
            System.out.println("5. Show reservations");
            System.out.println("6. Sort packages");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            switch(option) {

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

                    try {

                        System.out.print("Package ID: ");
                        int id = scanner.nextInt();

                        service.reservePackage(id, tourist);

                        System.out.println("Reservation successful");

                    } catch(PackageNotFoundException |
                            NoAvailableSpotsException e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    service.showReservations();
                    break;

                case 6:
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
}