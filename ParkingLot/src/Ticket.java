import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private final String vehicleNo;
    private final VehicleType vehicleType;
    private final int slotNo;


    private final LocalDateTime inTime;
    private LocalDateTime outTime;
    private long parkingCharge;

    public LocalDateTime getInTime() {
        return inTime;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Ticket(String vehicleNo, VehicleType vehicleType, int slotNo, LocalDateTime inTime, LocalDateTime outTime, long parkingCharge) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.slotNo = slotNo;
        this.inTime = inTime;
        this.outTime = outTime;
        this.parkingCharge = parkingCharge;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public void setParkingCharge(long charge) {
        this.parkingCharge = charge;
    }

    @Override
    public String toString() {
        return "Ticket:"
                + "\nVehicle no: " + vehicleNo
                + "\nVehicle type: " + vehicleType
                + "\nSlot no.: " + slotNo
                + "\nIn time: " + inTime
                + (Objects.equals(outTime, null) ? "" : ("\nOut time: " + outTime + "\nOut charge: " + parkingCharge));
    }
}
