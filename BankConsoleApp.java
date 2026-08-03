import java.util.HashMap;
import java.util.Scanner;

class Account {
    int id;
    String customerName;
    double balance;

    Account(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.balance = 0;
    }
}

class AccountNotFoundException extends Exception {}

class InsufficientFundsException extends Exception {}

public class BankConsoleApp {

    static HashMap<Integer, Account> accounts = new HashMap<>();
    static int nextId = 1;

    public static int createAccount(String name) {
        Account a = new Account(nextId, name);
        accounts.put(nextId, a);
        return nextId++;
    }

    public static void deposit(int id, double amount)
            throws AccountNotFoundException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException();

        accounts.get(id).balance += amount;
    }

    public static void withdraw(int id, double amount)
            throws AccountNotFoundException,
            InsufficientFundsException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException();

        Account a = accounts.get(id);

        if (a.balance < amount)
            throw new InsufficientFundsException();

        a.balance -= amount;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:
                        sc.nextLine();

                        System.out.print("Enter customer name: ");
                        String name = sc.nextLine();

                        int id = createAccount(name);

                        System.out.println("Account created");
                        System.out.println("Account ID: " + id);
                        break;

                    case 2:
                        System.out.print("Enter account ID: ");
                        int dId = sc.nextInt();

                        System.out.print("Enter amount: ");
                        double dAmount = sc.nextDouble();

                        deposit(dId, dAmount);

                        System.out.println("Deposit successful");
                        break;

                    case 3:
                        System.out.print("Enter account ID: ");
                        int wId = sc.nextInt();

                        System.out.print("Enter amount: ");
                        double wAmount = sc.nextDouble();

                        withdraw(wId, wAmount);

                        System.out.println("Withdrawal successful");
                        break;

                    case 4:
                        System.out.println("Thank you!");
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (AccountNotFoundException e) {
                System.out.println("Account not found");
            } catch (InsufficientFundsException e) {
                System.out.println("Insufficient balance");
            }
        }
    }
}