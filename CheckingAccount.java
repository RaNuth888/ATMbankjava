public class CheckingAccount extends Account {

    public CheckingAccount(
            int accountNumber,
            String name,
            String pin,
            double balance) {
        super(accountNumber, name, pin, balance);
    }
    @Override
    public void showAccountType() {
        System.out.println("Account Type: Checking");
    }
}