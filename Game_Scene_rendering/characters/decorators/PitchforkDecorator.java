package decorators;

import characters.ICharacter;
import java.util.List;

public class PitchforkDecorator extends CharacterDecorator {

    public PitchforkDecorator(ICharacter decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public int getAttackPower() {
        return super.getAttackPower() + 2;
    }

    @Override
    public List<String> getAbilities() {
        List<String> abilities = super.getAbilities();
        abilities.add("Pitchfork");
        return abilities;
    }
}