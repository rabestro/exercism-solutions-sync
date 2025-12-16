public class JedliksToyCar {
    public static final int METERS_PER_ONE_PERCENT = 20;
    public static final int FULL_CHARGE_IN_PERCENT = 100;
    private int chargeInPercent = FULL_CHARGE_IN_PERCENT;

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return "Driven " + distance() + " meters";
    }

    public String batteryDisplay() {
        if (isBatteryEmpty()) {
            return "Battery empty";
        }
        return "Battery at " + chargeInPercent + "%";
    }

    public void drive() {
        if (!isBatteryEmpty()) {
            chargeInPercent--;
        }
    }

    private int distance() {
        return METERS_PER_ONE_PERCENT * (FULL_CHARGE_IN_PERCENT - chargeInPercent);
    }

    private boolean isBatteryEmpty() {
        return chargeInPercent == 0;
    }
}
