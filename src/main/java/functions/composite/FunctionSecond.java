package functions.composite;

import functions.basic.Function;
import functions.basic.logarithmic.FunctionLog10;
import functions.basic.logarithmic.FunctionLog3;
import functions.basic.logarithmic.FunctionLog5;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FunctionSecond implements Function {
    private final FunctionLog5 log5;
    private final FunctionLog3 log3;
    private final FunctionLog10 log10;

    public FunctionSecond(FunctionLog5 log5, FunctionLog3 log3, FunctionLog10 log10) {
        this.log5 = log5;
        this.log3 = log3;
        this.log10 = log10;
    }

    public BigDecimal calculate(double x, double eps) {
        return log5.calculate(x, eps)
                .multiply(log5.calculate(x, eps))
                .pow(3)
                .multiply(log10.calculate(x, eps).add(log3.calculate(x, eps)))
                .multiply(log3.calculate(x, eps))
                .multiply(log5.calculate(x, eps))
                .divide(log3.calculate(x * x, eps), 20, RoundingMode.HALF_UP);
    }

}
