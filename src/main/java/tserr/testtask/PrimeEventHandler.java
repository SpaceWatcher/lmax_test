package tserr.testtask;

import com.lmax.disruptor.EventHandler;

public class PrimeEventHandler implements EventHandler<NumEvent> {
    @Override
    public void onEvent(NumEvent numEvent, long l, boolean b) throws  Exception {
        MathService.getInstance().checkPrime(numEvent);
    }

}
