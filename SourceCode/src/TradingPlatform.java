import java.util.Scanner;

public class TradingPlatform {
    private final Market market;
    private final User user;
    private final Scanner scanner;

    public TradingPlatform(String userName, double initialBalance) {
        market = new Market();
        user = new User(userName, initialBalance);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("========================================");
        System.out.println("       STOCK TRADING PLATFORM");
        System.out.println("========================================");
        System.out.println("Welcome, " + user.getName() + "!");
        System.out.printf("Starting Balance: $%.2f%n", user.getBalance());

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> market.displayMarketData();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> displayPortfolio();
                case 5 -> displayTransactions();
                case 6 -> addMoney();
                case 7 -> displayPerformance();
                case 8 -> {
                    saveData();
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        System.out.println("Thank you for using Stock Trading Platform!");
    }

    private void showMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Display Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. View Transaction History");
        System.out.println("6. Add Money");
        System.out.println("7. Portfolio Performance");
        System.out.println("8. Save & Exit");
        System.out.println("===============================");
    }

    private void buyStock() {
        market.displayMarketData();

        String symbol = readString("Enter stock symbol: ").toUpperCase();
        Stock stock = market.getStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        int quantity = readInt("Enter quantity: ");

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        double total = stock.getPrice() * quantity;

        if (user.buyStock(stock, quantity)) {
            System.out.printf(
                    "Successfully purchased %d shares of %s for $%.2f%n",
                    quantity, symbol, total
            );
            System.out.printf("Remaining Balance: $%.2f%n", user.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void sellStock() {
        if (user.getPortfolio().isEmpty()) {
            System.out.println("Your portfolio is empty.");
            return;
        }

        displayPortfolio();

        String symbol = readString("Enter stock symbol to sell: ").toUpperCase();
        Stock stock = market.getStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        int owned = user.getPortfolio().getQuantity(symbol);

        if (owned == 0) {
            System.out.println("You do not own this stock.");
            return;
        }

        int quantity = readInt("Enter quantity: ");

        if (quantity <= 0 || quantity > owned) {
            System.out.println("Invalid quantity.");
            return;
        }

        double total = stock.getPrice() * quantity;

        if (user.sellStock(stock, quantity)) {
            System.out.printf(
                    "Successfully sold %d shares of %s for $%.2f%n",
                    quantity, symbol, total
            );
            System.out.printf("New Balance: $%.2f%n", user.getBalance());
        } else {
            System.out.println("Sale failed.");
        }
    }

    private void displayPortfolio() {
        System.out.println("\n========== MY PORTFOLIO ==========");

        if (user.getPortfolio().isEmpty()) {
            System.out.println("No stocks in portfolio.");
            System.out.printf("Cash Balance: $%.2f%n", user.getBalance());
            return;
        }

        System.out.printf(
                "%-8s %-12s %-15s %-15s%n",
                "SYMBOL", "QUANTITY", "PRICE", "CURRENT VALUE"
        );
        System.out.println("--------------------------------------------------------");

        double totalValue = 0;

        for (var entry : user.getPortfolio().getHoldings().entrySet()) {
            Stock stock = market.getStock(entry.getKey());

            if (stock != null) {
                double value = stock.getPrice() * entry.getValue();
                totalValue += value;

                System.out.printf(
                        "%-8s %-12d $%-14.2f $%-14.2f%n",
                        stock.getSymbol(),
                        entry.getValue(),
                        stock.getPrice(),
                        value
                );
            }
        }

        System.out.println("--------------------------------------------------------");
        System.out.printf("Stock Value:    $%.2f%n", totalValue);
        System.out.printf("Cash Balance:   $%.2f%n", user.getBalance());
        System.out.printf("Total Account:  $%.2f%n", totalValue + user.getBalance());
    }

    private void displayTransactions() {
        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (user.getTransactions().isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : user.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private void addMoney() {
        double amount = readDouble("Enter amount to add: ");

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        user.addMoney(amount);
        System.out.printf("Money added successfully. Balance: $%.2f%n", user.getBalance());
    }

    private void displayPerformance() {
        double currentPortfolioValue =
                user.getPortfolio().getCurrentValue(market);

        double totalAccountValue =
                currentPortfolioValue + user.getBalance();

        double totalBuyCost = 0;
        double totalSellRevenue = 0;

        for (Transaction transaction : user.getTransactions()) {
            if (transaction.getType() == Transaction.Type.BUY) {
                totalBuyCost += transaction.getTotal();
            } else {
                totalSellRevenue += transaction.getTotal();
            }
        }

        double netInvestment = totalBuyCost - totalSellRevenue;
        double profitLoss = currentPortfolioValue - netInvestment;

        System.out.println("\n========== PORTFOLIO PERFORMANCE ==========");
        System.out.printf("Current Stock Value: $%.2f%n", currentPortfolioValue);
        System.out.printf("Cash Balance:        $%.2f%n", user.getBalance());
        System.out.printf("Total Account Value:  $%.2f%n", totalAccountValue);
        System.out.printf("Net Investment:       $%.2f%n", netInvestment);
        System.out.printf("Profit/Loss:          $%.2f%n", profitLoss);

        if (netInvestment > 0) {
            double percentage = (profitLoss / netInvestment) * 100;
            System.out.printf("Return:               %.2f%%%n", percentage);
        } else {
            System.out.println("Return:               N/A");
        }
    }

    private void saveData() {
        FileManager fileManager = new FileManager("data/portfolio.txt");
        fileManager.saveUser(user, market);
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
