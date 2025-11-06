public class StorageDataSource implements DataSource{
    private String storage; // salveaza data

    @Override
    public void Write(String data) {
        storage = data + System.lineSeparator();
        System.out.println("Se salveaza: "  + System.lineSeparator() + data);
    }

    @Override
    public String Read() {
        return storage != null ? storage : "Storage data read";
    }
}
