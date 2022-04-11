package functions.basic;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(double x, double eps);
}
