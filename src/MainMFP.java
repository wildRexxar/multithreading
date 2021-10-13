import java.security.spec.MGF1ParameterSpec;
import java.util.concurrent.CountDownLatch;

public class MainMFP {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        MFP mfp = new MFP();

        new Thread(() -> {
            mfp.scan(100);
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            mfp.scan(100);
            countDownLatch.countDown();
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run(){
                mfp.scan(100);
                countDownLatch.countDown();
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
