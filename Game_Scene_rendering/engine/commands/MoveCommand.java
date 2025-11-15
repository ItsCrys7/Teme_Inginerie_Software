package engine.commands;

import core.IGameObject;
import core.Position;
import engine.GameEngine;

public class MoveCommand implements ICommand {

    private IGameObject target;
    private Position newPosition;

    public MoveCommand(IGameObject target, Position newPosition) {
        this.target = target;
        this.newPosition = newPosition;
    }

    @Override
    public void execute() {
        GameEngine engine = GameEngine.getInstance();
        boolean isValid = engine.isPositionValid(newPosition, target.getSize());

        if (isValid) {
            target.moveTo(newPosition);
        } else {
            System.out.println("VALIDATION FAILED: " + target.getName() + " cannot move to " + newPosition + ". Position is occupied or out of bounds.");
        }
    }
}