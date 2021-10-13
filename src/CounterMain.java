import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.List;

public class CounterMain {
    public static void main(String[] args) {
        Counter counter = new Counter();

//        Thread timer = new Thread(() -> {
//            int i = 0;
//            try {
//                while(true){
//                    System.out.println(i++);
//                    Thread.sleep(10);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        Thread thread1 = new Thread(() ->{
            for(int i = 0; i < 2_000; i ++){
                counter.dec();
            }
        });

        Thread thread2 = new Thread(() ->{
            for(int i = 0; i < 2_000; i ++){
                counter.inc();
            }
        });
//        timer.setDaemon(true);
//        timer.start();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getValue());
    }
}
