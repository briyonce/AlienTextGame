import java.util.Scanner;

public class Room {
  public static void main(String[] args) {}

  private Inventory loot = new Inventory();
  private String name = "room";

  // Environmental conditions for room descriptions
  private boolean onShip = true;
  private boolean outside = false;
  private boolean powerOut = true;


  public Room () {}

  public Room (String n) {
    this.name = n;
    if (n.toLowerCase().equals("med bay")) {
      loot.acquire("map");
      for (int i = 0; i < 3; ++i) {
        loot.acquire("stimpak");
      }
      loot.acquire("flashlight");
    }
  }

  void describe(boolean flashlight) {
    if (onShip) {
      System.out.print("We're on the ship.");
      if (powerOut && !flashlight) {
        System.out.println(" Power's out. It's pretty dark in here. May need a flashlight");
      } else if (powerOut && flashlight) {
        if (this.name.toLowerCase().equals("med bay")) {
          System.out.println("Lots of equipment thrown about. Looks like the ship went through a rough patch");
          System.out.println("The walls are a sterile white. Looks like a regular infirmary for the most part.");
        }
      }
    }
  }

  String getName() {
    return this.name;
  }

  boolean display() {
    return loot.inventoryNumberPrint();
  }

  boolean lootRoom(Human h) {
    Scanner reader = new Scanner(System.in);
    System.out.println("Let's see what this room has to offer...");
    while (!this.loot.isEmpty()) {
      this.display();
      System.out.println("What do you want? One at a time. ");
      int choice = reader.nextInt();
      reader.nextLine();
      boolean valid_input = false;
      if (choice < loot.numItems() + 1 ) {
        valid_input = true;
      }
      while (!valid_input) {
        System.out.println("Sorry, that's not one of the options. Please select from what's available.");
        this.display();
        choice = reader.nextInt();
        reader.nextLine();
        if ((choice < loot.numItems() + 2) && (choice >= 0)) {
          valid_input = true;
        }
      }
      if (choice == 0) {
        reader.close();
        return true;
      } else if (choice == 1) {
        transfer(loot, h);
      } else {
        transfer(loot.retrieve(choice - 2), h);
      }
    }
    System.out.println("You've stripped the room of all its worth.");
    reader.close();
    return true;
  }

  // The character loots everything in the room
  void transfer (Inventory i, Human h) {
    int counter = 0;
    while (i.numItems() > 0) {
      Item item = i.retrieve(counter);
      String name = item.getName();
      int quantity = item.getQuantity();
      i.dropAll(item.getName());
      // Item newItem = new Item(name, quantity); // to be used later upon optimization
      for (int index = 0; index < quantity; ++index) {
        boolean success = h.acquire(name);
        if (!success) {
          i.acquire(name);
          break;
        }
      }
    }
  }

  // Move an item from this room's inventory
  // to another character's inventory
  void transfer (Item i, Human h) {
    boolean hasItem = loot.drop(i.getName());
    if (hasItem) {
      System.out.println(h.getName() + "picked up the " + i.getName() + ".");
      h.acquire(i.getName());
    } else {
      System.out.println("That item isn't in this room.");
    }
  }
}
