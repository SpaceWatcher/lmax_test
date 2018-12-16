package tserr.testtask;

import com.lmax.disruptor.EventFactory;

public class IntEventFactory implements EventFactory<NumEvent> {

    @Override
    public NumEvent newInstance() {
        return new NumEvent();
    }
}
