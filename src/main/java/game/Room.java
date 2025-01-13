package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Room
{
    public String name;

    private ArrayList<RoomEvent> roomEvents;

    public String description;
    public boolean visited;

    public Room(String name, ArrayList<RoomEvent> roomEvents, String description)
    {
        this.name = name;
        this.roomEvents = roomEvents;
        this.description = description;
    }

    public void runEvents()
    {
        for (RoomEvent roomEvent : roomEvents)
        {

            System.out.println("\t" + roomEvent.describe());
            System.out.println();
            System.out.println("What do you want to do? (Attack, Run, Open, Ignore)");

            Scanner scanner = new Scanner(System.in);
            String givenAction = scanner.next();

            switch (givenAction)
            {
                case "Attack":
                    roomEvent.reaction(RoomAction.Attack);
                    break;
                case "Run":
                    roomEvent.reaction(RoomAction.Run);
                    break;
                case "Open":
                    roomEvent.reaction(RoomAction.Open);
                    break;
                case "Ignore":
                    roomEvent.reaction(RoomAction.Ignore);
                    break;
                default:
                    System.out.println("Unknown action");
            }
        }
    }
}
