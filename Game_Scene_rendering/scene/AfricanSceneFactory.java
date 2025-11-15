package scene;

import core.Position;
import core.Size;
import scene.products.DirtHouse;
import scene.products.IHouse;

public class AfricanSceneFactory implements ISceneFactory {
    @Override
    public IHouse createHouse(Position pos, String name) {
        return new DirtHouse(name, pos, new Size(15, 15));
    }
}