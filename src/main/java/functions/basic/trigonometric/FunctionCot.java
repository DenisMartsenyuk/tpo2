package functions.basic.trigonometric;

import functions.basic.Function;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionCot implements Function {

    private final FunctionSin sin;
    private final FunctionCos cos;

    public FunctionCot(FunctionSin sin, FunctionCos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public BigDecimal calculate(double x, double eps) {
        return cos.calculate(x, eps).divide(sin.calculate(x, eps),20, RoundingMode.HALF_UP);
    }
}
