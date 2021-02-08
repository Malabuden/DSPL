// Java-код для реализации
// Линейная экстраполяция

public class Extrapolator {

// Функция для вычисления экстраполяции сигнала

   public static double extrapolate(double[][] d, double x) {

        double y = d[0][1] + (x - d[0][0]) /
                (d[1][0] - d[0][0]) *
                (d[1][1] - d[0][1]);
        return y;
    }

}
