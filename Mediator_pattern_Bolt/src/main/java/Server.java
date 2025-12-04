import java.util.*;
import java.util.stream.Collectors;

public class Server implements IServer {
    private Map<Integer, IUser> users = new HashMap<>();
    private Map<Integer, List<Message>> offlineMessages = new HashMap<>();
    
    public void RegisterUser(IUser user) {
        if (users.containsKey(user.getId())) return;
        users.put(user.getId(), user);
        deliverOfflineMessages(user.getId());
    }

    public void Unregister(IUser user) {
        if (!users.containsKey(user.getId())) return;
        users.remove(user.getId());
    }

    public void SendMessage(int userIdSender, int userIdReceiver, String message) {
        IUser receiver = users.get(userIdReceiver);
        IUser sender = users.get(userIdSender);
        String senderName = sender != null ? sender.getName() : "Unknown";

        if (receiver == null || receiver.getIsBusy()) {
            System.out.println("[Server] Message put in queue for " + userIdReceiver + " (Offline/Busy)");
            queueOfflineMessage(userIdReceiver, new Message(senderName, message));
            return;
        }
        receiver.ReceiveMessage(senderName, message);
    }

    public void RequestRide(int clientId, int destinationLocation) {
        IUser client = users.get(clientId);
        if (client == null) return;

        int clientLocation = client.getLocation();
        int tripDistance = Math.abs(destinationLocation - clientLocation);
        
        System.out.println("\n>>> RIDE REQUEST for " + client.getName());
        System.out.println("Route: " + clientLocation + " -> " + destinationLocation + " (Distance: " + tripDistance + "km)");

        List<Vehicle> availableVehicles = new ArrayList<>();

        for (IUser user : users.values()) {
            if (user instanceof Vehicle) {
                Vehicle v = (Vehicle) user;
                
                if (v.getIsBusy()) continue; 
                if (!v.canFulfillTrip(tripDistance)) continue; 

                availableVehicles.add(v);
            }
        }

        availableVehicles.sort(Comparator.comparingInt(v -> Math.abs(v.getLocation() - clientLocation)));

        List<Vehicle> topOptions = availableVehicles.stream().limit(3).collect(Collectors.toList());
        if (topOptions.isEmpty()) {
            client.ReceiveMessage("BoltApp", "No vehicles available.");
        } else {
            StringBuilder sb = new StringBuilder("Options found:\n");
            for (Vehicle v : topOptions) {
                int distToClient = Math.abs(v.getLocation() - clientLocation);
                sb.append(" - " + v.getName() + " (" + distToClient + "km distance). " + v.getDetails() + "\n");
            }
            client.ReceiveMessage("BoltApp", sb.toString());
        }
    }

    private void queueOfflineMessage(int userIdReceiver, Message msg) {
        offlineMessages.computeIfAbsent(userIdReceiver, k -> new ArrayList<>()).add(msg);
    }

    private void deliverOfflineMessages(int userId) {
        List<Message> list = offlineMessages.get(userId);
        if (list != null && !list.isEmpty()) {
            IUser user = users.get(userId);
            System.out.println("[Server] Delivering offline messages to " + user.getName());
            for (Message m : list) {
                user.ReceiveMessage(m.senderName, "[Offline] " + m.body);
            }
            offlineMessages.remove(userId);
        }
    }
    
    private static class Message {
        String senderName, body;
        Message(String s, String b) { senderName = s; body = b; }
    }
}