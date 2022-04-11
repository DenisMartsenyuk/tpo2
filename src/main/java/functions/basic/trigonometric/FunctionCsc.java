package functions.basic.trigonometric;

import functions.basic.Function;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionCsc implements Function {

    private final FunctionSin sin;

    public FunctionCsc(FunctionSin sin) {
        this.sin = sin;
    }

    public BigDecimal calculate(double x, double eps) {
        return new BigDecimal(1).divide(sin.calculate(x, eps), 20, RoundingMode.HALF_UP);
    }
}
