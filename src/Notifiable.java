public interface Notifiable {
    void notifyParkingLotIsFull(ParkingLot parkingLot);

    void notifyParkingLotIsAvailable(ParkingLot parkingLot);
}