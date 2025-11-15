package engine;

import core.IGameObject;
import core.Position;
import core.Size;
import scene.ISceneFactory;
import scene.products.IHouse;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private static GameEngine instance;

    private List<IGameObject> allGameObjects;
    private List<IHouse> staticObstacles;
    private Size boardSize;
    private ISceneFactory sceneFactory;

    private GameEngine() {
        allGameObjects = new ArrayList<>();
        staticObstacles = new ArrayList<>();
    }

    public static synchronized GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void initialize(Size boardSize, ISceneFactory factory) {
        this.boardSize = boardSize;
        this.sceneFactory = factory;
        System.out.println("Game Engine Initialized. Board: " + boardSize);
    }

    public void addGameObject(IGameObject object) {
        this.allGameObjects.add(object);
        if (object instanceof IHouse) {
            this.staticObstacles.add((IHouse) object);
        }
    }

    public void renderScene() {
        System.out.println("\n--- RENDERING FULL SCENE ---");
        for (IGameObject obj : allGameObjects) {
            obj.render();
        }
        System.out.println("--- END OF SCENE ---");
    }

    public boolean isPositionValid(Position newPos, Size objectSize) {
        if (newPos.x < 0 || newPos.y < 0 ||
            newPos.x + objectSize.width > boardSize.width ||
            newPos.y + objectSize.height > boardSize.height) {
            return false;
        }

        for (IHouse house : staticObstacles) {
            Position housePos = house.getPosition();
            Size houseSize = house.getSize();

            if (newPos.x < housePos.x + houseSize.width &&
                newPos.x + objectSize.width > housePos.x &&
                newPos.y < housePos.y + houseSize.height &&
                newPos.y + objectSize.height > housePos.y) {
                return false;
            }
        }
        
        return true;
    }

    public ISceneFactory getSceneFactory() {
        return sceneFactory;
    }
}