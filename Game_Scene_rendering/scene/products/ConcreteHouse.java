package scene.products;

import core.Position;
import core.Size;

public class ConcreteHouse implements IHouse {

    private String name;
    private Position position;
    private Size size;
    private final String material = "Concrete";

    public ConcreteHouse(String name, Position position, Size size) {
        this.name = name;
        this.position = position;
        this.size = size;
    }

    @Override
    public void render() {
        System.out.println("🏠 Building: " + name + " [" + material + "] at " + position + " with size " + size);
    }

    @Override
    public void moveTo(Position newPosition) {
        System.out.println("Error: Cannot move a house.");
    }

    @Override
    public Position getPosition() { return position; }
    @Override
    public Size getSize() { return size; }
    @Override
    public String getName() { return name; }
    @Override
    public String getMaterial() { return material; }
}