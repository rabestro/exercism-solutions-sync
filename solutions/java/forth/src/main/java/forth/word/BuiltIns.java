package forth.word;

import java.util.Deque;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public final class BuiltIns {
    private static final Map<String, ForthWord> WORDS = Map.of(
            "+", binary("Addition", Integer::sum),
            "-", binary("Subtraction", (a, b) -> a - b),
            "*", binary("Multiplication", (a, b) -> a * b),
            "/", BuiltIns::divide,
            "dup", BuiltIns::dup,
            "drop", BuiltIns::drop,
            "swap", BuiltIns::swap,
            "over", BuiltIns::over);

    private BuiltIns() {
    }

    public static Map<String, ForthWord> words() {
        return WORDS;
    }

    private static ForthWord binary(String name, IntBinaryOperator operator) {
        return stack -> {
            ensureSize(stack, 2, name);
            int b = stack.pop();
            int a = stack.pop();
            stack.push(operator.applyAsInt(a, b));
        };
    }

    private static void divide(Deque<Integer> stack) {
        ensureSize(stack, 2, "Division");
        if (stack.peek() == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
        int divisor = stack.pop();
        int dividend = stack.pop();
        stack.push(dividend / divisor);
    }

    private static void dup(Deque<Integer> stack) {
        ensureSize(stack, 1, "Duplicating");
        stack.push(stack.peek());
    }

    private static void drop(Deque<Integer> stack) {
        ensureSize(stack, 1, "Dropping");
        stack.pop();
    }

    private static void swap(Deque<Integer> stack) {
        ensureSize(stack, 2, "Swapping");
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a);
        stack.push(b);
    }

    private static void over(Deque<Integer> stack) {
        ensureSize(stack, 2, "Overing");
        int a = stack.pop();
        int b = stack.peek();
        stack.push(a);
        stack.push(b);
    }

    private static void ensureSize(Deque<Integer> stack, int required, String operation) {
        if (stack.size() < required) {
            throw new IllegalArgumentException("%s requires that the stack contain at least %d %s"
                    .formatted(operation, required, required == 1 ? "value" : "values"));
        }
    }
}
