package functions.basic.trigonometric;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionSinTest {
    private final Double accuracy = 0.0001;
    private final FunctionSin sin = new FunctionSin();

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/trigonometric/sin.csv")
    public void sinTest(Double x, BigDecimal expected) {
        BigDecimal result = sin.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }
}
