public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        User client = new User(1, "Client_Ion", server, 40); 
        server.RegisterUser(client);

        server.RegisterUser(VehicleFactory.createCar(10, "Car_A", server, 10));
        server.RegisterUser(VehicleFactory.createScooter(11, "Scooter_B", server, 25, 5));
        server.RegisterUser(VehicleFactory.createCar(12, "Car_C", server, 50));
        
        Vehicle trotD = VehicleFactory.createScooter(13, "Scooter_D", server, 65, 50);
        server.RegisterUser(trotD);
        
        server.RegisterUser(VehicleFactory.createCar(14, "Car_E", server, 95));

        System.out.println("--- BOLT SCENARIOS ---");
        server.RequestRide(client.getId(), 45);
        server.RequestRide(client.getId(), 90);

        System.out.println("\n--- Test Messages Offline ---");
        
        server.Unregister(trotD); 
        System.out.println("Scooter D has disconnected.");
        
        client.SendMessage(13, "Hello, come back online!");
        
        System.out.println("Scooter D is coming back online...");
        server.RegisterUser(trotD); 
    }
}