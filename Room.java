import java.util.Scanner;

public class Room {
  public static void main(String[] args) {}

  private Inventory loot = new Inventory();
  private String name = "room";

  // Environmental conditions for room descriptions
  private boolean onShip = true;
  private boolean outside = false;
  private boolean powerOut = true;

  // Different Rooms in Mind: Storage closet, Cafeteria, Med Bay, Quarters,
  // captain's quarters, living room
  public Room () {}

  public Room (String n) {
    this.name = n;
    if (n.toLowerCase().equals("med bay")) {
      initializeMedBay();
    } else if (n.toLowerCase().equals("living quarters")) {
      initializeLivingQuarters();
    }
  }

  void initializeLivingQuarters() {
  }

  void initializeMedBay() {
    loot.acquire(loot.map, true);
    loot.acquire(loot.flashlight, true);
    for (int i = 0; i < 3; ++i) {
      loot.acquire(loot.stimpak, true);
    }

  }
  void tierDisplay() {
    loot.levelPrint();
  }

  void describe(boolean flashlight) {
    if (onShip) {
      System.out.print("We're on the ship.");
      if (powerOut && !flashlight) {
        System.out.println(" Power's out. It's pretty dark in here. May need a flashlight. \n");
      } else if (powerOut && flashlight) {
        if (this.name.toLowerCase().equals("med bay")) {
          System.out.println(" Lots of equipment thrown about. Looks like the ship went through a rough patch.");
          System.out.println("The walls are a sterile white. Looks like a regular infirmary for the most part.\n");
        }
      }
    }
  }

  String getName() {
    return this.name;
  }

  // Show all of the items currently available for looting in the room
  int display() {
    return loot.inventoryNumberPrint();
  }

  boolean lootRoom(Human h, Scanner reader) {
    System.out.println("Let's see what this room has to offer...\n");
    int numItems = 0;
    while (!this.loot.isEmpty()) {
      numItems = this.display();
      System.out.println("What do you want? One at a time. ");
      int choice = reader.nextInt();
      reader.nextLine();
      boolean validInput = false;
      if (choice < numItems + 2) {
        validInput = true;
      }
      while (!validInput) {
        System.out.println("Sorry, that's not one of the options. Please select from what's available.\n");
        this.display();
        choice = reader.nextInt();
        reader.nextLine();
        if ((choice < numItems + 2) && (choice >= 0)) {
          validInput = true;
        }
      }
      if (choice == 0) {
        return true;
      } else if (choice == 1) {
        transfer(loot, h);
      } else {
        transfer(loot.retrieve(choice - 2), h);
      }
    }
    System.out.println("You've stripped the room of all its worth.\n");
    return true;
  }

  // The character loots everything in the room
  void transfer (Inventory i, Human h) {
    int counter = 0;
    while (i.numItems() > 0) {
      Item item = i.retrieve(counter);
      int quantity = item.getQuantity();
      i.dropAll(item, true);
      // Item newItem = new Item(name, quantity); // to be used later upon optimization
      for (int index = 0; index < quantity; ++index) {
        boolean success = h.acquire(item);
        if (!success) {
          i.acquire(item);
          break;
        }
      }
    }
  }

  // Move an item from this room's inventory
  // to another character's inventory
  void transfer (Item i, Human h) {
    boolean hasItem = loot.drop(false, i, false);
    if (hasItem) {
      h.acquire(i);
    } else {
      System.out.println("That item isn't in this room.\n");
    }
  }
}
