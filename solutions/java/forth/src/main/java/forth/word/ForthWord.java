package forth.word;

import java.util.Deque;
import java.util.function.Consumer;

@FunctionalInterface
public interface ForthWord extends Consumer<Deque<Integer>> {
    ForthWord NO_OP = stack -> { };

    static ForthWord number(int value) {
        return stack -> stack.push(value);
    }

    default ForthWord then(ForthWord next) {
        return stack -> {
            accept(stack);
            next.accept(stack);
        };
    }
}
