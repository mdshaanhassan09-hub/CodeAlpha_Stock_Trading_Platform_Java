public class Stock {
    private final String symbol;
    private final String companyName;
    private double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Stock price cannot be negative.");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-22s $%.2f", symbol, companyName, price);
    }
}
