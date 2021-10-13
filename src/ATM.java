import java.util.Objects;

public class ATM {
    private int amount;

    private final Object object = new Object();

    public ATM(int amount) {
        this.amount = amount;
    }

    public  void withdraw(String name, int amount) {
        synchronized (object) {
            System.out.println(name + " went to the ATM");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (amount <= this.amount) {
                this.amount -= amount;
                System.out.println(name + " withdrew " + amount);
                System.out.println("Left: " + this.amount);
            } else {
                System.out.println("There is not enough money in the account for " + name);
            }
        }
    }
}
