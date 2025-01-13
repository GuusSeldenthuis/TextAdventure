package events;

import game.RoomAction;
import game.RoomEvent;

public class Loot implements RoomEvent
{
    @Override
    public String describe()
    {
        return "You found a chest!";
    }

    @Override
    public void reaction(RoomAction action)
    {
        switch (action)
        {
            case Attack:
                System.out.println("Ouch! That hurts.");
                return;
            case Run:
                System.out.println("You run away from the chest.");
                return;
            case Open:
                System.out.println("You open the chest, there's money inside!");
                return;
            case Ignore:
                System.out.println("You ignore the chest.");
                return;
        }
    }
}
