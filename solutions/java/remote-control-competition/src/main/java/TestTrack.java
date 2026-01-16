import java.util.Collection;
import java.util.List;

public class TestTrack {

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(Collection<ProductionRemoteControlCar> cars) {
        return cars.stream().sorted().toList();
    }
}
