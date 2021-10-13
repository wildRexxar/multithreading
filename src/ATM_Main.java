public class ATM_Main {
    public static void main(String[] args) {
        ATM atm = new ATM(1000);

        Thread thread1 = new Thread(() -> {
            atm.withdraw("Max", 300);
        });

        Thread thread2 = new Thread(() -> {
            atm.withdraw("John", 500);
        });

        Thread thread3 = new Thread(() -> {
            atm.withdraw("Nick", 400);
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
