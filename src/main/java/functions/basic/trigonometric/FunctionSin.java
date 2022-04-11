package functions.basic.trigonometric;

import functions.basic.Function;
import functions.basic.other.FunctionFact;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionSin implements Function {

    public BigDecimal calculate(double x, double eps) {
        FunctionFact fact = new FunctionFact();
        BigDecimal answer = new BigDecimal("0");
        int n = 0;
        BigDecimal prev;
        do {
            prev = answer;
            answer = answer.add(new BigDecimal(-1).pow(n).multiply(new BigDecimal(x).pow(2 * n + 1)).divide(new BigDecimal(fact.calculate(2 * n + 1)), 20, RoundingMode.HALF_UP));
            n++;
        } while (eps / 10 <= answer.subtract(prev).abs().doubleValue());

        return answer;
    }
}
