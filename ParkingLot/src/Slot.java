public class Slot {
    private final int slotNo;
    private Vehicle vehicle;

    public Slot(int slotNo, Vehicle vehicle) {
        this.slotNo = slotNo;
        this.vehicle = vehicle;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
