package decorators;

import characters.ICharacter;
import core.Position;
import core.Size;
import java.util.List;

public abstract class CharacterDecorator implements ICharacter {

    protected ICharacter decoratedCharacter;

    public CharacterDecorator(ICharacter decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }

    @Override
    public void render() {
        decoratedCharacter.render();
    }

    @Override
    public void moveTo(Position newPosition) {
        decoratedCharacter.moveTo(newPosition);
    }

    @Override
    public Position getPosition() {
        return decoratedCharacter.getPosition();
    }

    @Override
    public Size getSize() {
        return decoratedCharacter.getSize();
    }

    @Override
    public String getName() {
        return decoratedCharacter.getName();
    }

    @Override
    public int getSpeed() {
        return decoratedCharacter.getSpeed();
    }

    @Override
    public int getAttackPower() {
        return decoratedCharacter.getAttackPower();
    }

    @Override
    public List<String> getAbilities() {
        return decoratedCharacter.getAbilities();
    }
}