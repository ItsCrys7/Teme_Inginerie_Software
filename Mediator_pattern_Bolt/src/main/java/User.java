public class User implements IUser {
    protected int id;
    protected String name;
    protected IServer server;
    protected boolean isBusy;
    protected int location;

    public User(int id, String name, IServer server, int location) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.location = location;
    }

    public User(int id, String name, IServer server) {
        this(id, name, server, 0);
    }

    public void SendMessage(int userId, String message) {
        server.SendMessage(id, userId, message);
    }

    public void ReceiveMessage(String senderName, String message) {
        System.out.println(name + " Received message from " + senderName + ": " + message);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public boolean getIsBusy() {
        return isBusy;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}