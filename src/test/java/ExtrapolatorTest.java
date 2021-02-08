import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class ExtrapolatorTest {

    @org.junit.jupiter.api.Test
    void extrapolate() {
        // Образец набора данных для экстраполяции
        double[][] d = {{1.2, 2.7}, {1.4, 3.1}};
        // Пример значения x
        double x = 3.1;
        System.out.println("Value of y at x = 2.1 : " + Extrapolator.extrapolate(d, x));
        Assertions.assertNotNull(d);
    }

}