public class StatisticsFunction {

    /**
     * Функция вычисления МО всего сигнала
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param sigLength количество дискретных значений
     * @return возаращает МО всего сигнала
     */
    public static double calcSignalMean(double[] sigSrcArr, int sigLength) {
        double meanCalc = 0.0;
        for (int i = 0; i < sigLength; i++) {
            meanCalc = meanCalc + sigSrcArr[i];
        }
        meanCalc = meanCalc / (double) sigLength;
        return meanCalc;
    }

    /**
     * Функция расчета дисперсии сигнала
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param sigMean   МО сигнала
     * @param sigLength количество дискретных значений
     * @return возвращает дисперсию сигнала
     */
    public static double calcSignalVariance(double[] sigSrcArr, double sigMean, int sigLength) {
        double variance = 0.0;
        for (int i = 0; i < sigLength; i++) {
            variance = variance + Math.pow((sigSrcArr[i] - sigMean), 2);
        }
        variance = variance / (double) (sigLength - 1);
        return variance;
    }

    /**
     * Функция расчета СКО сигнала
     *
     * @param sigVariance дисперсия сигнала
     * @return возвращает СКО сигнала
     */
    public static double calcSignalStd(double sigVariance) {
        return Math.sqrt(sigVariance);
    }

    /**
     * Функция расчета МО сигнала скользящим окном
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param blockSize размер скользящего окна
     * @return возвращает значение МО в скользящем окне
     */
    public static double calcSignalMeanLoop(double[] sigSrcArr, int blockSize) {
        double mean = 0.0;
        double sum = 0.0;
        int blkCnt;    /*loop Counter */
        int idx = 0;

        double in1, in2, in3, in4;

        blkCnt = blockSize >> 2;// blkCnt  =  blockSize / 4

        //Compute  4 outputs at a time

        while (blkCnt > 0) {
            //C = a[0 ]+a[1]+a[2]+....+a[blockSize-1]

            in1 = sigSrcArr[idx++];
            in2 = sigSrcArr[idx++];
            in3 = sigSrcArr[idx++];
            in4 = sigSrcArr[idx++];

            sum += in1;
            sum += in2;
            sum += in3;
            sum += in4;

            blkCnt--;

        }
        blkCnt = blockSize % 0x4;
        while (blkCnt > 0) {
            sum += sigSrcArr[idx++];
            blkCnt--;
        }
        /* C = (A[0] + A[1] + A[2] + ... + A[blockSize-1]) / blockSize  */
        /* Store the result to the destination */
        mean = sum / (double) blockSize;
        return mean;
    }

    /**
     * Функция расчета дисперсии сигнала скользящим окном
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param blockSize размер скользящего окна
     * @return возвращает значение дисперсии в скользящем окне
     */
    public static double calcSignalVarianceLoop(double[] sigSrcArr, int blockSize) {
        double variance = 0.0;
        double sum = 0.0;
        int blkCnt;
        int idx = 0;

        double fMean, fValue;
        double fSum = 0.0;
        double in1, in2, in3, in4;

        if (blockSize <= 1) {
            variance = 0;
        }
        blkCnt = blockSize >> 2;

        while (blkCnt > 0) {
            in1 = sigSrcArr[idx++];
            in2 = sigSrcArr[idx++];
            in3 = sigSrcArr[idx++];
            in4 = sigSrcArr[idx++];

            sum += in1;
            sum += in2;
            sum += in3;
            sum += in4;

            blkCnt--;
        }

        blkCnt = blockSize % 0x4;
        while (blkCnt > 0) {
            sum += sigSrcArr[idx++];
            blkCnt--;
        }
        fMean = sum / (double) blockSize;

        idx = 0;
        blkCnt = blockSize >> 2;
        while (blkCnt > 0) {

            fValue = sigSrcArr[idx++] - fMean;
            fSum += fValue * fValue;
            fValue = sigSrcArr[idx++] - fMean;
            fSum += fValue * fValue;
            fValue = sigSrcArr[idx++] - fMean;
            fSum += fValue * fValue;
            fValue = sigSrcArr[idx++] - fMean;
            fSum += fValue * fValue;

            blkCnt--;
        }
        blkCnt = blockSize % 0x4;

        while (blkCnt > 0) {
            fValue = sigSrcArr[idx++] - fMean;
            fSum += fValue * fValue;
            blkCnt--;
        }

        variance = fSum / (double) (blockSize - 1.0);
        return variance;
    }

    /**
     * Функция расчета СКО скользящим окном
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param blockSize размер скользящего окна
     * @return возвращает значение СКО в скользящем окне
     */
    public static double calcSignalStdLoop(double[] sigSrcArr, int blockSize) {

        double std;
        double sum = 0.0;
        int blkCnt;
        int idx = 0;

        double sumOfSquares = 0.0;
        double in;
        double meanOfSquares, mean, squareOfMean;

        if (blockSize == 1) {
            std = 0;
        }

        blkCnt = blockSize >> 2;
        while (blkCnt > 0) {
            /* C = (A[0] * A[0] + A[1] * A[1] + ... + A[blockSize-1] * A[blockSize-1]) */
            /* Compute Sum of squares of the input samples
             * and then store the result in a temporary variable, sum. */
            in = sigSrcArr[idx++];
            sum += in;
            sumOfSquares += in * in;
            in = sigSrcArr[idx++];
            sum += in;
            sumOfSquares += in * in;
            in = sigSrcArr[idx++];
            sum += in;
            sumOfSquares += in * in;
            in = sigSrcArr[idx++];
            sum += in;
            sumOfSquares += in * in;

            blkCnt--;
        }
        blkCnt = blockSize % 0x4;

        while (blkCnt > 0) {
            /* C = (A[0] * A[0] + A[1] * A[1] + ... + A[blockSize-1] * A[blockSize-1]) */
            /* Compute Sum of squares of the input samples
             * and then store the result in a temporary variable, sum. */
            in = sigSrcArr[idx++];
            sum += in;
            sumOfSquares += in * in;
            blkCnt--;
        }

        //Compute mean of squares of the input samples and then store the result
        //in temporary variable, meanOfSquares

        meanOfSquares = sumOfSquares / ((double) blockSize - 1.0);

        mean = sum / (double) blockSize;

        squareOfMean = (mean * mean) * ((double) blockSize) / ((double) blockSize - 1.0);
        std = Math.sqrt((meanOfSquares - squareOfMean));

        return std;
    }

    /**
     * Функция расчета СКЗ методом скользящего окна
     *
     * @param sigSrcArr исходный дискретный сигнал
     * @param blockSize размер скользящего окна
     * @return возвращает значение СКЗ в скользящем окне
     */
    public static double calcSignalRms(double[] sigSrcArr, int blockSize) {
        double rms;
        double sum = 0.0;
        int blkCnt;
        int idx = 0;
        double in;

        blkCnt = blockSize >> 2;
        /* First part of the processing with loop unrolling.  Compute 4 outputs at a time.
         ** a second loop below computes the remaining 1 to 3 samples. */
        while (blkCnt > 0) {
            /* C = A[0] * A[0] + A[1] * A[1] + A[2] * A[2] + ... + A[blockSize-1] * A[blockSize-1] */
            /* Compute sum of the squares and then store the result in a temporary variable, sum  */

            in = sigSrcArr[idx++];
            sum += in * in;
            in = sigSrcArr[idx++];
            sum += in * in;
            in = sigSrcArr[idx++];
            sum += in * in;
            in = sigSrcArr[idx++];
            sum += in * in;

            blkCnt--;

        }
        /* If the blockSize is not a multiple of 4, compute any remaining output samples here.
         ** No loop unrolling is used. */

        blkCnt = blockSize % 0x4;
        while (blkCnt > 0) {
            in = sigSrcArr[idx++];
            sum += in * in;
            blkCnt--;

        }
        rms = Math.sqrt(sum / (double) blockSize);

        return rms;
    }

    /**
     * Функция расчета минимального значения сигнала
     *
     * @return возвращает минимальное значение сигнала
     * @brief Минимальное значение в массиве чисел с плавающей точкой.
     * @param[in] sigSrcArr to the input arr
     * @param[in] blockSize length of the input arr
     * @param[out] resultDestArr minimum value and index returned here
     */

    public static double[] calcSigMin(double[] sigSrcArr, int blockSize) {

        double minValue1 = 0;
        double minValue2 = 0;
        double out;
        int blkCnt, outIndex, count;
        int idx = 0;
        count = 0;
        outIndex = 0;
        out = sigSrcArr[idx++];

        double[] resultDestArr = new double[2];

        blkCnt = (blockSize - 1) >> 2;
        while (blkCnt > 0) {

            minValue1 = sigSrcArr[idx++];
            minValue2 = sigSrcArr[idx++];


            if (out > minValue1) {
                out = minValue1;
                outIndex = count + 1;
            }
            if (out > minValue2) {
                out = minValue2;
                outIndex = count + 2;

            }
            minValue1 = sigSrcArr[idx++];
            minValue2 = sigSrcArr[idx++];

            if (out > minValue1) {
                out = minValue1;
                outIndex = count + 3;
            }

            if (out > minValue2) {
                out = minValue2;
                outIndex = count + 4;
            }

            count += 4;
            blkCnt--;
        }

        blkCnt = (blockSize - 1) % 4;

        while (blkCnt > 0) {

            minValue1 = sigSrcArr[idx++];

            if (out > minValue1) {
                out = minValue1;
                outIndex = blockSize - blkCnt;
            }
            blkCnt--;
        }
        resultDestArr[0] = out;
        resultDestArr[1] = outIndex;
        return resultDestArr;
    }

    /**
     * Функция расчета максимального значения сигнала.
     * Возвращает максимальное значение сигнала и индекс дискреты.
     *
     * @return none.
     * @brief Maximum value of a floating-point array.
     * @param[in] sigSrcArr the input array
     * @param[in] blockSize length of the input array
     * @param[out] resultDestArr maximum value and index  returned here
     */


    public static double[] calcSigMax(double[] sigSrcArr, int blocksize) {

        double maxValue1, maxValue2, out;
        double blkCnt, outIndex;
        double count = 0.0;
        int idx = 0;

        outIndex = 0;

        out = sigSrcArr[idx++];
        double[] resultDestArr = new double[2];

        blkCnt = (blocksize - 1) >> 2;
        while (blkCnt > 0) {
            /* Initialize maxVal to the next consecutive values one by one */
            maxValue1 = sigSrcArr[idx++];
            maxValue2 = sigSrcArr[idx++];

            if (out < maxValue1) {
                out = maxValue1;
                outIndex = count + 1;
            }
            if (out < maxValue2) {
                out = maxValue2;
                outIndex = count + 2;
            }

            maxValue1 = sigSrcArr[idx++];
            maxValue2 = sigSrcArr[idx++];

            if (out < maxValue1) {
                out = maxValue1;
                outIndex = count + 3;
            }

            if (out < maxValue2) {
                out = maxValue2;
                outIndex = count + 4;
            }

            count += 4;
            blkCnt--;
        }
        blkCnt = (blocksize - 1) % 0x4;
        while (blkCnt > 0) {
            maxValue1 = sigSrcArr[idx++];
            if (out < maxValue1) {
                out = maxValue1;
                outIndex = blocksize - blkCnt;
            }
            blkCnt--;
        }
        resultDestArr[0] = out;
        resultDestArr[1] = outIndex;
        return resultDestArr;
    }
}
