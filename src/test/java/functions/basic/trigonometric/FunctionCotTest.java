package functions.basic.trigonometric;

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

public class FunctionCotTest {
    private static final Double accuracy = 0.001;
    private static final FunctionSin sinMock = mock(FunctionSin.class);
    private static final FunctionCos cosMock = mock(FunctionCos.class);

    private static void getSinMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/trigonometric/sin.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(sinMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getCosMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/trigonometric/cos.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cosMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        getSinMock();
        getCosMock();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/trigonometric/cot.csv")
    public void cotTestWithMocks(Double x, BigDecimal expected) {
        FunctionCot cot = new FunctionCot(sinMock, cosMock);
        BigDecimal result = cot.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/trigonometric/cot.csv")
    public void cotTestWithMockSin(Double x, BigDecimal expected) {
        FunctionCot cot = new FunctionCot(sinMock, new FunctionCos(new FunctionSin()));
        BigDecimal result = cot.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/trigonometric/cot.csv")
    public void cotTestWithMockCos(Double x, BigDecimal expected) {
        FunctionCot cot = new FunctionCot(new FunctionSin(), cosMock);
        BigDecimal result = cot.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/basic/trigonometric/cot.csv")
    public void cosTestWithoutMocks(Double x, BigDecimal expected) {
        FunctionCot cot = new FunctionCot(new FunctionSin(), new FunctionCos(new FunctionSin()));
        BigDecimal result = cot.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }
}
