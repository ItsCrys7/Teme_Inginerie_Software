public class Main {
    public static void main(String[] args) {
        
        String fileName = "my_data.txt";

        DataSource storage = new StorageDataSource(fileName);

        DataSource ds = new EncryptionDecorator(new CompressDecorator(storage));

        ds.Write("Hello There World!");
        System.out.println("--- The data has been written to " + fileName + " ---");

        String read = ds.Read();

        System.out.println("--- The data has been read and processed ---");
        System.out.println("Final result: " + read); 
    }
}