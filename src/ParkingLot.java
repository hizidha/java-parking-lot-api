import java.util.ArrayList;
import java.util.List;

import Exceptions.CarAlreadyParkedException;
import Exceptions.CarNotParkedException;
import Exceptions.ParkingLotFullException;

public class ParkingLot {
    private final int capacity;
    private final List<Car> parkedCars;
    private final List<Notifiable> notifiables;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
        this.notifiables = new ArrayList<>();
    }

    public void addSubscriber(Notifiable subscriber){
        this.notifiables.add(subscriber);
    }

    public void park(Car car) {
        if(this.isParked(car)) {
            throw new CarAlreadyParkedException();
        }

        if (this.isFull()) {
            throw new ParkingLotFullException();
        }

        this.parkedCars.add(car);
        if (this.isFull()) {
            for (Notifiable notifiable : notifiables) {
                notifiable.notifyParkingLotIsFull(this);
            }
        }
    }

    public void unpark(Car car) {
        if(!this.isParked(car)) {
            throw new CarNotParkedException();
        }

        boolean wasFull = this.isFull();
        this.parkedCars.remove(car);

        if (wasFull) {
            for (Notifiable notifiable : notifiables) {
                notifiable.notifyParkingLotIsAvailable(this);
            }
        }
    }

    private boolean isFull() {
        return this.parkedCars.size() == this.capacity;
    }

    public boolean isParked(Car car) {
        return this.parkedCars.contains(car);
    }

    @Override
    public String toString() {
        return "Car successfully entered the parking lot.";
    }
}