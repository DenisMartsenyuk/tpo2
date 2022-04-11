package utils;

import functions.basic.Function;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class PrinterFunction {
    private CsvPrinter csvPrinter = new CsvPrinter();

    public void print(double a, double b, double step, double eps, Function function, String name) throws FileNotFoundException {
        csvPrinter.setFilePath("log/" + name + ".csv");
        for (double i = a; i <= b; i += step) {
            BigDecimal res = function.calculate(i, eps);
            csvPrinter.print(i, res.doubleValue());
        }
    }
}
