public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        Account account = new CheckingAccount(111, "Vannda", "111", 1000);
        bank.addAccount(account);
        ATM atm = new ATM(bank);
        atm.start();
    }
}