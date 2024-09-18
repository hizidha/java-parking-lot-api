import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Exceptions.CarAlreadyParkedException;
import Exceptions.CarNotParkedException;
import Exceptions.NoParkingLotAvailableException;

public class ParkingAttendant implements Notifiable {
    private final List<ParkingLot> parkingLots;
    private final List<ParkingLot> availableParkingLots;

    public ParkingAttendant(List<ParkingLot> parkingLots, List<ParkingLot> availableParkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
        this.availableParkingLots = new ArrayList<>(availableParkingLots);
        this.subscribeTo(parkingLots);
    }

    private void subscribeTo(List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            parkingLot.addSubscriber(this);
        }
    }

    private Optional<ParkingLot> findParkedLot(Car car) {
        return this.parkingLots.stream()
                .filter(parkingLot -> parkingLot.isParked(car))
                .findFirst();
    }

    @Override
    public void notifyParkingLotIsFull(ParkingLot parkingLot) {
        this.availableParkingLots.remove(parkingLot);
    }

    @Override
    public void notifyParkingLotIsAvailable(ParkingLot parkingLot) {
        this.availableParkingLots.add(parkingLot);
    }

    public void park(Car car) {
        Optional<ParkingLot> parkedLot = findParkedLot(car);

        if (parkedLot.isPresent()) {
            throw new CarAlreadyParkedException();
        }

        if (this.availableParkingLots.isEmpty()) {
            throw new NoParkingLotAvailableException();
        }

        this.availableParkingLots.getFirst().park(car);
    }

    public void unpark(Car car) {
        Optional<ParkingLot> parkedLot = findParkedLot(car);

        if (parkedLot.isPresent()) {
            parkedLot.get().unpark(car);
        }

        if (!parkedLot.isPresent()) {
            throw new CarNotParkedException();
        }
    }
}