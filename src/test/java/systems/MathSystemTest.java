package systems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
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

public class MathSystemTest {
    private static final Double accuracy = 0.01;
    private static final FunctionFirst firstMock = mock(FunctionFirst.class);
    private static final FunctionSecond secondMock = mock(FunctionSecond.class);


    private static void getFirstMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/composite/first.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(firstMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getSecondMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/composite/second.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(secondMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        getFirstMock();
        getSecondMock();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/systems/system.csv")
    public void systemTestWithMocks(Double x, BigDecimal expected) {
        MathSystem mathSystem = new MathSystem(firstMock, secondMock);
        BigDecimal result = mathSystem.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/systems/system.csv")
    public void systemTestWithMockSecond(Double x, BigDecimal expected) {
        FunctionSin sin = new FunctionSin();
        FunctionCos cos = new FunctionCos(sin);
        FunctionFirst first = new FunctionFirst(cos, new FunctionCot(sin, cos), new FunctionCsc(sin));
        MathSystem mathSystem = new MathSystem(first, secondMock);
        BigDecimal result = mathSystem.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/systems/system.csv")
    public void systemTestWithMockFirst(Double x, BigDecimal expected) {
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(new FunctionLog5(ln), new FunctionLog3(ln), new FunctionLog10(ln));
        MathSystem mathSystem = new MathSystem(firstMock, second);
        BigDecimal result = mathSystem.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/systems/system.csv")
    public void systemTestWithoutMocks(Double x, BigDecimal expected) {
        FunctionSin sin = new FunctionSin();
        FunctionCos cos = new FunctionCos(sin);
        FunctionFirst first = new FunctionFirst(cos, new FunctionCot(sin, cos), new FunctionCsc(sin));
        FunctionLn ln = new FunctionLn();
        FunctionSecond second = new FunctionSecond(new FunctionLog5(ln), new FunctionLog3(ln), new FunctionLog10(ln));
        MathSystem mathSystem = new MathSystem(first, second);
        BigDecimal result = mathSystem.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }
}
