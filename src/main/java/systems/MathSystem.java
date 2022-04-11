package systems;

import functions.basic.Function;
import functions.basic.logarithmic.FunctionLn;
import functions.basic.logarithmic.FunctionLog10;
import functions.basic.logarithmic.FunctionLog3;
import functions.basic.logarithmic.FunctionLog5;
import functions.basic.trigonometric.FunctionCos;
import functions.basic.trigonometric.FunctionCot;
import functions.basic.trigonometric.FunctionCsc;
import functions.basic.trigonometric.FunctionSin;
import functions.composite.FunctionFirst;
import functions.composite.FunctionSecond;

import java.math.BigDecimal;

public class MathSystem implements Function {
    private final FunctionFirst first;
    private final FunctionSecond second;

    public MathSystem(FunctionFirst first, FunctionSecond second) {
        this.first = first;
        this.second = second;
    }

    public BigDecimal calculate(double x, double eps) {
        if (x > 0) {
            return second.calculate(x, eps);
        } else {
            return first.calculate(x, eps);
        }
    }
}
