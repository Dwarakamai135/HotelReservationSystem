import java.io.*;
import java.util.Scanner;

class Room {

    int roomNumber;
    String roomType;
    double price;
    boolean booked;

    String customerName;
    String phoneNumber;
    String bookingId;

    public Room(int roomNumber, String roomType, double price) {

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;

        this.booked = false;

        this.customerName = "";
        this.phoneNumber = "";
        this.bookingId = "";
    }
}

public class HotelReservationSystem {

    // View Rooms
    public static void displayRooms(Room[] rooms) {

        System.out.println("\n===== ROOM DETAILS =====");

        for (Room room : rooms) {

            System.out.println(
                    room.roomNumber + " | " +
                    room.roomType + " | Rs." +
                    room.price + " | " +
                    (room.booked ? "Booked" : "Available"));
        }
    }

    // Save Bookings to File
    public static void saveBookings(Room[] rooms) {

        try {

            FileWriter writer = new FileWriter("bookings.txt");

            for (Room room : rooms) {

                writer.write(
                        room.roomNumber + "," +
                        room.roomType + "," +
                        room.price + "," +
                        room.booked + "," +
                        room.customerName + "," +
                        room.phoneNumber + "," +
                        room.bookingId + "\n");
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving bookings.");
        }
    }

    // Book Room
    public static void bookRoom(Room[] rooms,
                                int roomNo,
                                String customerName,
                                String phoneNumber) {

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (!room.booked) {

                    room.booked = true;

                    room.customerName = customerName;
                    room.phoneNumber = phoneNumber;
                    room.bookingId = "B" + roomNo;

                    saveBookings(rooms);

                    System.out.println("Payment Successful!");
                    System.out.println("Booking ID: " + room.bookingId);
                    System.out.println("Room booked successfully.");

                } else {

                    System.out.println("Room is already booked.");
                }

                return;
            }
        }

        System.out.println("Room not found.");
    }

    // Cancel Booking
    public static void cancelBooking(Room[] rooms, int roomNo) {

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (room.booked) {

                    room.booked = false;

                    room.customerName = "";
                    room.phoneNumber = "";
                    room.bookingId = "";

                    saveBookings(rooms);

                    System.out.println("Booking cancelled successfully.");

                } else {

                    System.out.println("Room is not booked.");
                }

                return;
            }
        }

        System.out.println("Room not found.");
    }

    // View Booking Details
    public static void viewBookings(Room[] rooms) {

        System.out.println("\n===== BOOKED ROOMS =====");

        boolean found = false;

        for (Room room : rooms) {

            if (room.booked) {

                System.out.println(
                        "Booking ID: " + room.bookingId +
                        " | Room: " + room.roomNumber +
                        " | Type: " + room.roomType +
                        " | Customer: " + room.customerName +
                        " | Phone: " + room.phoneNumber);

                found = true;
            }
        }

        if (!found) {

            System.out.println("No rooms are currently booked.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Room[] rooms = {

                new Room(101, "Standard", 1000),

                new Room(102, "Deluxe", 2000),

                new Room(103, "Suite", 3000)

        };

        while (true) {

            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");

            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                displayRooms(rooms);

            }
            else if (choice == 2) {

                System.out.print("Enter Room Number: ");
                int roomNo = sc.nextInt();

                sc.nextLine();

                System.out.print("Enter Customer Name: ");
                String customerName = sc.nextLine();

                System.out.print("Enter Phone Number: ");
                String phoneNumber = sc.nextLine();

                bookRoom(rooms, roomNo, customerName, phoneNumber);

            }
            else if (choice == 3) {

                System.out.print("Enter Room Number: ");
                int roomNo = sc.nextInt();

                cancelBooking(rooms, roomNo);

            }
            else if (choice == 4) {

                viewBookings(rooms);

            }
            else if (choice == 5) {

                saveBookings(rooms);

                System.out.println("Thank You!");
                break;

            }
            else {

                System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}