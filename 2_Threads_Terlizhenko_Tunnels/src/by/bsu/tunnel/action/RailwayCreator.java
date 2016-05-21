package by.bsu.tunnel.action;

import by.bsu.tunnel.pool.Tunnel;
import by.bsu.tunnel.thread.Train;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 6:47
 * To change this template use File | Settings | File Templates.
 */
public class RailwayCreator {
    public static Train createTrain(Tunnel[] tunnels, int index) {
        Random random = new Random();
        return new Train(tunnels[random.nextInt(tunnels.length)], random.nextBoolean(), index);
    }
}
