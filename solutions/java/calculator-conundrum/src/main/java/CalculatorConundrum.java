class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        mustNot(operation == null, "Operation cannot be null");
        mustNot(operation.isEmpty(), "Operation cannot be empty");

        var result = switch (operation) {
            case "*" -> operand1 * operand2;
            case "+" -> operand1 + operand2;
            case "/" -> divide(operand1, operand2);
            default -> throw new IllegalOperationException("Operation '" + operation + "' does not exist");
        };

        return operand1 + " " + operation + " " + operand2 + " = " + result;
    }

    private int divide(int operand1, int operand2) {
        try {
            return operand1 / operand2;
        } catch (Exception e) {
            throw new IllegalOperationException("Division by zero is not allowed", e);
        }
    }

    private void mustNot(boolean condition, String message) {
        if (condition) throw new IllegalArgumentException(message);
    }
}
