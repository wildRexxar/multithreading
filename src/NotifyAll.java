public class NotifyAll {

    private static final Object monitor = new Object();
    private static final String A ="A";
    private static final String B ="B";
    private static final String C ="C";
    private static String nextLetter = A;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while(!nextLetter.equals(A)){
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(A);
                    nextLetter = B;
                    monitor.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (!nextLetter.equals(B)) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(B);
                    nextLetter = C;
                    monitor.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (!nextLetter.equals(C)) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(C + "  ");
                    nextLetter = A;
                    monitor.notifyAll();
                }
            }
        }).start();
    }
}
