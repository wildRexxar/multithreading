public class MainAtomic {
    public static void main(String[] args) {
        Atomic atomic = new Atomic();
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                atomic.inc();
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++){
                atomic.dec();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
