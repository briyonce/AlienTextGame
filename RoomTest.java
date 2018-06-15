import java.util.Scanner;

public class RoomTest {

  static Human player = new Human(true);
  static Party p = new Party();

  public static void main(String[] args) {
    // test1();
    test2();
  }

  public static void test1 () {
    Room curRoom = new Room("med bay");
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
