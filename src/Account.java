public class Account {
    private int amount1;
    private int amount2;

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    public Account(int account1, int account2) {
        this.amount1 = account1;
        this.amount2 = account2;
    }

    public void transferFrom1To2(int amount) {
        synchronized (monitor1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(amount <= amount1) {
                System.out.println("amount <= amount1");
                synchronized (monitor2){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    amount1 -=amount;
                    amount2 +=amount;
                }

            } else {
                System.out.println("Insufficient funds");
            }
        }
    }

    public void transferFrom2To1(int amount) {
        synchronized (monitor2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(amount <= amount2) {
                System.out.println("amount <= amount2");
                synchronized (monitor1){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    amount2 -=amount;
                    amount1 +=amount;
                }

            } else {
                System.out.println("Insufficient funds");
            }
        }
    }
}
