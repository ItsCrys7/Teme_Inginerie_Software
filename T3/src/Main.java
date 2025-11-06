public class Main {
    public static void main(String[] args) {
        // ordinea: EncryptionDecorator (extern) -> CompressDecorator -> StorageDataSource
        // la Write: Se cripteaza -> Se comprima -> Se salveaza
        // la Read: Se decripteaza -> Se decomprima -> (se returneaza textul original)
        DataSource ds = new EncryptionDecorator(new CompressDecorator(new StorageDataSource()));

        ds.Write("Hello");
        String read = ds.Read();
        System.out.println("Rezultat final: " + read);
    }
}
