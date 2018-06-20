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
    test6();
  }

  public static void test1 () {
    Room curRoom = new Room("med bay");
    curRoom.tierDisplay();
    curRoom.display();
    Human h = new Human();
    Party pa = new Party(h);
    pa.printParty();
    curRoom.lootRoom(h, new Scanner(System.in));
    pa.printParty();
  }

  static void test2() {
    Room curRoom = new Room("med bay");
    player.setName("bri");
    player.setGender("f");
    p.addMember(player);
    p.printParty();
    roomExitSequence(curRoom);
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
    player.acquire(new Shootable("gun"));
    player.acquire(new Shootable("gun"));
    player.acquire(new Ranged("knives"));
    player.showInventory();
    player.chooseWeapon(reader, x);
  }

  static void test6 () {
    Scanner reader = new Scanner(System.in);
    player.setName("bri");
    player.setGender("f");
    player.acquire(new Item("map"));
    player.acquire(new Shootable("gun"));
    player.acquire(new Shootable("gun"));
    player.acquire(new Melee("bat"));
    player.acquire(new Ranged("knives"));
    player.acquire(new Item("stimpak"));
    player.showInventory();
    player.manageInventory(reader);
  }

  static void roomExitSequence(Room r) {
		System.out.println("Preparing to leave " + r.getName());
		boolean exit = false;
		Scanner reader = new Scanner(System.in);
		while (!exit) {
			System.out.println("What would you like to do?");
			System.out.println("1. Exit");
			System.out.println("2. Examine Room");
			System.out.println("3. Loot Room");
			int choice = reader.nextInt();
			reader.nextLine();
			while (choice < 1 || choice > 3) {
				System.out.println("Invalid choice. Please try again.\n");
				choice = reader.nextInt();
				reader.nextLine();
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
			}
		}
		reader.close();
	}


}
