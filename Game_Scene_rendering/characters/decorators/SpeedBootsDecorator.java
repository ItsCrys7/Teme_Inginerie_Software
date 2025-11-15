package decorators;

import characters.ICharacter;

public class SpeedBootsDecorator extends CharacterDecorator {

    public SpeedBootsDecorator(ICharacter decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public int getSpeed() {
        return super.getSpeed() * 2;
    }
}