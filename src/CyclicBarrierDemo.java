import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                long millis = (long) (Math.random() * 5000 + 1000);
                String name = Thread.currentThread().getName();
                System.out.println(name + " : Data is being prepares");
                try{
                    Thread.sleep(millis);
                    System.out.println(name + " : Data is ready");
                    cyclicBarrier.await();
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println(name + " : Continue work");
            }).start();
        }
    }
}
