import org.junit.jupiter.api.Assertions;

class ExtrapolatorTest {

    @org.junit.jupiter.api.Test
    void extrapolate() {
        // Образец набора данных для экстраполяции
        double[][] d = {{1.2, 2.7}, {1.4, 3.1}};
        // Пример значения x
        double x = 3.1;
        System.out.println("Value of y at x = 2.1 : " + Extrapolator.LinExtrapolate(d, x));
        Assertions.assertNotNull(d);
    }

    @org.junit.jupiter.api.Test
    void regressionCoefficientsTest() {
        double[] x = {1, 2, 3, 4, 5, 6};
        double[] y = {4, 6, 8, 9, 10, 11};

        double[] yExtrapolation = new double[x.length];
        double[] coefficients = Extrapolator.regressionsExtrapolator(x, y);
        for (double coefficient : coefficients) {
            System.out.println(coefficient);
        }

        System.out.println("Восстановленная кривая");
        for (int i = 0; i < x.length; i++) {
            yExtrapolation[i] = coefficients[0] * Math.pow(x[i], coefficients[1]);
            System.out.println(yExtrapolation[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void regressionFunctionTest() {
        double[] x = {1, 2, 3, 4, 5, 6, 8, 11};
        double[] y = {4, 6, 8, 9, 10, 11, 10, 12};
        double[] xExtrap = {12, 13, 14, 15, 16, 17};

        double[] regressionValues = new double[xExtrap.length];
        regressionValues = Extrapolator.regressionFunction(x, y, xExtrap);
        for (int i = 0; i < xExtrap.length; i++) {
            System.out.println(regressionValues[i]);
        }
    }

}