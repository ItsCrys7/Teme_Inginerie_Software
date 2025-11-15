package scene;

import core.Position;
import scene.products.IHouse;

public interface ISceneFactory {
    IHouse createHouse(Position pos, String name);
}