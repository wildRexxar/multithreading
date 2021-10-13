import jdk.management.resource.internal.inst.InitInstrumentation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CDL {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newSingleThreadExecutor();


        Runnable task1 = () -> {
            long sum = 0;
            for (int i = 0; i < 1_000_000.; i++) {
                if (i % 2 == 0) {
                    sum += i;
                }
            }
            System.out.println("Task 1 : " + sum);
            countDownLatch.countDown();
        };


        Runnable task2 = (() -> {
            long sum = 0;
            for (int i = 0; i < 1_000_000; i++) {
                if (i % 7 == 0) {
                    sum += i;
                }
            }
            System.out.println("Task 2  : " + sum);
            countDownLatch.countDown();
        });

        Runnable task3 = (() -> {
            int count = 0;
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < 1000; i++) {
                list.add((int) (Math.random() * 1000));
            }
            for (int arr : list) {
                if (arr % 2 == 0) {
                    count++;
                }
            }
            System.out.println("Task 3 : " + count);
            countDownLatch.countDown();
        });

        long before = System.currentTimeMillis();
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);
        executorService.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis();
        System.out.println("Time : " + (after - before));
    }
}


