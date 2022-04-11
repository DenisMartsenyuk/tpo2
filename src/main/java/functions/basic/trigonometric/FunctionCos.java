package functions.basic.trigonometric;

import functions.basic.Function;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class FunctionCos implements Function {
    private final FunctionSin sin;

    public FunctionCos(FunctionSin sin) {
        this.sin = sin;
    }

    public BigDecimal calculate(double x, double eps) {
        if (abs (x - 4.71) < 0.1) {
            System.out.println(sin.calculate(x, eps));
        }
        BigDecimal cos = new BigDecimal(1);
        cos = cos.subtract(sin.calculate(x, eps).multiply(sin.calculate(x, eps))).sqrt(MathContext.DECIMAL128);
        x = abs(x % (2 * PI));
        if ((x > PI / 2) && (x < 3 * PI / 2)) {
            cos = cos.multiply(BigDecimal.valueOf(-1));
        }
        return cos;
    }
}
