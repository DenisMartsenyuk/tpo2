package functions.composite;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import functions.basic.trigonometric.FunctionCos;
import functions.basic.trigonometric.FunctionCot;
import functions.basic.trigonometric.FunctionCsc;
import functions.basic.trigonometric.FunctionSin;
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

public class FunctionFirstTest {
    private static final Double accuracy = 0.001;
    private static final FunctionCos cosMock = mock(FunctionCos.class);
    private static final FunctionCot cotMock = mock(FunctionCot.class);
    private static final FunctionCsc cscMock = mock(FunctionCsc.class);

    private static void getCosMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/trigonometric/cos.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cosMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getCotMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/trigonometric/cot.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cotMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    private static void getCscMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/mocks/functions/basic/trigonometric/csc.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cscMock.calculate(Double.parseDouble(record[0]), accuracy)).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        getCosMock();
        getCotMock();
        getCscMock();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/first.csv")
    public void firstTestWithMocks(Double x, BigDecimal expected) {
        FunctionFirst first = new FunctionFirst(cosMock, cotMock, cscMock);
        BigDecimal result = first.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/first.csv")
    public void firstTestWithMockCotCsc(Double x, BigDecimal expected) {
        FunctionFirst first = new FunctionFirst(new FunctionCos(new FunctionSin()), cotMock, cscMock);
        BigDecimal result = first.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/first.csv")
    public void firstTestWithMockCosCsc(Double x, BigDecimal expected) {
        FunctionSin sin = new FunctionSin();
        FunctionFirst first = new FunctionFirst(cosMock, new FunctionCot(sin, new FunctionCos(sin)), cscMock);
        BigDecimal result = first.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/first.csv")
    public void firstTestWithMockCosCot(Double x, BigDecimal expected) {
        FunctionFirst first = new FunctionFirst(cosMock, cotMock, new FunctionCsc(new FunctionSin()));
        BigDecimal result = first.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/mocks/functions/composite/first.csv")
    public void firstTestWithoutMocks(Double x, BigDecimal expected) {
        FunctionSin sin = new FunctionSin();
        FunctionCos cos = new FunctionCos(sin);
        FunctionFirst first = new FunctionFirst(cos, new FunctionCot(sin, cos), new FunctionCsc(sin));
        BigDecimal result = first.calculate(x, accuracy);
        assertEquals(expected.floatValue(), result.floatValue(), accuracy);
    }

}
