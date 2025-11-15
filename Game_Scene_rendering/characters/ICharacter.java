// Character.java
// Implementează IGameObject, este un obiect mobil.
public class Character implements IGameObject {
    
    private String name;
    private Position position;
    private Size size;
    // Alte atribute (age, abilities) vor fi adăugate cu Builder/Decorator

    public Character(String name, Position position, Size size) {
        this.name = name;
        this.position = position;
        this.size = size;
    }

    @Override
    public void render() {
        System.out.println(
            "👤 Character: " + this.name + 
            " at " + this.position + 
            " with size " + this.size
        );
    }

    @Override
    public void moveTo(Position newPosition) {
        // Aceasta este mișcarea efectivă, nevalidată.
        // Validarea se face înainte de a apela .moveTo()
        System.out.println("-> " + this.name + " moving from " + this.position + " to " + newPosition);
        this.position = newPosition;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }
}