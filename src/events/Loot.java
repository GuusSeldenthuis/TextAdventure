package events;

import game.RoomAction;
import game.RoomEvent;

public class Loot implements RoomEvent
{
    @Override
    public String describe()
    {
        return "Je hebt een kist gevonden!";
    }

    @Override
    public void reaction(RoomAction action)
    {
        switch (action)
        {
            case Slaan:
                System.out.println("Auw! Dat doet pijn.");
                return;
            case Ren:
                System.out.println("Je vlucht weg van de kist.");
                return;
            case Open:
                System.out.println("Je opent de kist, is zit geld in!");
                return;
            case Negeer:
                System.out.println("Je negeert de kist.");
                return;
        }
    }
}
