import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ThreadFactory {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new java.util.concurrent.ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int seconds  = 0;
                while(true){
                    try {
                        System.out.println(seconds);
                        Thread.sleep(1000);
                        seconds++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        for(int i = 0; i < 10; i++){
            System.out.println("...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finish");
    }

    public abstract Thread newThread(Runnable r);
}
