package ATMbankjava;

public class SavingsAccount extends Account {

    public SavingsAccount(
            int accountNumber,
            String name,
            String pin,
            double balance) {
        super(accountNumber, name, pin, balance);
    }

    @Override
    public void showAccountType() {
        System.out.println("Account Type: Savings");
    }
}