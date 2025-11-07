public class CompressDecorator extends BaseDataSourceDecorator{
    public CompressDecorator(DataSource wrappee)
    {
        super(wrappee);
    }

    @Override
// e imp sa nu umplem Metoda Write cu logica de compresie sau cu o functie, 
//ci e mai bine sa o facem in alta parte, iar in Write doar sa apelam acea functie
    public void Write(String data) { 
        System.out.println("Compress: " + data);
        _wrappee.Write("Compress:" + data);
    }

    @Override
    public String Read() {
        String data = _wrappee.Read();
        if (data == null) return null;
        if (data.startsWith("Compress:")) {
            String uncompressed = data.substring("Compress:".length());
            System.out.println("Decompress: " + uncompressed);
            return uncompressed;
        }
        System.out.println("Decompress: " + data);
        return data;
    }
}
