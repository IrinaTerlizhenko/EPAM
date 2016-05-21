package by.bsu.tunnel.reporter;

import by.bsu.tunnel.pool.Tunnel;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 6:38
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrinter {
    public static void printTunnels(Tunnel[] tunnels) {
        for (Tunnel tunnel : tunnels) {
            System.out.println(tunnel);
        }
    }

    public static void printWaitForTunnel(int train, int tunnel, boolean direction) {
        System.out.println("Train " + train + " is waiting for tunnel " + tunnel + " (direction " + (direction ? "->" : "<-") + ")");
    }

    public static void printEnterTunnel(int train, int tunnel, boolean direction) {
        System.out.println("Train " + train + " entered tunnel " + tunnel + " in direction " + (direction ? "->" : "<-"));
    }

    public static void printLeaveTunnel(int train, int tunnel) {
        System.out.println("Train " + train + " leaved tunnel " + tunnel);
    }
}
