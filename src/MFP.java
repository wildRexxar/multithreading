public class MFP {

    private final Object printMonitor = new Object();
    private final Object scanMonitor = new Object();

    public void scan (int count) {

            try {
                for(int i = 0; i < count; i++) {
                    Thread.sleep(10);
                    synchronized (scanMonitor) {
                        System.out.println("Scanned " + i + "pages");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public void print (int count) {
        synchronized (printMonitor) {
            try {
                for(int i = 0; i < count; i++) {
                    Thread.sleep(10);
                    System.out.println("Printed " + i + " pages");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
