package game;

import events.Enemy;
import events.Loot;

import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    public static Room[][] rooms = new Room[2][2];
    public static int currentRow = 0;    // First index - up/down
    public static int currentCol = 0;    // Second index - left/right

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

        // Layout the rooms in a more intuitive way:
        // [0,0] = Entrance    [0,1] = Treasure Room
        // [1,0] = Cellar      [1,1] = Kitchen
        rooms[0][0] = room1;
        rooms[0][1] = room2;
        rooms[1][0] = room3;
        rooms[1][1] = room4;

        return rooms;
    }

    /**
     * Attempts to move the player in the specified direction.
     * @param direction The direction to move (up, down, left, right)
     * @return true if movement was successful, false otherwise
     */
    private static boolean movePlayer(String direction) {
        switch (direction) {
            case "up":
                if (currentRow > 0) {
                    currentRow--;
                    return true;
                }
                break;
            case "down":
                if (currentRow < rooms.length - 1) {
                    currentRow++;
                    return true;
                }
                break;
            case "left":
                if (currentCol > 0) {
                    currentCol--;
                    return true;
                }
                break;
            case "right":
                if (currentCol < rooms[0].length - 1) {
                    currentCol++;
                    return true;
                }
                break;
        }
        return false;
    }

    public static void main(String[] args)
    {
        rooms = setupRooms();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            Room currentRoom = rooms[currentRow][currentCol];
            System.out.println("\n=== " + currentRoom.name + " ===");
            System.out.println(currentRoom.description);
            System.out.println();
            
            // Show available directions
            System.out.println("Available exits:");
            if (currentRow > 0) System.out.println("- up");
            if (currentRow < rooms.length - 1) System.out.println("- down");
            if (currentCol > 0) System.out.println("- left");
            if (currentCol < rooms[0].length - 1) System.out.println("- right");
            System.out.println();

            currentRoom.runEvents();
            System.out.println();
            System.out.println("Where do you want to go?");
            
            String direction = scanner.next().toLowerCase();
            if (!movePlayer(direction)) {
                System.out.println("You can't go that way!");
            }
        }
    }
}
