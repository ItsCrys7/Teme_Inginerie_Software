package scene;

import core.Position;
import core.Size;
import scene.products.BambooHouse;
import scene.products.IHouse;

public class AsianSceneFactory implements ISceneFactory {
    @Override
    public IHouse createHouse(Position pos, String name) {
        return new BambooHouse(name, pos, new Size(20, 20));
    }
}