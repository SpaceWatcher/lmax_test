package tserr.testtask;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.EventTranslatorOneArg;

import java.nio.ByteBuffer;

public class IntEventProducerWithTranslator
{
    private final RingBuffer<NumEvent> ringBuffer;

    public IntEventProducerWithTranslator(RingBuffer<NumEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<NumEvent, ByteBuffer> TRANSLATOR =
            (NumEvent event, long sequence, ByteBuffer bb) -> event.setValue(bb.getInt(0));

    public void onData(ByteBuffer bb)
    {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}