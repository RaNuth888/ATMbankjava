import java.util.ArrayList;

public abstract class Account {

private int accountNumber;
private String name;
private String pin;
private double balance;
private boolean locked;
private int wrongAttempts;
private double dailyWithdraw;

private ArrayList<String> transactions;
public Account(int accountNumber, String name, String pin, double balance){

    this.accountNumber = accountNumber;
    this.name = name;
    this.pin = pin;
    this.balance = balance;
    locked = false;
    wrongAttempts = 0;
    dailyWithdraw = 0;
    transactions = new ArrayList<>();}
    
public int getAccountNumber(){
    return accountNumber;
}

public String getName() {
    return name;
}

public double getBalance() {
    return balance;
}

public boolean isLocked() {
    return locked;
}

public boolean checkPin(String pin) {

if (this.pin.equals(pin)) {
    wrongAttempts = 0;
    return true;
}
    wrongAttempts++;

if (wrongAttempts == 3) {
    locked = true;
}
    return false;
}

public void unlock(){
    
    locked = false;
    wrongAttempts = 0;
}

public void changePin(String newPin) {
    pin = newPin;
}
public void deposit(double amount) {

    balance = balance + amount;
    transactions.add(
    "Deposit: $" + amount
    );
}

public void withdraw(double amount)
    throws InsufficientFundsException {
    if (amount <= 0) {
    throw new InsufficientFundsException(
        "Invalid amount."
    );
}

if (dailyWithdraw + amount > 1000) {

throw new InsufficientFundsException(
    "Daily withdrawal limit is $1000."
);
}

if (amount > balance) {
throw new InsufficientFundsException(
    "Not enough money."
);
}
balance = balance - amount;
dailyWithdraw = dailyWithdraw + amount;
transactions.add(
"Withdraw: $" + amount
 );
}
public void removeMoney(double amount) {
balance = balance - amount;
}
public void addMoney(double amount) {
balance = balance + amount;
}
public void addTransaction(String text) {
transactions.add(text);
}
public ArrayList<String> getTransactions() {
return transactions;
}
public abstract void showAccountType();
}