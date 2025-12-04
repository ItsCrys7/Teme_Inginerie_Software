public interface IUser {
    void SendMessage(int userId, String message);
    void ReceiveMessage(String senderName, String message);
    
    int getId();
    String getName();
    
    boolean getIsBusy();
    void setIsBusy(boolean busy);

    int getLocation();
    void setLocation(int location);
}