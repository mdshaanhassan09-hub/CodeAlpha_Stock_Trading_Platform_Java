import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private double balance;
    private final Portfolio portfolio;
    private final List<Transaction> transactions;

    public User(String name, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.name = name;
        this.balance = initialBalance;
        this.portfolio = new Portfolio();
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        balance += amount;
    }

    public boolean buyStock(Stock stock, int quantity) {
        if (stock == null || quantity <= 0) {
            return false;
        }

        double totalCost = stock.getPrice() * quantity;

        if (balance < totalCost) {
            return false;
        }

        balance -= totalCost;
        portfolio.addShares(stock.getSymbol(), quantity);
        transactions.add(new Transaction(
                Transaction.Type.BUY,
                stock.getSymbol(),
                quantity,
                stock.getPrice()
        ));

        return true;
    }

    public boolean sellStock(Stock stock, int quantity) {
        if (stock == null || quantity <= 0) {
            return false;
        }

        if (!portfolio.removeShares(stock.getSymbol(), quantity)) {
            return false;
        }

        double totalReceived = stock.getPrice() * quantity;
        balance += totalReceived;

        transactions.add(new Transaction(
                Transaction.Type.SELL,
                stock.getSymbol(),
                quantity,
                stock.getPrice()
        ));

        return true;
    }
}
