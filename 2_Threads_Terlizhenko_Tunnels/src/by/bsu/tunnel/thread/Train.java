package by.bsu.tunnel.thread;

import by.bsu.tunnel.exception.TrainException;
import by.bsu.tunnel.pool.Tunnel;
import by.bsu.tunnel.reporter.ConsolePrinter;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 4:29
 * To change this template use File | Settings | File Templates.
 */
public class Train extends Thread {

    static Logger log = Logger.getLogger(Train.class);

    private Tunnel tunnel;
    private boolean direction;
    private int index;

    public Train(Tunnel tunnel, boolean direction, int index) {
        this.direction = direction;
        this.tunnel = tunnel;
        this.index = index;
    }

    public void enterTunnel() {
        try {
            ConsolePrinter.printWaitForTunnel(index, tunnel.INDEX, direction);
            tunnel.acquireWay(direction);
            ConsolePrinter.printEnterTunnel(index, tunnel.INDEX, direction);
            return;
        } catch (TrainException e) {
            log.error(e);
        }
    }

    public void proceedTunnel() {
        try {
            TimeUnit.MILLISECONDS.sleep(tunnel.TIME_TO_PASS_MS);
        } catch (InterruptedException e) {
            log.fatal(e);
        }
    }

    public void leaveTunnel() {
        ConsolePrinter.printLeaveTunnel(index, tunnel.INDEX);
        tunnel.releaseWay();
    }

    @Override
    public void run() {
        enterTunnel();
        proceedTunnel();
        leaveTunnel();
    }
}
