public class CarParkingChargeStrategy implements ParkingChargeStrategy {

    @Override
    public long getParkingCharge(long mins) {
        return mins * 2;
    }
}
