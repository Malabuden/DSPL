import org.junit.jupiter.api.Test;

class StatisticsFunctionTest {

    @Test
    void calcSignalMean() {
        Signal inputSignal = new Signal();
        double mean;
        mean = StatisticsFunction.calcSignalMean(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("Mean : ");
        System.out.println(mean);
    }

    @Test
    void calcSignalVariance() {
        Signal inputSignal = new Signal();
        double variance;
        double mean;
        mean = StatisticsFunction.calcSignalMean(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        variance = StatisticsFunction.calcSignalVariance(inputSignal.InputSignal_f32_1kHz_15kHz, mean, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("Variance : ");
        System.out.println(variance);
    }

    @Test
    void calcSignalStd() {
        Signal inputSignal = new Signal();
        double std;
        double variance;
        double mean;
        mean = StatisticsFunction.calcSignalMean(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        variance = StatisticsFunction.calcSignalVariance(inputSignal.InputSignal_f32_1kHz_15kHz, mean, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        std = StatisticsFunction.calcSignalStd(variance);
        System.out.println("Standard Deviation : ");
        System.out.println(std);
    }

    @Test
    void calcSignalMeanLoop() {
        Signal inputSignal = new Signal();
        double meanLoop;
        meanLoop = StatisticsFunction.calcSignalMeanLoop(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("Mean Loop : ");
        System.out.println(meanLoop);

    }

    @Test
    void calcSignalVarianceLoop() {
        Signal inputSignal = new Signal();
        double varianceLoop;
        varianceLoop = StatisticsFunction.calcSignalVarianceLoop(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("Variance Loop : ");
        System.out.println(varianceLoop);
    }

    @Test
    void calcSignalStdLoop() {
        Signal inputSignal = new Signal();
        double stdLoop;
        stdLoop = StatisticsFunction.calcSignalStdLoop(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("Standard Deviation Loop : ");
        System.out.println(stdLoop);
    }

    @Test
    void calcSignalRms() {
        Signal inputSignal = new Signal();
        double rmsLoop;
        rmsLoop = StatisticsFunction.calcSignalRms(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.println("RMS : ");
        System.out.println(rmsLoop);
    }

    @Test
    void calcSigMin() {
        Signal inputSignal = new Signal();
        double[] sigMinimum;
        sigMinimum = StatisticsFunction.calcSigMin(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.print("Index of min value : ");
        System.out.print(sigMinimum[1]);
        System.out.print(" Min value : ");
        System.out.println(sigMinimum[0]);
    }

    @Test
    void calcSigMax() {
        Signal inputSignal = new Signal();
        double[] sigMaximum;
        sigMaximum = StatisticsFunction.calcSigMax(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        System.out.print("Index of max value : ");
        System.out.print(sigMaximum[1]);
        System.out.print(" Max value : ");
        System.out.println(sigMaximum[0]);
    }

}