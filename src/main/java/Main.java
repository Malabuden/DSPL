public class Main {

    public static void main(String[] args) {
        Signal inputSignal = new Signal();
        double mean;
        double variance;
        double std;

        double meanLoop;
        double varianceLoop;
        double stdLoop;
        double rmsLoop;

        double[] sigMinimum = new double[2];

        mean = StatisticsFunction.calcSignalMean(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        variance = StatisticsFunction.calcSignalVariance(inputSignal.InputSignal_f32_1kHz_15kHz, mean, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        std = StatisticsFunction.calcSignalStd(variance);

        meanLoop = StatisticsFunction.calcSignalMeanLoop(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        varianceLoop = StatisticsFunction.calcSignalVarianceLoop(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);
        stdLoop =  StatisticsFunction.calcSignalStdLoop(inputSignal.InputSignal_f32_1kHz_15kHz,inputSignal.InputSignal_f32_1kHz_15kHz.length);
        rmsLoop = StatisticsFunction.calcSignalRms(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);

        sigMinimum = StatisticsFunction.calcSigMin(inputSignal.InputSignal_f32_1kHz_15kHz, inputSignal.InputSignal_f32_1kHz_15kHz.length);

        System.out.print("Index of min value : ");
        System.out.print(sigMinimum[1]);
        System.out.print(" Min value : ");
        System.out.println(sigMinimum[0]);

        System.out.println("Mean Loop : ");
        System.out.println(meanLoop);
        System.out.println("Variance Loop : ");
        System.out.println(varianceLoop);
        System.out.println("Standard Deviation Loop : ");
        System.out.println(stdLoop);
        System.out.println("RMS : ");
        System.out.println(rmsLoop);

        System.out.println("Mean : ");
        System.out.println(mean);
        System.out.println("Variance : ");
        System.out.println(variance);
        System.out.println("Standard Deviation : ");
        System.out.println(std);

    }
}

