import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvLoggerService {
    private static final String FILE_PATH = "actions_log.csv";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CsvLoggerService() {
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
        }
    }

    public void logAction(String actionName) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            writer.append(actionName).append(",").append(timestamp).append("\n");
        } catch (IOException e) {
            System.err.println("Failed to log action: " + e.getMessage());
        }
    }
}