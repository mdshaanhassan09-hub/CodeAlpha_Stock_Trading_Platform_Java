import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveUser(User user, Market market) {
        File file = new File(filePath);

        try {
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("USER|" + user.getName());
                writer.newLine();
                writer.write("BALANCE|" + user.getBalance());
                writer.newLine();

                for (var entry : user.getPortfolio().getHoldings().entrySet()) {
                    writer.write("HOLDING|" + entry.getKey() + "|" + entry.getValue());
                    writer.newLine();
                }

                for (Transaction transaction : user.getTransactions()) {
                    writer.write("TRANSACTION|" + transaction.toFileString());
                    writer.newLine();
                }
            }

            System.out.println("Portfolio saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Could not save portfolio: " + e.getMessage());
        }
    }
}
