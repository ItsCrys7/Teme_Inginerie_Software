package characters;

import core.Position;
import core.Size;

public class CharacterFactory {

    public static ICharacter createHuman(String name) {
        return new CharacterBuilder()
                .withName(name)
                .withInitialPosition(new Position(0, 0))
                .withAge(25)
                .withSize(new Size(5, 5))
                .withSpeed(10)
                .withAttackPower(1)
                .build();
    }
}