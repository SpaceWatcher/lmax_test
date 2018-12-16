package tserr.testtask;


import com.lmax.disruptor.EventHandler;

public class StatisticEventHandler implements EventHandler<NumEvent> {
    private static final int TIMEOUT = 5000;

    private double primeCount = 0;
    private double notPrimeCount = 0;
    private long curTime = 0;

    @Override
    public void onEvent(NumEvent event, long l, boolean b) throws Exception {
        if (event.isPrime())
            ++primeCount;
        else
            ++notPrimeCount;

        if ((curTime + TIMEOUT) < System.currentTimeMillis()) {
            int totalCount = (int) (primeCount + notPrimeCount);
            if (totalCount > 0) {
                double percent = (primeCount / totalCount) * 100;
                System.out.println("Percent of the prime numbers: " + percent + "%");
            }
            curTime = System.currentTimeMillis();
        }
    }
}
