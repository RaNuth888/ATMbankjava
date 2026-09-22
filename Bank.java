import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(int number) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == number) {
                return account;
            }
        }
        return null;
    }

    public void showAccounts() {
        if (accounts.size() == 0) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println();
        for (Account account : accounts) {

            System.out.println("----------------------------" );
            System.out.println( "Account Number: " + account.getAccountNumber());
            System.out.println( "Name: " + account.getName());
            System.out.println( "Balance: $" + account.getBalance());

            account.showAccountType();

            if (account.isLocked()) {
                System.out.println("Status: Locked");
            } else {
                System.out.println("Status: Active");
            }
        }
        System.out.println("----------------------------");
    }
}