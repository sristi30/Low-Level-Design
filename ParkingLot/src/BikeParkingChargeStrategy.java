public class BikeParkingChargeStrategy implements ParkingChargeStrategy {
    @Override
    public long getParkingCharge(long mins) {
        return mins;
    }
}
