abstract class Vehicle extends User {
    public Vehicle(int id, String name, IServer server, int location) {
        super(id, name, server, location);
    }
    public abstract boolean canFulfillTrip(int tripDistance);
    public abstract String getDetails();
}

class Car extends Vehicle {
    public Car(int id, String name, IServer server, int location) {
        super(id, name, server, location);
    }
    @Override
    public boolean canFulfillTrip(int tripDistance) {
        return true;
    }
    @Override
    public String getDetails() { return "Type: Car"; }
}

class Scooter extends Vehicle {
    private int batteryRangeKm;

    public Scooter(int id, String name, IServer server, int location, int batteryRangeKm) {
        super(id, name, server, location);
        this.batteryRangeKm = batteryRangeKm;
    }

    @Override
    public boolean canFulfillTrip(int tripDistance) {
        return batteryRangeKm >= (tripDistance * 0.5);
    }
    @Override
    public String getDetails() { return "Type: Scooter (Range: " + batteryRangeKm + "km)"; }
}

class VehicleFactory {
    public static Vehicle createCar(int id, String name, IServer server, int location) {
        return new Car(id, name, server, location);
    }

    public static Vehicle createScooter(int id, String name, IServer server, int location, int batteryRange) {
        return new Scooter(id, name, server, location, batteryRange);
    }
}