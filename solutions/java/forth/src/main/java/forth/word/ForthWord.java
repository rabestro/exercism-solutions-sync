package forth.word;


import java.util.Deque;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

@FunctionalInterface
public interface ForthWord extends Consumer<Deque<Integer>> {

    static Map<String, ForthWord> builtInWords() {
        return Map.of(
                "+", binaryOperator("Addition requires that the stack contain at least 2 values", Integer::sum),
                "-", binaryOperator("Subtraction requires that the stack contain at least 2 values", (a, b) -> a - b),
                "*", binaryOperator("Multiplication requires that the stack contain at least 2 values", (a, b) -> a * b),
                "/", new Division(),
                "dup", new Duplication(),
                "drop", new Dropping(),
                "swap", new Swapping(),
                "over", new Overing());
    }

    static void ensureSize(Deque<Integer> stack, int requiredSize, String errorMessage) {
        if (stack.size() < requiredSize) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    static ForthWord number(String token) {
        return stack -> stack.push(Integer.parseInt(token));
    }

    static ForthWord binaryOperator(String errorMessage, BinaryOperator<Integer> operator) {
        return stack -> {
            ensureSize(stack, 2, errorMessage);
            int b = stack.pop();
            int a = stack.pop();
            stack.push(operator.apply(a, b));
        };
    }
}
