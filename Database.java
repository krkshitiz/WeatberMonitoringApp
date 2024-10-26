import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Database {
    private static final String FILE_PATH = "data/weather_summary.txt";

    public void saveSummary(String summary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(summary);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving summary: " + e.getMessage());
        }
    }
}
