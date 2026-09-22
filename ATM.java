import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner input;

    public ATM(Bank bank) {
        this.bank = bank;
        input = new Scanner(System.in);
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("       ATM BANKING SYSTEM");
            System.out.println("==============================");

            System.out.println("1. User Login");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");

            System.out.println("==============================");

            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            if (choice == 1) {
                userLogin();
            } else if (choice == 2) {
                adminMode();
            } else if (choice == 3) {
                System.out.println("Thank you for using the ATM.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void userLogin() {

        System.out.println();
        System.out.println("========== USER LOGIN ==========");
        System.out.print("Enter Account Number: ");

        int number = input.nextInt();
        Account account = bank.findAccount(number);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        if (account.isLocked()) {
            System.out.println("Account is locked.");
            return;
        }
        System.out.print("Enter PIN: ");
        String pin = input.next();
        if (account.checkPin(pin)) {
            System.out.println("Login successful!");

            System.out.println();

            System.out.println("Welcome, " + account.getName());

            userMenu(account);
        } else {

            System.out.println("Wrong PIN.");

            if (account.isLocked()) {
                System.out.println("Account locked after 3 attempts.");
            }
        }
    }

    private void userMenu(Account account) {

        while (true) {

            System.out.println();
            System.out.println("========== USER MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Change PIN");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            if (choice == 1) {

                checkBalance(account);

            } else if (choice == 2) {

                deposit(account);

            } else if (choice == 3) {

                withdraw(account);

            } else if (choice == 4) {

                transfer(account);

            } else if (choice == 5) {

                showHistory(account);

            } else if (choice == 6) {

                changePin(account);

            } else if (choice == 7) {

                System.out.println("Logged out.");
                break;

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    
    private void checkBalance(Account account) {

        System.out.println();
        System.out.println("========== BALANCE ==========");
        System.out.println("Current Balance: $" + account.getBalance());
    }

    private void deposit(Account account) {

        System.out.println();
        System.out.println("========== DEPOSIT ==========");
        System.out.print("Enter amount: $");

        double amount = input.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid amount.");
            return;
        }

        account.deposit(amount);

        System.out.println("Deposit successful.");
        System.out.println("New Balance: $" + account.getBalance());
    }

    private void withdraw(Account account) {

        System.out.println();
        System.out.println("========== WITHDRAW ==========");
        System.out.print("Enter amount: $");

        double amount = input.nextDouble();

        try {
            account.withdraw(amount);

            System.out.println("Withdrawal successful.");
            System.out.println("Withdrawn: $" + amount);
            System.out.println("Remaining Balance: $" + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void transfer(Account sender) {

        System.out.println();
        System.out.println("========== TRANSFER ==========");
        System.out.print("Enter receiver account number: ");

        int number = input.nextInt();

        Account receiver = bank.findAccount(number);

        if (receiver == null) {
            System.out.println("Account not found.");
            return;
        }

        if (receiver == sender) {
            System.out.println("Cannot transfer to yourself.");
            return;
        }

        System.out.print("Enter transfer amount: $");

        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > sender.getBalance()) {
            System.out.println("Not enough money.");
            return;
        }

        sender.removeMoney(amount);

        receiver.addMoney(amount);

        sender.addTransaction("Transfer to Account " + number + ": $" + amount);
        receiver.addTransaction("Transfer from Account " + sender.getAccountNumber() + ": $" + amount);

        System.out.println();
        System.out.println("Transfer successful.");
        System.out.println("Transferred: $" + amount);
        System.out.println("To Account: " + number);
        System.out.println("Remaining Balance: $" + sender.getBalance());
    }

    private void showHistory(Account account) {

        System.out.println();
        System.out.println("===== TRANSACTION HISTORY =====");

        if (account.getTransactions().size() == 0) {

            System.out.println("No transactions.");
        } else {

            for (String transaction :
                    account.getTransactions()) {

                System.out.println(transaction);
            }
        }
    }

    private void changePin(Account account) {

        System.out.println();
        System.out.println("========== CHANGE PIN ==========");
        System.out.print("Enter old PIN: ");

        String oldPin = input.next();

        if (account.checkPin(oldPin)) {
            System.out.print("Enter new PIN: ");
            String newPin = input.next();

            account.changePin(newPin);
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Wrong old PIN.");
        }
    }

    private void adminMode() {

        while (true) {

            System.out.println();
            System.out.println("========== ADMIN MODE ==========");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Unlock Account");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            if (choice == 1) {

                createAccount();

            } else if (choice == 2) {

                bank.showAccounts();

            } else if (choice == 3) {

                unlockAccount();

            } else if (choice == 4) {

                System.out.println("Leaving Admin Mode.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void createAccount() {

        System.out.println();

        System.out.println("======= CREATE ACCOUNT =======");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        System.out.print("Choose type: ");

        int type = input.nextInt();

        if (type != 1 && type != 2) {

            System.out.println("Invalid account type.");
            return;
        }

        System.out.print("Enter Account Number: ");
        int number = input.nextInt();

        if (bank.findAccount(number) != null) {

            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = input.next();

        System.out.print("Enter PIN: ");
        String pin = input.next();

        System.out.print("Enter Starting Balance: $");
        double balance = input.nextDouble();

        if (balance < 0) {

            System.out.println("Balance cannot be negative.");
            return;
        }

        if (type == 1) {

            Account account = new CheckingAccount(number, name, pin, balance);
            bank.addAccount(account);

        } else {

            Account account = new SavingsAccount(number,name, pin, balance);
            bank.addAccount(account);
        }

        System.out.println();
        System.out.println("Account created successfully!");
    }

    private void unlockAccount() {

        System.out.println();

        System.out.println("========== UNLOCK ACCOUNT ==========");
        System.out.print("Enter Account Number: ");

        int number = input.nextInt();
        Account account = bank.findAccount(number);

        if (account == null) {
            System.out.println("Account not found.");
        } else {

            account.unlock();
            System.out.println("Account unlocked successfully.");
        }
    }
}