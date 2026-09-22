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
    