import java.util.Scanner;

public class Room {
  public static void main(String[] args) {}

  Inventory loot = new Inventory();
  String name = "room";
  public Room () {

  }

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

  String getName() {
    return this.name;
  }

  void display() {
    loot.inventoryNumberPrint();
  }

  boolean lootRoom(Human h) {
    Scanner reader = new Scanner(System.in);
    System.out.println("Let's see what this room has to offer...");
    while (!this.loot.isEmpty()) {
      this.display();
      System.out.println("What do you want? One at a time. ");
      int choice = reader.nextInt();
      boolean valid_input = false;
      if (choice < loot.numItems() + 1 ) {
        valid_input = true;
      }
      while (!valid_input) {
        System.out.println("Sorry, that's not one of the options. Please select from what's available.");
        this.display();
        choice = reader.nextInt();
        if (choice < loot.numItems() + 1) {
          valid_input = true;
        }
      }
      if (choice == 0) {
        reader.close();
        return true;
      }
      transfer(loot.retrieve(choice - 1), h);
    }
    reader.close();
    return true;
  }

  // Move an item from this character's inventory
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
