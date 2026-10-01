import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create Bank
        Bank bank = new Bank();

        // Create sample accounts
        Account hemaAccount = new Account(
                "1001",
                "hema",
                "1234",
                10000
        );

        Account rahulAccount = new Account(
                "1002",
                "rahul",
                "5678",
                5000
        );

        // Add accounts to the bank
        bank.addAccount(hemaAccount);
        bank.addAccount(rahulAccount);

        // Create ATM
        ATM atm = new ATM(bank);

        System.out.println("=================================");
        System.out.println("        WELCOME TO ATM");
        System.out.println("=================================");

        boolean loggedIn = false;
        int attempts = 0;

        // Login
        while (attempts < 3 && !loggedIn) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (atm.login(userId, pin)) {

                loggedIn = true;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, " + userId + "!");

            } else {

                attempts++;

                System.out.println("Invalid User ID or PIN.");

                if (attempts < 3) {
                    System.out.println(
                            "Attempts remaining: " + (3 - attempts)
                    );
                }
            }
        }

        // Deny access after 3 incorrect attempts
        if (!loggedIn) {

            System.out.println("\nAccess denied.");
            System.out.println("Too many incorrect attempts.");
            System.out.println("Thank you for using the ATM.");

            scanner.close();
            return;
        }

        // Main ATM menu
        boolean running = true;

        while (running) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    atm.showTransactionHistory();
                    break;

                case "2":

                    System.out.print("Enter withdrawal amount: ");

                    try {
                        double withdrawAmount =
                                Double.parseDouble(scanner.nextLine());

                        atm.withdraw(withdrawAmount);

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter a valid amount.");
                    }

                    break;

                case "3":

                    System.out.print("Enter deposit amount: ");

                    try {
                        double depositAmount =
                                Double.parseDouble(scanner.nextLine());

                        atm.deposit(depositAmount);

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter a valid amount.");
                    }

                    break;

                case "4":

                    System.out.print("Enter recipient account ID: ");
                    String recipientId = scanner.nextLine();

                    System.out.print("Enter transfer amount: ");

                    try {
                        double transferAmount =
                                Double.parseDouble(scanner.nextLine());

                        atm.transfer(
                                recipientId,
                                transferAmount
                        );

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter a valid amount.");
                    }

                    break;

                case "5":

                    System.out.println("\nThank you for using the ATM.");
                    System.out.println("Goodbye!");

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please select 1 to 5."
                    );
            }
        }

        scanner.close();
    }
}