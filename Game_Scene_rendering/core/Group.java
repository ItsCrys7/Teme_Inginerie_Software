package core;

import java.util.ArrayList;
import java.util.List;

public class Group implements IGameObject {
    
    private String name;
    private List<IGameObject> children = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public void add(IGameObject child) {
        this.children.add(child);
    }

    public void remove(IGameObject child) {
        this.children.remove(child);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void render() {
        System.out.println("--- Start Rendering Group: '" + this.name + "' ---");
        for (IGameObject child : children) {
            System.out.print("  ");
            child.render();
        }
        System.out.println("--- End Rendering Group: '" + this.name + "' ---");
    }

    @Override
    public void moveTo(Position newPosition) {
        Position oldGroupPos = this.getPosition();
        int deltaX = newPosition.x - oldGroupPos.x;
        int deltaY = newPosition.y - oldGroupPos.y;

        System.out.println("-> Moving Group '" + this.name + "' by (" + deltaX + ", " + deltaY + ")");
        
        for (IGameObject child : children) {
            Position childOldPos = child.getPosition();
            Position childNewPos = new Position(childOldPos.x + deltaX, childOldPos.y + deltaY);
            child.moveTo(childNewPos);
        }
    }

    @Override
    public Position getPosition() {
        if (children.isEmpty()) {
            return new Position(0, 0);
        }
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (IGameObject child : children) {
            if (child.getPosition().x < minX) minX = child.getPosition().x;
            if (child.getPosition().y < minY) minY = child.getPosition().y;
        }
        return new Position(minX, minY);
    }

    @Override
    public Size getSize() {
        if (children.isEmpty()) {
            return new Size(0, 0);
        }

        Position groupPos = this.getPosition();
        
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (IGameObject child : children) {
            Position childPos = child.getPosition();
            Size childSize = child.getSize();
            if (childPos.x + childSize.width > maxX) maxX = childPos.x + childSize.width;
            if (childPos.y + childSize.height > maxY) maxY = childPos.y + childSize.height;
        }
        
        return new Size(maxX - groupPos.x, maxY - groupPos.y);
    }
}