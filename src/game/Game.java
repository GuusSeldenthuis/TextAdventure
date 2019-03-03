package game;

import events.Enemy;
import events.Loot;

import java.util.ArrayList;
import java.util.Scanner;

public class Game
{

    public static Room[][] rooms = new Room[2][2];

    public static int locationX = 0;

    public static int locationY = 0;

    public static Room[][] setupRooms()
    {
        ArrayList<RoomEvent> kamer1Events = new ArrayList<>();
        kamer1Events.add(new Enemy());
        Room kamer1 = new Room("De ingang", kamer1Events, "Het is hier donker.");

        ArrayList<RoomEvent> kamer2Events = new ArrayList<>();
        kamer2Events.add(new Loot());
        Room kamer2 = new Room("De schatkamer", kamer2Events, "Het is hier heel mooi.");

        ArrayList<RoomEvent> kamer3Events = new ArrayList<>();
        kamer3Events.add(new Enemy());
        kamer3Events.add(new Loot());
        Room kamer3 = new Room("De kelder", kamer3Events, "Alles zit onder het bloed.");

        ArrayList<RoomEvent> kamer4Events = new ArrayList<>();
        kamer4Events.add(new Loot());
        Room kamer4 = new Room("De keuken", kamer4Events, "Wow, zo mooi.");

        Room[][] rooms = new Room[2][2];

        rooms[0][0] = kamer1;
        rooms[0][1] = kamer2;
        rooms[1][0] = kamer3;
        rooms[1][1] = kamer4;

        return rooms;
    }

    public static void main(String[] args)
    {
        rooms = setupRooms();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Je bent in de kamer: " + rooms[locationX][locationY].naam);
            System.out.println(rooms[locationX][locationY].description);
            System.out.println();
            rooms[locationX][locationY].runEvents();
            System.out.println();
            System.out.println("Waar wil je heen?");
            String richting = scanner.next();
            switch (richting)
            {
                case "links":
                    if (locationX != 0)
                    {
                        locationX--;
                    }
                    continue;
                case "rechts":
                    if (locationX != 1)
                    {
                        locationX++;
                    }
                    continue;
                case "boven":
                    if (locationY != 1)
                    {
                        locationY++;
                    }
                    continue;
                case "onder":
                    if (locationY != 0)
                    {
                        locationY--;
                    }
                    continue;
                default:
                    System.out.println("Opties: links, rechts, boven, onder");
            }
        }
    }
}
