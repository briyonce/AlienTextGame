// Just used for testing new mechanics and functions

import java.util.Scanner;
import java.util.ArrayList;

public class Test {

  static Human player = new Human(true);
  static Party p = new Party();

  public static void main(String[] args) {
    // test1();
    // System.out.println("\n\n");
    // test2();
    // System.out.println("\n\n");
    // test3();
    // System.out.println("\n\n");
    // test4();
    // test5();
    // test6();
    test7();
  }

  public static void test1 () {
    Room curRoom = new MedBay();
    curRoom.tierDisplay();
    curRoom.display();
    Human h = new Human();
    Party pa = new Party(h);
    pa.printParty();
    curRoom.lootRoom(h, new Scanner(System.in));
    pa.printParty();
  }

  static void test2() {
    Room curRoom = new MedBay();
    player.setName("bri");
    player.setGender("f");
    p.addMember(player);
    p.printParty();
    player.listInventory();
    roomExitSequence(curRoom, new Scanner(System.in));
    p.printParty();
  }

  static void test3 () {
    Xeno x = new Xeno();
    Xeno x2 = new Xeno();
    Xeno x3 = new Xeno("bribrad", 4, 5);
    p.addMember(x);
    p.addMember(x2);
    p.addMember(x3);
    p.printParty();
  }


  static void test4 () {
    ArrayList<ArrayList<Item>> i = new ArrayList<ArrayList<Item>>(5);
    System.out.println(i.size());
    i.ensureCapacity(5);
    System.out.println(i.size());
    i.add(new ArrayList<Item>());
    i.add(new ArrayList<Item>());
    i.add(new ArrayList<Item>());
    i.add(new ArrayList<Item>());
    i.add(new ArrayList<Item>());
    System.out.println(i.size());
    ArrayList<Item> x = i.get(0);
    System.out.println(x.size());
  }

  static void test5() {
    Scanner reader = new Scanner(System.in);
    player.setName("bri");
    player.setGender("f");
    Xeno x = new Xeno();
    player.showInventory();
    player.acquire(new Shootable("Gun"));
    player.acquire(new Shootable("Gun"));
    player.acquire(new Ranged("Knives"));
    player.showInventory();
    player.chooseWeapon(reader, x);
  }

  static void test6 () {
    Scanner reader = new Scanner(System.in);
    player.setName("bri");
    player.setGender("f");
    player.acquire(new Item("Map"));
    player.acquire((Item) new Shootable("Gun"));
    player.acquire((Item) new Shootable("Gun"));
    player.acquire((Item) new Melee("Bat"));
    player.acquire((Item) new Ranged("Knives"));
    player.acquire((Item) new Item("Stimpak"));
    player.showInventory();
    ArrayList<Item> droppedItems = player.manageInventory(reader);
    for (Item i : droppedItems) {
      System.out.println(i.getName());
    }
  }

  static void test7 () {
    player.setName("Bri");
    player.setGender("f");
    System.out.println("BRIS INVENTORY############\n");
    player.showInventory();
    Human h = new Human (player);
    System.out.println("\n\n CLONE INVENTORY##############\n");
    h.showInventory();
    player.acquire(new Item("Map"));
    player.acquire((Item) new Shootable("Gun"));
    player.acquire((Item) new Shootable("Gun"));
    player.acquire((Item) new Melee("Bat"));
    player.acquire((Item) new Ranged("Knives"));
    Item stimpak = new Item ("Stimpak");
    player.acquire((Item) stimpak);
    System.out.println("\n\nBRIS INVENTORY############\n");
    player.showInventory();
    System.out.println("\n\n CLONE INVENTORY##############\n");
    h.showInventory();
    player = h;
    System.out.println("\n\nBRIS INVENTORY############\n");
    player.showInventory();
    System.out.println("\n\n");
  }

  static void test8 () {
    Human h = new Human("bob");
    Human i = new Human(h);
  }

  // This is where the player will loot or examine the room.
	// You can't loot the room until you've cleared it
	static void roomExitSequence(Room r, Scanner reader) {
		System.out.println("--- Preparing to leave " + r.getName() + " ---\n");
		boolean exit = false;
    int choice = -1;
		while (!exit) {
			System.out.println("What would you like to do?");
			System.out.println("1. Exit Room");
			System.out.println("2. Examine Room");
			System.out.println("3. Loot Room");
			System.out.println("4. Manage Inventory");
      if (reader.hasNextInt())
		    choice = reader.nextInt();
			reader.nextLine();
			System.out.println();
			while (choice < 1 || choice > 4) {
				System.out.println("Invalid choice. Please try again.\n");
        if (reader.hasNextInt())
				    choice = reader.nextInt();
				reader.nextLine();
				System.out.println();
			}
			if (choice == 1) {
				exit = true;
			} else if (choice == 2) {
				if (player.hasFlashlight()) {
					r.describe(true);
				} else {
					r.describe(false);
				}
			} else if (choice == 3) {
				r.lootRoom(player, reader);
			} else if (choice == 4) {
				// manage inventory
				player.manageInventory(reader);
			}
		}
	}


}
