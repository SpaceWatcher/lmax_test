package tserr.testtask;

import com.lmax.disruptor.EventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputEventHandler implements EventHandler<NumEvent> {

    @Override
    public void onEvent(NumEvent numEvent, long l, boolean b) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("stepOne.txt", true));
        writer.write(numEvent.getValue() + "\r\n");
        writer.close();
    }
}
