import java.util.LinkedHashMap;
import java.util.Map;

public class Portfolio {
    private final Map<String, Integer> holdings = new LinkedHashMap<>();

    public void addShares(String symbol, int quantity) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
    }

    public boolean removeShares(String symbol, int quantity) {
        int current = holdings.getOrDefault(symbol, 0);

        if (current < quantity) {
            return false;
        }

        int remaining = current - quantity;

        if (remaining == 0) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
        }

        return true;
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public double getCurrentValue(Market market) {
        double total = 0;

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.getStock(entry.getKey());

            if (stock != null) {
                total += entry.getValue() * stock.getPrice();
            }
        }

        return total;
    }

    public boolean isEmpty() {
        return holdings.isEmpty();
    }
}
