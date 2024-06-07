package com.ViewandController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Car;
import com.model.Rental;
import com.model.User;

public class CarRental {
    private List<Car> cars;
    private List<User> users;
    private List<Rental> rentals;

    public CarRental() {
        cars = new ArrayList<>();
        users = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Car findCarById(String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void rentCar(String userId, String carId) {
        User user = findUserById(userId);
        Car car = findCarById(carId);

        if (user == null || car == null) {
            System.out.println("User or Car not found.");
            return;
        }

        if (!car.isAvailable()) {
            System.out.println("Car is not available.");
            return;
        }

        car.setAvailable(false);
        Rental rental = new Rental("R" + (rentals.size() + 1), user, car, LocalDate.now());
        rentals.add(rental);
        user.addRental(rental);

        System.out.println("Car rented successfully.");
    }

    public void returnCar(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId)) {
                if (rental.getReturnDate() != null) {
                    System.out.println("Car has already been returned.");
                    return;
                }
                rental.setReturnDate(LocalDate.now());
                rental.getCar().setAvailable(true);
                System.out.println("Car returned successfully.");
                return;
            }
        }
        System.out.println("Rental not found.");
    }

    public void viewRentalHistory(String userId) {
        User user = findUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Rental History for " + user.getName() + ":");
        for (Rental rental : user.getRentalHistory()) {
            System.out.println("Rental ID: " + rental.getRentalId() +
                               ", Car: " + rental.getCar().getMake() + " " + rental.getCar().getModel() +
                               ", Rental Date: " + rental.getRentalDate() +
                               ", Return Date: " + rental.getReturnDate());
        }
    }

    public void viewActiveRentals(String userId) {
        User user = findUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Active Rentals for " + user.getName() + ":");
        for (Rental rental : user.getRentalHistory()) {
            if (rental.getReturnDate() == null) {
                System.out.println("Rental ID: " + rental.getRentalId() +
                                   ", Car: " + rental.getCar().getMake() + " " + rental.getCar().getModel() +
                                   ", Rental Date: " + rental.getRentalDate());
            }
        }
    }

    public static void main(String[] args) {
        CarRental system = new CarRental();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainChoice) {
                case 1:
                    while (true) {
                        System.out.println("Admin Menu:");
                        System.out.println("1. Add a User");
                        System.out.println("2. Add a Car");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (adminChoice) {
                            case 1:
                                System.out.print("Enter User ID: ");
                                String userId = scanner.nextLine();
                                System.out.print("Enter User Name: ");
                                String userName = scanner.nextLine();
                                system.addUser(new User(userId, userName));
                                System.out.println("User added successfully.");
                                break;
                            case 2:
                                System.out.print("Enter Car ID: ");
                                String carId = scanner.nextLine();
                                System.out.print("Enter Car Make: ");
                                String carMake = scanner.nextLine();
                                System.out.print("Enter Car Model: ");
                                String carModel = scanner.nextLine();
                                system.addCar(new Car(carId, carMake, carModel));
                                System.out.println("Car added successfully.");
                                break;
                            case 3:
                                System.out.println("Returning to Main Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                        if (adminChoice == 3) break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("User Menu:");
                        System.out.println("1. Rent a Car");
                        System.out.println("2. Return a Car");
                        System.out.println("3. View Rental History");
                        System.out.println("4. View Active Rentals");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (userChoice) {
                            case 1:
                                System.out.print("Enter User ID: ");
                                String userId = scanner.nextLine();
                                System.out.print("Enter Car ID: ");
                                String carId = scanner.nextLine();
                                system.rentCar(userId, carId);
                                break;
                            case 2:
                                System.out.print("Enter User ID: ");
                                userId = scanner.nextLine();
                                System.out.print("Enter Rental ID: ");
                                String rentalId = scanner.nextLine();
                                system.returnCar(rentalId);
                                break;
                            case 3:
                                System.out.print("Enter User ID: ");
                                userId = scanner.nextLine();
                                system.viewRentalHistory(userId);
                                break;
                            case 4:
                                System.out.print("Enter User ID: ");
                                userId = scanner.nextLine();
                                system.viewActiveRentals(userId);
                                break;
                            case 5:
                                System.out.println("Returning to Main Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                        if (userChoice == 5) break;
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
