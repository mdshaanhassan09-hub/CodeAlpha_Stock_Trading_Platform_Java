import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Market {
    private final Map<String, Stock> stocks = new LinkedHashMap<>();

    public Market() {
        loadDefaultStocks();
    }

    private void loadDefaultStocks() {
        addStock(new Stock("AAPL", "Apple Inc.", 225.50));
        addStock(new Stock("GOOGL", "Alphabet Inc.", 201.32));
        addStock(new Stock("MSFT", "Microsoft Corp.", 511.20));
        addStock(new Stock("TSLA", "Tesla Inc.", 340.15));
        addStock(new Stock("AMZN", "Amazon.com Inc.", 230.45));
        addStock(new Stock("NVDA", "NVIDIA Corp.", 181.20));
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol().toUpperCase(), stock);
    }

    public Stock getStock(String symbol) {
        if (symbol == null) {
            return null;
        }
        return stocks.get(symbol.toUpperCase());
    }

    public Collection<Stock> getAllStocks() {
        return stocks.values();
    }

    public void displayMarketData() {
        System.out.println("\n========== MARKET DATA ==========");
        System.out.printf("%-8s %-22s %s%n", "SYMBOL", "COMPANY", "PRICE");
        System.out.println("-----------------------------------------------");

        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }

        System.out.println("-----------------------------------------------");
    }

    // Simulates a small price update for demonstration purposes.
    public void simulateMarketMovement() {
        for (Stock stock : stocks.values()) {
            double changePercent = (Math.random() * 4.0) - 2.0;
            double newPrice = stock.getPrice() * (1 + changePercent / 100);
            stock.setPrice(Math.max(1, newPrice));
        }
    }
}
