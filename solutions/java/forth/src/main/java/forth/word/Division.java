package forth.word;

import java.util.Deque;

public class Division implements ForthWord {
    @Override
    public void accept(Deque<Integer> stack) {
        ForthWord.ensureSize(stack, 2, "Division requires that the stack contain at least 2 values");
        int divisor = stack.pop();
        if (divisor == 0) {
            throw new IllegalArgumentException("Division by 0 is not allowed");
        }
        int dividend = stack.pop();
        stack.push(dividend / divisor);
    }
}
