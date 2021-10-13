import java.util.Map;
import java.util.concurrent.*;

public class DemoRace {

    private static final int MAX_CARS_IN_TUNNEL = 3;
    private static final int CARS_COUNT = 10;
    private static final Semaphore tunnelSemaphore = new Semaphore(MAX_CARS_IN_TUNNEL);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
    private static final Map<Integer, Long> score = new ConcurrentHashMap<>();
    private static final CountDownLatch countDownLatch = new CountDownLatch(CARS_COUNT);

    private static int winner = -1;
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < CARS_COUNT; i++) {
            final int index = i;
            executorService.execute(() -> {
                prepare(index);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                long before = System.currentTimeMillis();
                roadFirst(index);
                tunnel(index);
                roarSecond(index);
                synchronized (monitor) {
                    if (winner == -1) {
                        winner = index;
                    }
                }
                long after = System.currentTimeMillis();
                score.put(index, after - before);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int key : score.keySet()) {
            System.out.println(key + " " + score.get(key));
        }
        System.out.println("Победитель: " + winner + " Время: " + score.get(winner));
        executorService.shutdown();
    }

    private static void sleepRandomTime() {
        long millis = (long) (Math.random() * 5000 + 1000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void prepare(int index) {
        System.out.println(index + " начинает подготовку");
        sleepRandomTime();
        System.out.println(index + " заканчивает погдотовку");
    }

    private static void roadFirst(int index) {
        System.out.println(index + " : начинает первый этап");
        sleepRandomTime();
        System.out.println(index + " : заканчивает первый этап");
    }

    private static void roarSecond(int index) {
        System.out.println(index + " : начинает второй этап");
        sleepRandomTime();
        System.out.println(index + " : заканчивает второй этап");
    }

    private static void tunnel(int index) {
        try {
            tunnelSemaphore.acquire();
            System.out.println(index + " : входит в туннель");
            sleepRandomTime();
            System.out.println(index + " : выходит из тунеля");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tunnelSemaphore.release();
        }
    }
}
