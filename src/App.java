import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Notifiable owner = new Owner();
        Notifiable trafficCop = new TrafficCop();
        
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        
        parkingLot1.addSubscriber(owner);
        parkingLot1.addSubscriber(trafficCop);
        parkingLot2.addSubscriber(owner);
        parkingLot2.addSubscriber(trafficCop);
        
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        Car car1 = new Car() {};
        Car car2 = new Car() {};
        Car car3 = new Car() {};

        parkingAttendant.park(car1);
        parkingAttendant.park(car2);
        parkingAttendant.park(car3);
    }
}