package characters;

import core.Position;
import core.Size;

public class CharacterBuilder {

    private String name = "Unnamed";
    private Position position = new Position(0, 0);
    private Size size = new Size(5, 5);
    private int age = 20;
    private int speed = 10;
    private int attackPower = 1;

    public CharacterBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder withInitialPosition(Position position) {
        this.position = position;
        return this;
    }

    public CharacterBuilder withSize(Size size) {
        this.size = size;
        return this;
    }

    public CharacterBuilder withAge(int age) {
        this.age = age;
        return this;
    }
    
    public CharacterBuilder withSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public CharacterBuilder withAttackPower(int attackPower) {
        this.attackPower = attackPower;
        return this;
    }

    public ICharacter build() {
        return new BaseCharacter(name, position, size, age, speed, attackPower);
    }
}