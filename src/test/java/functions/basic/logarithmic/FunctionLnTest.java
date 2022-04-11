package functions.basic.logarithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionLnTest {
    private final Double accuracy = 0.01;
    private final FunctionLn ln = new FunctionLn();

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/logarithmic/ln.csv")
    public void lnTest(Double x, BigDecimal expected) {
        BigDecimal result = ln.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @Test
    void lnTestNegativeValue() {
        Exception exception = assertThrows(ArithmeticException.class, () -> ln.calculate(-1, accuracy));
        assertEquals("x must be non-negative!", exception.getMessage());
    }
}
