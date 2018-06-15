import java.util.*;

public class Calculator {
    private class Expression {
        public String value;
        public Expression left;
        public Expression right;
        public char operator;

        public double evaluate() {
            if (value != null)
                return Double.valueOf(value);

            double lValue = left.evaluate();
            double rValue = right.evaluate();
            switch (operator) {
                case '+':
                    return lValue + rValue;

                case '-':
                    return lValue - rValue;

                case '*':
                    return lValue * rValue;

                case '/':
                    return lValue / rValue;

                default:
                    return 0.f;
            }
        };
    }

    public Double ans(String s) {
        String[] _operands = s.split("" "");
        List<String> operands = Arrays.asList(_operands);
        Stack<Expression> expressions = new Stack<> ();
        Expression expression = new Expression();

        for (int i=0; i<operands.size(); ++i) {
            String operand = operands.get(i);
            switch (operand.charAt(0)) {
                case '(':
                    Expression newExpression = new Expression();
                    if (!expressions.empty()) {
                        if (expressions.peek().left == null) {
                            expressions.peek().left = newExpression;
                        }
                        else {
                            expressions.peek().right = newExpression;
                        }
                    }
                    expressions.add(newExpression);
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    expressions.peek().operator = operand.charAt(0);
                    break;

                case ')':
                    expression = expressions.pop();
                    break;

                default:
                    if (expressions.peek().left == null) {
                        expressions.peek().left = new Expression();
                        expressions.peek().left.value = operand;
                    }
                    else {
                        expressions.peek().right = new Expression();
                        expressions.peek().right.value = operand;
                    }
                    break;
            }
        }

        return expression.evaluate();
    }
}

