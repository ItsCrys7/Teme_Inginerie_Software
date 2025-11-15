package characters;

import core.IGameObject;
import java.util.List;

public interface ICharacter extends IGameObject {
    int getSpeed();
    int getAttackPower();
    List<String> getAbilities();
}