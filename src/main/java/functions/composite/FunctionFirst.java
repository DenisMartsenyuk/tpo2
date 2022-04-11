package functions.composite;

import functions.basic.Function;
import functions.basic.trigonometric.FunctionCos;
import functions.basic.trigonometric.FunctionCot;
import functions.basic.trigonometric.FunctionCsc;

import java.math.BigDecimal;

public class FunctionFirst implements Function {
    private final FunctionCos cos;
    private final FunctionCot cot;
    private final FunctionCsc csc;

    public FunctionFirst(FunctionCos cos, FunctionCot cot, FunctionCsc csc) {
        this.cos = cos;
        this.cot = cot;
        this.csc = csc;
    }

    public BigDecimal calculate(double x, double eps) {
        return cot.calculate(x, eps)
                .subtract(cos.calculate(x, eps))
                .multiply(cos.calculate(x, eps))
                .add(csc.calculate(x, eps))
                .pow(2)
                .pow(2);
    }
}
