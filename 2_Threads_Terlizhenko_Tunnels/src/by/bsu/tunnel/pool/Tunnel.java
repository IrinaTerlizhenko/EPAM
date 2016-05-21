package by.bsu.tunnel.pool;

import by.bsu.tunnel.exception.TrainException;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 4:51
 * To change this template use File | Settings | File Templates.
 */
public class Tunnel {

    static Logger log = Logger.getLogger(Tunnel.class);

    public static final int MAX_TRAINS = 4;
    public static final int MAX_TIME_TO_PASS_MS = 1000;
    public static final int MAX_IN_DIRECTION = 4;
    public static final int MAX_WAIT_SEC = 30;

    public final int TRAINS;
    public final int TIME_TO_PASS_MS;
    public final int IN_DIRECTION;
    public final int INDEX;

    private Semaphore trainSemaphore;
    private Semaphore leftSemaphore;
    private Semaphore rightSemaphore;

    private AtomicBoolean lastEnteredDirection = new AtomicBoolean();
    private ReentrantLock lock = new ReentrantLock(true);

    private static class TunnelHolder {
        private static Tunnel firstTunnel;
        private static Tunnel secondTunnel;

        private ReentrantLock lock = new ReentrantLock(true);

        private TunnelHolder() {
            lock.lock();
            if (firstTunnel == null) {
                firstTunnel = new Tunnel(1);
            }
            if (secondTunnel == null) {
                secondTunnel = new Tunnel(2);
            }
            lock.unlock();
        }
    }

    private Tunnel(int index) {
        INDEX = index;
        Random random = new Random();
        TRAINS = random.nextInt(MAX_TRAINS) + 1;
        TIME_TO_PASS_MS = random.nextInt(MAX_TIME_TO_PASS_MS) + 500;
        IN_DIRECTION = random.nextInt(MAX_IN_DIRECTION) + 1;
        trainSemaphore = new Semaphore(TRAINS, true);
        leftSemaphore = new Semaphore(IN_DIRECTION, true);
        rightSemaphore = new Semaphore(IN_DIRECTION, true);
    }

    public static Tunnel getFirstTunnel() {
        return TunnelHolder.firstTunnel;
    }

    public static Tunnel getSecondTunnel() {
        return TunnelHolder.secondTunnel;
    }

    public void acquireWay(boolean direction) throws TrainException {
        try {
            if (!semaphoreByDirection(direction).tryAcquire(MAX_WAIT_SEC, TimeUnit.SECONDS)) {
                throw new TrainException("Train can't enter tunnel " + INDEX +
                        " because maximum trains have already proceeded in direction " + (direction ? "->" : "<-"));
            }
            if (!lastEnteredDirection.compareAndSet(direction, direction)) {
                try {
                    Semaphore oppositeSemaphore = semaphoreByDirection(!direction);
                    lock.lock();
                    while (oppositeSemaphore.availablePermits() != 0 &&
                            trainSemaphore.availablePermits() == 0) {
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                    boolean prevDirection = lastEnteredDirection.getAndSet(direction);
                    if (prevDirection != direction) {
                        oppositeSemaphore.release(IN_DIRECTION -
                                oppositeSemaphore.availablePermits());
                    }
                } finally {
                    lock.unlock();
                }
            }
            trainSemaphore.acquire();
            return;
        } catch (InterruptedException e) {
            log.fatal(e);
        }
    }

    public void releaseWay() {
        trainSemaphore.release();
    }

    private Semaphore semaphoreByDirection(boolean direction) {
        return direction ? rightSemaphore : leftSemaphore;
    }

    @Override
    public String toString() {
        return "Tunnel " + INDEX + ". TRAINS = " + TRAINS + "; TIME_TO_PASS = " +
                TIME_TO_PASS_MS + "ms; IN_DIRECTION = " + IN_DIRECTION;
    }
}
