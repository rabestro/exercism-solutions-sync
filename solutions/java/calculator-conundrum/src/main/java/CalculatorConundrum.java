class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        mustNot(operation == null, "Operation cannot be null");
        mustNot(operation.isEmpty(), "Operation cannot be empty");

            var result = 0;
            switch(operation) {
            case "*": result = operand1 * operand2; break;
            case "+": result = operand1 + operand2; break;
            case "/": try { result = operand1 / operand2; }
                    catch (Exception e) {
            throw new IllegalOperationException(
                "Division by zero is not allowed", e);
        }
                    break;
                default: throw new IllegalOperationException(
                "Operation '" + operation + "' does not exist");
            }; 
            return operand1 + " " + operation + " " + operand2 + " = " + result;
    }

    private void mustNot(boolean condition, String message) {
        if (condition) throw new IllegalArgumentException(message);
    }
}
