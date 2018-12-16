package tserr.testtask;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        IntEventFactory factory = new IntEventFactory();

        int bufferSize = 1024;

        Disruptor<NumEvent> disruptor = new Disruptor<>(factory, bufferSize,
                DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());

        disruptor.handleEventsWith(new OutputEventHandler())
                .then(new PrimeEventHandler())
                .then(new StatisticEventHandler());

        disruptor.start();

        RingBuffer<NumEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(4);
        Random random = new Random();
        while (true) {
            bb.putInt(0, random.nextInt());
            ringBuffer.publishEvent(((intEvent, sequence, buffer) -> intEvent.setValue(buffer.getInt(0))), bb);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
