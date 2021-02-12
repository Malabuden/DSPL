// Java-код для реализации
// Линейная экстраполяция

public class Extrapolator {

// Функция для вычисления линейной экстраполяции сигнала

    public static double LinExtrapolate(double[][] d, double x) {

        double y = d[0][1] + (x - d[0][0]) /
                (d[1][0] - d[0][0]) *
                (d[1][1] - d[0][1]);
        return y;
    }

    /**
     * Вычисляет два коээфициента модели нелинейной регресии
     *
     * @param x массив входных значений по оси Х
     * @param y массив входных значений по оси У
     * @return возвращает два коээфициента функции нелинейной регрессии
     */
    public static double[] regressionsExtrapolator(double[] x, double[] y) {
        double[] regressionCoefficients = new double[2];
        double A;
        double a;
        double b;
        double[] Z = new double[x.length];
        double[] U = new double[x.length];
        double[] Z2 = new double[x.length];
        double[] ZU = new double[x.length];
        double Zs = 0;
        double Us = 0;
        double Z2s = 0;
        double ZUs = 0;

        for (int i = 0; i < x.length; i++) {
            Z[i] = Math.log10(x[i]);
            U[i] = Math.log10(y[i]);
            Z2[i] = Z[i] * Z[i];
            ZU[i] = Z[i] * U[i];
        }

        for (int i = 0; i < x.length; i++) {
            Zs += Z[i];
            Us += U[i];
            Z2s += Z2[i];
            ZUs += ZU[i];
        }

        A = (Us * Z2s - ZUs * Zs) / (x.length * Z2s - Math.pow(Zs, 2));
        a = Math.pow(10, A);
        b = (x.length * ZUs - Zs * Us) / (x.length * Z2s - Math.pow(Zs, 2));

        regressionCoefficients[0] = a;
        regressionCoefficients[1] = b;

        return regressionCoefficients;
    }

    /**
     * @param x
     * @param y
     * @param xExtrapolation
     * @return
     */
    public static double[] regressionFunction(double[] x, double[] y, double[] xExtrapolation) {
        double[] yExtrapolation = new double[xExtrapolation.length];
        double[] coefficients = Extrapolator.regressionsExtrapolator(x, y);
        for (int i = 0; i < xExtrapolation.length; i++) {
            yExtrapolation[i] = coefficients[0] * Math.pow(xExtrapolation[i], coefficients[1]);
        }
        return yExtrapolation;
    }

}
