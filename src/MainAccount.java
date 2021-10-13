public class MainAccount {
    public static void main(String[] args) {
        Account account = new Account(1000, 1000);
        new Thread(() -> {
            account.transferFrom1To2(600);
        }).start();

        new Thread(() -> {
            account.transferFrom2To1(1500);
        }).start();
    }
}
