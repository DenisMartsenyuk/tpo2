package functions.basic.other;

import java.math.BigInteger;

public class FunctionFact {
    public BigInteger calculate(int x) {
        BigInteger result = new BigInteger(String.valueOf(1));
        for (int i = 1; i <= x; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
