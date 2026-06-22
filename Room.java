public class Room {

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