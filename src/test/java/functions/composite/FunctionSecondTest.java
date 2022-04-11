package functions.composite;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import functions.basic.logarithmic.FunctionLn;
import functions.basic.logarithmic.FunctionLog10;
import functions.basic.logarithmic.FunctionLog3;
import functions.basic.logarithmic.FunctionLog5;
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

public class FunctionSecondTest {
    private static final Double accuracy = 0.01;
    private static final FunctionLog5 log5Mock = mock(FunctionLog5.class);
    private static final FunctionLog3 log3Mock = mock(FunctionLog3.class);
    private static final FunctionLog10 log10Mock = mock(FunctionLog10.class);


    private static void getLog5Mock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/logarithmic/log5.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(log5Mock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getLog3Mock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/logarithmic/log3.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(log3Mock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getLog10Mock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/logarithmic/log10.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(log10Mock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        getLog5Mock();
        getLog3Mock();
        getLog10Mock();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/second.csv")
    public void secondTestWithMocks(Double x, BigDecimal expected) {
        FunctionSecond second = new FunctionSecond(log5Mock, log3Mock, log10Mock);
        BigDecimal result = second.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/second.csv")
    public void secondTestWithMockLog3Log10(Double x, BigDecimal expected) {
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(new FunctionLog5(ln), log3Mock, log10Mock);
        BigDecimal result = second.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/second.csv")
    public void secondTestWithMockLog5Log10(Double x, BigDecimal expected) {
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(log5Mock, new FunctionLog3(ln), log10Mock);
        BigDecimal result = second.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/second.csv")
    public void secondTestWithMockLog5Log3(Double x, BigDecimal expected) {
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(log5Mock, log3Mock, new FunctionLog10(ln));
        BigDecimal result = second.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/second.csv")
    public void secondTestWithoutMocks(Double x, BigDecimal expected) {
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(new FunctionLog5(ln), new FunctionLog3(ln), new FunctionLog10(ln));
        BigDecimal result = second.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }
}
