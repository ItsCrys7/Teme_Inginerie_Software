package scene;

import core.Position;
import core.Size;
import scene.products.ConcreteHouse;
import scene.products.IHouse;

public class EuropeanSceneFactory implements ISceneFactory {
    @Override
    public IHouse createHouse(Position pos, String name) {
        return new ConcreteHouse(name, pos, new Size(30, 20));
    }
}