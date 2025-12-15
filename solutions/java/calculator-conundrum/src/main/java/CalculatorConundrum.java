class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        mustNot(operation == null, "Operation cannot be null");
        mustNot(operation.isEmpty(), "Operation cannot be empty");
        int result = calculate(operand1, operand2, operation.charAt(1));
        return operand1 + " " + operation + " " + operand2 + " = " + result;
    }

    private int calculate(int operand1, int operand2, char operation) {
        return switch (operation) {
            case '*' -> operand1 * operand2;
            case '+' -> operand1 + operand2;
            case '/' -> {
                try {
                    yield operand1 / operand2;
                } catch (ArithmeticException e) {
                    throw new IllegalOperationException("Division by zero is not allowed", e);
                }
            }
            default -> throw new IllegalOperationException("Operation '" + operation + "' does not exist");
        };
    }

    private void mustNot(boolean condition, String message) {
        if (condition) throw new IllegalArgumentException(message);
    }
}
