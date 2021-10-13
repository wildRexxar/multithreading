import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    private AtomicInteger value = new AtomicInteger();

    public void inc() {
        value.getAndIncrement();

    }

    public void dec() {
        value.getAndDecrement();

    }

    public int getValue() {
        return value.intValue();
    }
}
