package functions.basic.logarithmic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FunctionLog3Test {
    private static final Double accuracy = 0.01;
    private static final FunctionLn lnMock = mock(FunctionLn.class);

    private static void getLnMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/logarithmic/ln.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(lnMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        getLnMock();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/logarithmic/log3.csv")
    public void log3TestWithMockLn(Double x, BigDecimal expected) {
        FunctionLog3 log3 = new FunctionLog3(lnMock);
        BigDecimal result = log3.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/logarithmic/log3.csv")
    public void log3TestWithoutMocks(Double x, BigDecimal expected) {
        FunctionLog3 log3 = new FunctionLog3(new FunctionLn());
        BigDecimal result = log3.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }
}
