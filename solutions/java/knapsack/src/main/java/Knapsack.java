import java.util.List;
import java.util.stream.IntStream;

public class Knapsack {
    int maximumValue(int capacity, List<Item> items) {
        return new MaxValue(items.toArray(Item[]::new)).maximum(capacity);
    }
}

record Item(int weight, int value) {
}

record MaxValue(Item[] items) {
    int maximum(int capacity) {
        return maximum(capacity, 0);
    }
    private int maximum(int capacity, int index) {
        return IntStream.range(index, items.length).map(i -> {
            var weight = capacity - items[i].weight();
            var value = items[i].value();
            return weight < 0 ? 0 : value + maximum(weight, ++i);
        }).max().orElse(0);
    }
}
