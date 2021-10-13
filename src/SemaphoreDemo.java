import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i  <10; i++) {
            executorService.execute(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + " started working.");
                try {
                    Thread.sleep(500);
                    semaphore.acquire();
                    workWithFilesSystem();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println(name + " finished working.");
            });
        }
        executorService.shutdown();

    }
    private static void workWithFilesSystem() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started working with file system.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " finished working with file system");
    }
}
