public class EncryptionDecorator extends BaseDataSourceDecorator {

    private static final String PREFIX = "encrypted: ";

    public EncryptionDecorator(DataSource wrappee) {
        super(wrappee); // functia care impacheteaza
    }

    private String reverse(String data) {
        return new StringBuilder(data).reverse().toString(); // inversare a sirului de caractere
    }

    @Override
    public void Write(String data) {
        String reversedData = reverse(data);

        System.out.println("Reverse encription: " + reversedData);

        System.out.println("Reverse result: " + reversedData);

        _wrappee.Write(reversedData);
    }

    @Override
    public String Read() {

        System.out.println("Decrypt reverse: ");

        String reversedData = _wrappee.Read();

        if (reversedData == null || reversedData.isEmpty())
            return reversedData;
        System.out.println("Data reversed decrypted: ");

        System.out.println("Received data: " + reversedData);

        String originalData = reverse(reversedData);

        return originalData;
    }
}
