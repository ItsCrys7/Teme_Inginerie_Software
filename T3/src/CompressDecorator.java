public class CompressDecorator extends BaseDataSourceDecorator{
    public CompressDecorator(DataSource wrappee)
    {
        super(wrappee);
    }

    @Override
    public void Write(String data) {
        System.out.println("Se comprima: " + data);
        _wrappee.Write("Compress:" + data);
    }

    @Override
    public String Read() {
        String data = _wrappee.Read();
        if (data == null) return null;
        if (data.startsWith("Compress:")) {
            String uncompressed = data.substring("Compress:".length());
            System.out.println("Se decomprima: " + uncompressed);
            return uncompressed;
        }
        System.out.println("Se decomprima: " + data);
        return data;
    }
}
