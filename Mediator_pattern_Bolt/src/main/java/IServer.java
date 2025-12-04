public interface IServer {
    void RegisterUser(IUser user);
    void Unregister(IUser user);
    void SendMessage(int userIdSender, int userIdReceiver, String message);
    
    // Metoda nouă: Cere o cursă
    void RequestRide(int clientId, int destinationLocation);
}