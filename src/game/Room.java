package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Room
{
    public String naam;

    private ArrayList<RoomEvent> roomEvents;

    public String description;

    public Room(String naam, ArrayList<RoomEvent> roomEvents, String description)
    {
        this.naam = naam;
        this.roomEvents = roomEvents;
        this.description = description;
    }

    public void runEvents()
    {
        for (RoomEvent roomEvent : roomEvents)
        {

            System.out.println("\t" + roomEvent.describe());
            System.out.println();
            System.out.println("Wat wil je doen? (Slaan, Ren, Open, Negeer)");

            Scanner scanner = new Scanner(System.in);
            String gegevenActie = scanner.next();

            switch (gegevenActie)
            {
                case "Slaan":
                    roomEvent.reaction(RoomAction.Slaan);
                    continue;
                case "Ren":
                    roomEvent.reaction(RoomAction.Ren);
                    continue;
                case "Open":
                    roomEvent.reaction(RoomAction.Open);
                    continue;
                case "Negeer":
                    roomEvent.reaction(RoomAction.Negeer);
                    continue;
                default:
                    System.out.println("Onbekende actie");
            }
        }
    }
}
