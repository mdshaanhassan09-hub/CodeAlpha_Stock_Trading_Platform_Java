import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum Type {
        BUY, SELL
    }

    private final Type type;
    private final String symbol;
    private final int quantity;
    private final double price;
    private final LocalDateTime dateTime;

    public Transaction(Type type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public Type getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format(
                "%-5s %-8s Qty: %-5d Price: $%-10.2f Total: $%-10.2f Date: %s",
                type, symbol, quantity, price, getTotal(), dateTime.format(formatter)
        );
    }

    public String toFileString() {
        return type + "|" + symbol + "|" + quantity + "|" + price + "|" + dateTime;
    }
}
