import java.util.ArrayList;

public class ATM {

    private Account currentAccount;
    private Bank bank;
    private ArrayList<Transaction> transactions;

    public ATM(Bank bank) {
        this.bank = bank;
        this.transactions = new ArrayList<>();
    }

    public boolean login(String userId, String pin) {

        Account account = bank.findByUserId(userId);

        if (account != null && account.getPin().equals(pin)) {
            currentAccount = account;
            return true;
        }

        return false;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (currentAccount.getBalance() < amount) {
            System.out.println("Insufficient Funds.");
            return;
        }

        currentAccount.setBalance(currentAccount.getBalance() - amount);

        transactions.add(
            new Transaction(
                "Withdraw",
                amount,
                "Cash withdrawn"
            )
        );

        System.out.println("Withdrawal successful.");
        System.out.println("Amount withdrawn: Rs." + amount);
        System.out.println("Remaining balance: Rs." + currentAccount.getBalance());
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        currentAccount.setBalance(currentAccount.getBalance() + amount);

        transactions.add(
            new Transaction(
                "Deposit",
                amount,
                "Cash deposited"
            )
        );

        System.out.println("Deposit successful.");
        System.out.println("Amount deposited: Rs." + amount);
        System.out.println("Current balance: Rs." + currentAccount.getBalance());
    }

    public void transfer(String recipientAccountId, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (currentAccount.getBalance() < amount) {
            System.out.println("Insufficient Funds.");
            return;
        }

        Account recipient = bank.findByAccountId(recipientAccountId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient == currentAccount) {
            System.out.println("You cannot transfer money to the same account.");
            return;
        }

        currentAccount.setBalance(
            currentAccount.getBalance() - amount
        );

        recipient.setBalance(
            recipient.getBalance() + amount
        );

        transactions.add(
            new Transaction(
                "Transfer",
                amount,
                "Transfer to account " + recipientAccountId
            )
        );

        System.out.println("Transfer successful.");
        System.out.println("Amount transferred: ₹" + amount);
        System.out.println("Transferred to account: " + recipientAccountId);
        System.out.println("Remaining balance: Rs." + currentAccount.getBalance());
    }

    public void showTransactionHistory() {

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println("=========================================");
    }

    public double getBalance() {
        return currentAccount.getBalance();
    }

    public String getAccountId() {
        return currentAccount.getAccountId();
    }
}