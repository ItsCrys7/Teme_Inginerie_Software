package characters;

import core.Position;
import core.Size;
import java.util.ArrayList;
import java.util.List;

public class BaseCharacter implements ICharacter {

    private String name;
    private Position position;
    private Size size;
    private int age;
    private int speed;
    private int attackPower;

    public BaseCharacter(String name, Position position, Size size, int age, int speed, int attackPower) {
        this.name = name;
        this.position = position;
        this.size = size;
        this.age = age;
        this.speed = speed;
        this.attackPower = attackPower;
    }

    @Override
    public void render() {
        System.out.println(
            "👤 Character: " + name + 
            " at " + position + 
            " with size " + size + 
            " (Age: " + age + 
            ", Speed: " + getSpeed() + 
            ", Atk: " + getAttackPower() + 
            ", Abilities: " + getAbilities() + ")"
        );
    }

    @Override
    public void moveTo(Position newPosition) {
        System.out.println("-> " + name + " moving from " + position + " to " + newPosition);
        this.position = newPosition;
    }

    @Override
    public Position getPosition() { return position; }
    @Override
    public Size getSize() { return size; }
    @Override
    public String getName() { return name; }

    @Override
    public int getSpeed() { return speed; }
    @Override
    public int getAttackPower() { return attackPower; }

    @Override
    public List<String> getAbilities() {
        return new ArrayList<>(List.of("Walk", "Talk"));
    }
}