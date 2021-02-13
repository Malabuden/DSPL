// Java-код для реализации
// Линейная экстраполяция

public class Extrapolator {

// Функция для вычисления линейной экстраполяции сигнала

    public static double linExtrapolate(double[][] d, double x) {

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
    public static double[] regressionsCoefficientsPowerFunction(double[] x, double[] y) {
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
     * Вычисляет два коээфициента модели нелинейной регресии
     *
     * @param x массив входных значений по оси Х
     * @param y массив входных значений по оси У
     * @return возвращает два коээфициента функции нелинейной регрессии
     */
    public static double[] regressionsCoefficientsIndicativeFunction(double[] x, double[] y) {
        double[] regressionCoefficients = new double[2];
        double A;
        double a;
        double B;
        double b;
        double[] X = new double[x.length];
        double[] U = new double[x.length];
        double[] X2 = new double[x.length];
        double[] UX = new double[x.length];
        double Xs = 0;
        double Us = 0;
        double X2s = 0;
        double UXs = 0;

        for (int i = 0; i < x.length; i++) {
            X[i] = x[i];
            U[i] = Math.log10(y[i]);
            X2[i] = X[i] * X[i];
            UX[i] = X[i] * U[i];
            Xs += X[i];
            Us += U[i];
            X2s += X2[i];
            UXs += UX[i];
        }

        A = (Us * X2s - UXs * Xs) / (x.length * X2s - Math.pow(Xs, 2));
        B = (x.length * UXs - Xs * Us) / (x.length * X2s - Math.pow(Xs, 2));

        a = Math.pow(10, A);
        b = Math.pow(10, B);

        regressionCoefficients[0] = a;
        regressionCoefficients[1] = b;

        return regressionCoefficients;
    }

    /**
     * Вычисляет два коээфициента модели нелинейной регресии
     *
     * @param x массив входных значений по оси Х
     * @param y массив входных значений по оси У
     * @return возвращает два коээфициента функции нелинейной регрессии
     */
    public static double[] regressionsCoefficientsRationalFunction(double[] x, double[] y) {
        double[] regressionCoefficients = new double[2];
        double a;
        double b;
        double[] X = new double[x.length];
        double[] U = new double[x.length];
        double[] X2 = new double[x.length];
        double[] UX = new double[x.length];
        double Xs = 0;
        double Us = 0;
        double X2s = 0;
        double UXs = 0;

        for (int i = 0; i < x.length; i++) {
            X[i] = x[i];
            U[i] = 1/(y[i]);
            X2[i] = X[i] * X[i];
            UX[i] = X[i] * U[i];
            Xs += X[i];
            Us += U[i];
            X2s += X2[i];
            UXs += UX[i];
        }

        a = (Us * X2s - UXs * Xs) / (x.length * X2s - Math.pow(Xs, 2));
        b = (x.length * UXs - Xs * Us) / (x.length * X2s - Math.pow(Xs, 2));

        regressionCoefficients[0] = a;
        regressionCoefficients[1] = b;

        return regressionCoefficients;
    }

    /**
     * Вычисляет два коээфициента модели нелинейной регресии
     *
     * @param x массив входных значений по оси Х
     * @param y массив входных значений по оси У
     * @return возвращает два коээфициента функции нелинейной регрессии
     */
    public static double[] regressionsCoefficientsLogFunction(double[] x, double[] y) {
        double[] regressionCoefficients = new double[2];
        double a;
        double b;
        double[] Y = new double[x.length];
        double[] Z = new double[x.length];
        double[] Z2 = new double[x.length];
        double[] ZY = new double[x.length];
        double Ys = 0;
        double Zs = 0;
        double Z2s = 0;
        double ZYs = 0;

        for (int i = 0; i < x.length; i++) {
            Y[i] = y[i];
            Z[i] = Math.log10(x[i]);
            Z2[i] = Z[i] * Z[i];
            ZY[i] = Z[i] * Y[i];
            Ys += Y[i];
            Zs += Z[i];
            Z2s += Z2[i];
            ZYs += ZY[i];
        }

        a = (Ys * Z2s - ZYs * Zs) / (x.length * Z2s - Math.pow(Zs, 2));
        b = (x.length * ZYs - Zs * Ys) / (x.length * Z2s - Math.pow(Zs, 2));

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
        double[] coefficients = Extrapolator.regressionsCoefficientsPowerFunction(x, y);
        for (int i = 0; i < xExtrapolation.length; i++) {
            yExtrapolation[i] = coefficients[0] * Math.pow(xExtrapolation[i], coefficients[1]);
        }
        return yExtrapolation;
    }

}
