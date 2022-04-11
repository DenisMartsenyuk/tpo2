package functions.basic.logarithmic;

import functions.basic.Function;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionLog10 implements Function {

    private final FunctionLn ln;

    public FunctionLog10(FunctionLn ln) {
        this.ln = ln;
    }

    public BigDecimal calculate(double x, double eps) {
        BigDecimal answer = new BigDecimal(0);
        answer = answer.add(
                ln.calculate(x, eps)
                        .divide(ln.calculate(10D, eps), RoundingMode.HALF_UP)
        );
        return answer;
    }
}
