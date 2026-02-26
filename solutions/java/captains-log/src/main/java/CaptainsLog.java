import java.util.Random;

record CaptainsLog(Random random) {

    private static final char[] PLANET_CLASSES =
            new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    char randomPlanetClass() {
        return PLANET_CLASSES[random.nextInt(PLANET_CLASSES.length)];
    }

    String randomShipRegistryNumber() {
        return "NCC-%4d".formatted(1000 + random.nextInt(9000));
    }

    double randomStardate() {
        return 41000 + 1000 * random.nextDouble();
    }
}
