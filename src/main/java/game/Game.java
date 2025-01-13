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
        ArrayList<RoomEvent> room1Events = new ArrayList<>();
        room1Events.add(new Enemy());
        Room room1 = new Room("The Entrance", room1Events, "It's dark in here.");

        ArrayList<RoomEvent> room2Events = new ArrayList<>();
        room2Events.add(new Loot());
        Room room2 = new Room("The Treasure Room", room2Events, "It's very beautiful here.");

        ArrayList<RoomEvent> room3Events = new ArrayList<>();
        room3Events.add(new Enemy());
        room3Events.add(new Loot());
        Room room3 = new Room("The Cellar", room3Events, "Everything is covered in blood.");

        ArrayList<RoomEvent> room4Events = new ArrayList<>();
        room4Events.add(new Loot());
        Room room4 = new Room("The Kitchen", room4Events, "Wow, so beautiful.");

        Room[][] rooms = new Room[2][2];

        rooms[0][0] = room1;
        rooms[0][1] = room2;
        rooms[1][0] = room3;
        rooms[1][1] = room4;

        return rooms;
    }

    public static void main(String[] args)
    {
        rooms = setupRooms();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("You are in the room: " + rooms[locationX][locationY].name);
            System.out.println(rooms[locationX][locationY].description);
            System.out.println();
            rooms[locationX][locationY].runEvents();
            System.out.println();
            System.out.println("Where do you want to go?");
            String direction = scanner.next().toLowerCase();
            switch (direction)
            {
                case "left":
                    if (locationX > 0)
                    {
                        locationX--;
                    } else {
                        System.out.println("You can't go that way!");
                    }
                    break;
                case "right":
                    if (locationX < rooms.length - 1)
                    {
                        locationX++;
                    } else {
                        System.out.println("You can't go that way!");
                    }
                    break;
                case "up":
                    if (locationY < rooms[0].length - 1)
                    {
                        locationY++;
                    } else {
                        System.out.println("You can't go that way!");
                    }
                    break;
                case "down":
                    if (locationY > 0)
                    {
                        locationY--;
                    } else {
                        System.out.println("You can't go that way!");
                    }
                    break;
                default:
                    System.out.println("Options: left, right, up, down");
            }
        }
    }
}
