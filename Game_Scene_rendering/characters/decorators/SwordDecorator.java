package decorators;

import java.util.List;

import characters.ICharacter;

public class SwordDecorator extends CharacterDecorator {

    public SwordDecorator(ICharacter decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public int getAttackPower() {
        return super.getAttackPower() + 10;
    }

    @Override
    public List<String> getAbilities() {
        List<String> abilities = super.getAbilities();
        abilities.add("Sword");
        return abilities;
    }
}