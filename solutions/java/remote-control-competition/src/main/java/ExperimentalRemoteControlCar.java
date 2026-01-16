public final class ExperimentalRemoteControlCar implements RemoteControlCar {
    private static final int DISTANCE_PER_DRIVE = 20;
    private int distance;

    public void drive() {
        distance += DISTANCE_PER_DRIVE;
    }

    public int getDistanceTravelled() {
        return distance;
    }
}
