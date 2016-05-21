package by.bsu.tunnel.launcher;

import by.bsu.tunnel.action.RailwayCreator;
import by.bsu.tunnel.pool.Tunnel;
import by.bsu.tunnel.reporter.ConsolePrinter;
import by.bsu.tunnel.thread.Train;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 4:17
 * To change this template use File | Settings | File Templates.
 */
public class Launcher {
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    static Logger log = Logger.getLogger(Launcher.class);

    private static final int MIN_PAUSE_MS = 200;
    private static final int MAX_PAUSE_MS = 300;

    public static void main(String[] args) {

        Tunnel[] tunnels = {Tunnel.getFirstTunnel(), Tunnel.getSecondTunnel()};
        ConsolePrinter.printTunnels(tunnels);

        Random random = new Random();
        for (int index = 1; index < 51; index++) {
            try {
                Train train = RailwayCreator.createTrain(tunnels, index);
                train.start();
                TimeUnit.MILLISECONDS.sleep(MIN_PAUSE_MS + random.nextInt(MAX_PAUSE_MS));
            } catch (InterruptedException e) {
                log.fatal(e);
            }
        }
    }
}
