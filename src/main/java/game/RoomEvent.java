package game;

public interface RoomEvent
{
    String describe();
    void reaction(RoomAction action);
}
