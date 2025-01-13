package game;

import events.Enemy;
import events.Loot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    public static Room[][] rooms = new Room[2][2];
    public static int currentRow = 0;    // First index - up/down
    public static int currentCol = 0;    // Second index - left/right

    private static List<String> loadFile(String filename) {
        try {
            return Files.readAllLines(Paths.get("src/main/resources/" + filename));
        } catch (IOException e) {
            System.err.println("Error loading " + filename + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static String getRandomElement(List<String> list) {
        if (list.isEmpty()) return "Empty Room";
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static ArrayList<RoomEvent> generateRandomEvents() {
        ArrayList<RoomEvent> events = new ArrayList<>();
        Random random = new Random();
        
        // 70% chance for an enemy
        if (random.nextDouble() < 0.7) {
            events.add(new Enemy());
        }
        
        // 60% chance for loot
        if (random.nextDouble() < 0.6) {
            events.add(new Loot());
        }
        
        return events;
    }

    public static Room[][] setupRooms() {
        List<String> names = loadFile("room_names.txt");
        List<String> descriptions = loadFile("room_descriptions.txt");
        Room[][] rooms = new Room[2][2];

        // Generate random rooms
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String name = getRandomElement(names);
                String description = getRandomElement(descriptions);
                ArrayList<RoomEvent> events = generateRandomEvents();
                rooms[i][j] = new Room(name, events, description);
            }
        }

        return rooms;
    }

    private static void displayMap() {
        System.out.println("\n=== MAP ===");
        for (int i = 0; i < rooms.length; i++) {
            // Top border of rooms
            for (int j = 0; j < rooms[i].length; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            
            // Room contents
            for (int j = 0; j < rooms[i].length; j++) {
                System.out.print("|");
                if (i == currentRow && j == currentCol) {
                    System.out.print(" * "); // Current position
                } else if (rooms[i][j].visited) {
                    System.out.print(" · "); // Visited room
                } else {
                    System.out.print("   "); // Undiscovered room
                }
            }
            System.out.println("|");
        }
        
        // Bottom border
        for (int j = 0; j < rooms[0].length; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.println("Legend: * = You are here, · = Visited room");
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
            currentRoom.visited = true;
            displayMap();
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
