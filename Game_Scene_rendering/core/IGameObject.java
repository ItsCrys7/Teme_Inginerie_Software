package core;
public interface IGameObject {

    void render();

    void moveTo(Position newPosition);

    Position getPosition();
    Size getSize();
    String getName();
}