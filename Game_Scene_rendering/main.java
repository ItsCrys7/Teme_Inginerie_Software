import core.*;
import engine.GameEngine;
import engine.commands.ICommand;
import engine.commands.MoveCommand;
import scene.*;
import scene.products.IHouse;
import characters.*;
import decorators.*;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("--- 1. Initializing Game Engine (Singleton) ---");
        ISceneFactory factory = new EuropeanSceneFactory();
        GameEngine engine = GameEngine.getInstance();
        engine.initialize(new Size(1000, 1000), factory);

        System.out.println("\n--- 2. Creating Scene Objects (Abstract Factory) ---");
        IHouse house1 = engine.getSceneFactory().createHouse(new Position(50, 50), "House 1");
        IHouse house2 = engine.getSceneFactory().createHouse(new Position(200, 150), "House 2");
        engine.addGameObject(house1);
        engine.addGameObject(house2);

        System.out.println("\n--- 3. Creating Characters (Builder & Factory) ---");
        ICharacter human1 = CharacterFactory.createHuman("Gigel");
        ICharacter human2 = CharacterFactory.createHuman("Ion");
        ICharacter human3 = new CharacterBuilder().withName("Vasile").build();

        System.out.println("\n--- 4. Decorating Characters (Decorator) ---");
        ICharacter soldier1 = new SwordDecorator(human1);
        ICharacter farmer1 = new PitchforkDecorator(human2);
        ICharacter fastSoldier = new SpeedBootsDecorator(new SwordDecorator(human3));

        System.out.println("\n--- 5. Grouping Characters (Composite) ---");
        Group legion = new Group("Legion Alpha");
        legion.add(soldier1);
        legion.add(fastSoldier);
        
        engine.addGameObject(legion);
        engine.addGameObject(farmer1);

        System.out.println("\n--- 6. Initial Scene Render ---");
        engine.renderScene();

        System.out.println("\n--- 7. Executing Commands (Command) ---");
        System.out.println("\n>> Attempting to move Farmer (Ion) to a valid position (10, 10)...");
        ICommand move1 = new MoveCommand(farmer1, new Position(10, 10));
        move1.execute();

        System.out.println("\n>> Attempting to move Legion to a valid position (300, 300)...");
        ICommand move2 = new MoveCommand(legion, new Position(300, 300));
        move2.execute();

        System.out.println("\n>> Attempting to move Farmer (Ion) ONTO House 1 (50, 50) - SHOULD FAIL");
        ICommand move3_fail = new MoveCommand(farmer1, new Position(50, 50));
        move3_fail.execute();

        System.out.println("\n>> Attempting to move Legion so it overlaps House 2 (190, 140) - SHOULD FAIL");
        ICommand move4_fail = new MoveCommand(legion, new Position(190, 140));
        move4_fail.execute();

        System.out.println("\n--- 8. Final Scene Render ---");
        engine.renderScene();
    }
}