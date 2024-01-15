import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.PriorityQueue;

public class VehicleParker {
    private VehicleParker() {

    }

    static final CarParkingSpace carParkingSpace = new CarParkingSpace(LotSize.CAR_LOT_SIZE);
    static final BikeParkingSpace bikeParkingSpace = new BikeParkingSpace(LotSize.BIKE_LOT_SIZE);

    public static Ticket park(Vehicle vehicle) {
        ParkingSpace parkingSpace = getParkingSpace(vehicle.getVehicleType());
        if (Objects.equals(parkingSpace, null)) {
            return null;
        }
        PriorityQueue<Slot> emptySlotsQueue = parkingSpace.getEmptySlotsQueue();
        if (emptySlotsQueue.isEmpty()) {
            return null;
        }

        Slot slot = emptySlotsQueue.poll();
        slot.setVehicle(vehicle);
        return new Ticket(vehicle.getVehicleNo(), vehicle.getVehicleType(), slot.getSlotNo(), LocalDateTime.now(), null, -1);
    }

    public static Ticket unpark(Ticket ticket, ParkingChargeStrategy parkingChargeStrategy) {
        VehicleType vehicleType = ticket.getVehicleType();
        ParkingSpace parkingSpace = getParkingSpace(vehicleType);
        if (Objects.equals(parkingSpace, null)) {
            return null;
        }
        Slot[] slots = parkingSpace.getSlots();
        LocalDateTime outTime = LocalDateTime.now();
        LocalDateTime inTime = ticket.getInTime();
        Slot slot = slots[ticket.getSlotNo()];
        slot.setVehicle(null);
        parkingSpace.getEmptySlotsQueue().add(slot);

        long parkingCharge = parkingChargeStrategy.getParkingCharge(Duration.between(inTime, outTime).toMinutes());
        ticket.setOutTime(outTime);
        ticket.setParkingCharge(parkingCharge);
        return ticket;
    }

    private static ParkingSpace getParkingSpace(VehicleType vehicleType) {
        if (vehicleType == VehicleType.CAR) {
            return carParkingSpace;
        } else if (vehicleType == VehicleType.BIKE) {
            return bikeParkingSpace;
        }
        return null;
    }
}
