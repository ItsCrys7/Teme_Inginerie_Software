public class main {
    
}
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Initializing Game Objects ---");

        Character human1 = new Character("Human 1", new Position(10, 10), new Size(5, 5));
        Character human2 = new Character("Human 2", new Position(20, 10), new Size(5, 5));
        House house1 = new House("House 1", new Position(50, 50), new Size(20, 20), "Concrete");
        
        Group legion1 = new Group("Legion Alpha");
        legion1.add(human1);
        legion1.add(human2);

        List<IGameObject> allGameObjects = new ArrayList<>();
        allGameObjects.add(house1);
        allGameObjects.add(legion1); 
        System.out.println("\n--- ### Rendering Full Scene ### ---");
        for (IGameObject obj : allGameObjects) {
            obj.render();
        }

        System.out.println("\n--- ### Moving the Legion ### ---");
        legion1.moveTo(new Position(100, 100));

        System.out.println("\n--- ### Rendering Scene After Move ### ---");
        for (IGameObject obj : allGameObjects) {
            obj.render();
        }
    }
}