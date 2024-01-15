public class Main {
    public static void main(String[] args) throws InterruptedException {
        Vehicle vehicle1 = new Vehicle("WB1234", VehicleType.CAR);
        Ticket ticket1 = VehicleParker.park(vehicle1);
        System.out.println(ticket1);
        Vehicle vehicle2 = new Vehicle("KN1123", VehicleType.BIKE);
        Ticket ticket2 = VehicleParker.park(vehicle2);
        System.out.println(ticket2);
        Vehicle vehicle3 = new Vehicle("GJ1123", VehicleType.BIKE);
        Ticket ticket3 = VehicleParker.park(vehicle3);
        System.out.println(ticket3);
        Thread.sleep(1000 * 60);
        Ticket ticket4 = VehicleParker.unpark(ticket1, new CarParkingChargeStrategy());
        System.out.println(ticket4);
        Ticket ticket5 = VehicleParker.unpark(ticket2, new BikeParkingChargeStrategy());
        System.out.println(ticket5);
        Vehicle vehicle4 = new Vehicle("KN1111", VehicleType.BIKE);
        Ticket ticket6 = VehicleParker.park(vehicle4);
        System.out.println(ticket6);
    }
}