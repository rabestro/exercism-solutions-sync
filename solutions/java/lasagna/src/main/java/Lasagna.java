public class Lasagna {
    int expectedMinutesInOven() {
        return 40;
    }

    int remainingMinutesInOven(int min) {
        return expectedMinutesInOven() - min;
    }

    int preparationTimeInMinutes(int layers) {
        return layers * 2;
    }

    int totalTimeInMinutes(int layers, int min) {
        return preparationTimeInMinutes(layers) + min;
    }
}
