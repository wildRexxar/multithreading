public class Counter {

    private Object monitor = new Object();
    private int value;

    public void inc(){
        synchronized (monitor) {
            value++;
        }
    }

    public  void dec(){
        synchronized (monitor) {
            value--;
        }
    }

    public int getValue(){
        return value;
    }
}
