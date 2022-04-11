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
import systems.MathSystem;
import utils.PrinterFunction;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FunctionSin sin = new FunctionSin();
        FunctionCos cos = new FunctionCos(sin);
        FunctionCsc csc = new FunctionCsc(sin);
        FunctionCot cot = new FunctionCot(sin, cos);

        FunctionLn ln = new FunctionLn();
        FunctionLog3 log3 = new FunctionLog3(ln);
        FunctionLog5 log5 = new FunctionLog5(ln);
        FunctionLog10 log10 = new FunctionLog10(ln);

        FunctionFirst first = new FunctionFirst(cos, cot, csc);
        FunctionSecond second = new FunctionSecond(log5, log3, log10);

        MathSystem mathSystem = new MathSystem(first, second);
        PrinterFunction printerFunction = new PrinterFunction();

        Map<String, Function> mapFunctions = new HashMap<>();
        mapFunctions.put("sin", sin);
        mapFunctions.put("cos", cos);
        mapFunctions.put("csc", csc);
        mapFunctions.put("cot", cot);
        mapFunctions.put("ln", ln);
        mapFunctions.put("log3", log3);
        mapFunctions.put("log5", log5);
        mapFunctions.put("log10", log10);
        mapFunctions.put("first", first);
        mapFunctions.put("second", second);
        mapFunctions.put("mathSystem", mathSystem);

        for (mapFunctions.entrySet())

        System.out.println("Введите начало, конец промежутка, шаг, точность, имя функции:");

        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double step = scanner.nextDouble();
        double eps = scanner.nextDouble();
        String name = scanner.next();

        printerFunction.print(a, b, step, eps, mapFunctions.get(name), name);

    }
}
