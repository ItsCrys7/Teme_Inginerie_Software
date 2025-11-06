public class EncryptionDecorator extends BaseDataSourceDecorator{

    private static final String PREFIX = "encrypted: ";

    public EncryptionDecorator(DataSource wrappee)
    {
        super(wrappee); // functia care impacheteaza
    }

    @Override
    public void Write(String data) {
        System.out.println("Se cripteaza: " + data);
        _wrappee.Write(PREFIX + data);
    }

    @Override
    public String Read() {
        System.out.println("Se decripteaza");
        String data = _wrappee.Read();
        if (data == null) return null;
        if (data.startsWith(PREFIX)) {
            return data.substring(PREFIX.length());
        }
        return data;
    }
}
