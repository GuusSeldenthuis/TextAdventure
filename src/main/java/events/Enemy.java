package events;

import game.RoomAction;
import game.RoomEvent;

public class Enemy implements RoomEvent
{
    @Override
    public String describe()
    {
        return "There is an enemy!";
    }

    @Override
    public void reaction(RoomAction action)
    {
        switch (action)
        {
            case Attack:
                System.out.println("You attack the enemy.");
                return;
            case Run:
                System.out.println("You run away.");
                return;
            case Open:
                System.out.println("You try to open the enemy?!?");
                return;
            case Ignore:
                System.out.println("You ignore the enemy.");
                return;
        }
    }
}
