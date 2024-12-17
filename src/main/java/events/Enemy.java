package events;

import game.RoomAction;
import game.RoomEvent;

public class Enemy implements RoomEvent
{
    @Override
    public String describe()
    {
        return "Er is een vijand!";
    }

    @Override
    public void reaction(RoomAction action)
    {
        switch (action)
        {
            case Slaan:
                System.out.println("Je slaat de vijand.");
                return;
            case Ren:
                System.out.println("Je vlucht weg.");
                return;
            case Open:
                System.out.println("Je opent de vijand?!?");
                return;
            case Negeer:
                System.out.println("Je negeert de vijand.");
                return;
        }
    }
}
