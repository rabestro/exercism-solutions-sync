import java.util.List;

public class Knapsack {
    int maximumValue(int capacity, List<Item> items) {
        var dp = new int[capacity + 1];

        for (var item : items) {
            for (var w = capacity; w >= item.weight(); w--) {
                dp[w] = Math.max(dp[w], dp[w - item.weight()] + item.value());
            }
        }

        return dp[capacity];
    }
}

record Item(int weight, int value) {}