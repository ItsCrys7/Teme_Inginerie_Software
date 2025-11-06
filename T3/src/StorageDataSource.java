import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StorageDataSource implements DataSource {
    private String fileName; // salveaza data in fisier

    public StorageDataSource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void Write(String data) {
        System.out.println("Saves in the file: " + fileName);
        System.out.println("Data : " + data);

        byte[] dataBytes = data.getBytes();

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(dataBytes);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String Read() {
        Path filePath = Path.of(fileName);

        if (!Files.exists(filePath)) {
            System.out.println("File " + fileName + " does not exist yet: ");

            return "";
        }

        System.out.println("Reads from the file: " + fileName);

        try {
            byte[] dataBytes = Files.readAllBytes(filePath);

            return new String(dataBytes);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }
}
