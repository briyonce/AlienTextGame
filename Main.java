import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int Stimpak_drop_chance = 60;
	static Random rand = new Random();
	static Human player = new Human(true);
	static Party party = new Party(player);
	static Party enemies = new Party();

	public static void main (String[] args) {


		// Game Variables
		Scanner in = new Scanner(System.in);

		// Player Variables
		String playerName = "";
		String playerGender = "";

		// ESKETITTTTT
		System.out.println("Welcome to *GAME TITLE*");
		System.out.println("What is your name?");
		if (in.hasNextLine()) {
			playerName = in.nextLine();
		}

		boolean validInput = false; // Checks for appropriate user input when the user is prompted.

		// Gender is only used for pronouns. It may be used later
		// on for discrimination purposes once the player enters
		// the wasteland. Just a thought.
		while (!validInput) {
			System.out.println("Are you male or female?");
			if (in.hasNextLine()) {
				playerGender = in.nextLine();
			}
			if (playerGender.toLowerCase().equals("male") || playerGender.toLowerCase().equals("m") || playerGender.toLowerCase().equals("female") || playerGender.toLowerCase().equals("f")){
				validInput = true;
				break;
			} else {
				System.out.println("Invalid option! Please choose from the available options\n");
			}
		}

		if (playerGender.charAt(0) == 'm') {
			playerGender = "he";
		} else {
			playerGender = "she";
		}

		// Initialize the player's attributes
		player.setName(playerName);
		player.setGender(playerGender);

		boolean running = true; // Keeps track of game state

		for (int i = 0; i < 15; ++i)
			System.out.println();

		// GAME START
		System.out.println("Good Morning, private.\n");

		GAME_START_SEQUENCE_ONE:
		while (running) {
			try {
				System.out.println("-------------------------------------");
				System.out.println("...you open your eyes.\n");
				System.out.println("\"Computer?\"");
				for (int i = 0; i < 3; ++i) {
					System.out.println("....\n");
					TimeUnit.SECONDS.sleep(2);
				}
				System.out.println("....\n");

				System.out.println("...you hear something rustling about the room.\n");
				System.out.println("\"COMPUTER?\"");
				TimeUnit.SECONDS.sleep(3);
				Xeno e1 = new Xeno("Facehugger", 30, 20);
				enemies.addMember(e1);
				for (int i = 0; i < 2; ++i) {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("....\n");
				}
				System.out.println("Your vision clears.\n");
				TimeUnit.SECONDS.sleep(2);

				// Very first encounter. Jessie is the player's roommate and
				// is currently under attack.
				Human jessie = new Human("Jessie", "she", 70);

				System.out.println("QUICK!!!");
				System.out.println("Your crewmate is being attacked by a " + e1.getName() + "!!!!");
				validInput = false;
				String answer = "";
				while (!validInput) {
					System.out.println("Will you run? Y/N");
					if (in.hasNextLine()) {
						answer = in.nextLine().toLowerCase();
					}
					if (answer.equals("y") || answer.equals("n") || answer.equals("yes") || answer.equals("no")) {
						validInput = true;
					}
				}

				answer = answer.toLowerCase();
				int luck = 0;
				int result = 2;
				if (answer.equals("y") || answer.equals("yes")) {
					luck = rand.nextInt(100);
					if (luck > 60) {
						System.out.println("You were able to escape the " + e1.getName() + "...\n");
						System.out.println("This time.\n");
					} else {
						System.out.println("The " + e1.getName() + " has blocked your way! You can't escape!\n");
					}
				}

				if (luck <= 60 || answer.equals("n") || answer.equals("no")) {
					//engage combat
					result = encounter(in); // -1: death, 0: escape, 1: victory
					if (result < 0) { // you died in the first encounter
						System.out.println("\"Jessie....\" You remember her name.\n");
						die(player, party, e1);
						die(jessie, null, e1);
						if(endGame(in)) {
							break;
						} else {
							continue GAME_START_SEQUENCE_ONE;
						}
					} else if (result == 1) { // You beat the enemy!
						enemies.empty();
						System.out.println("You are able to save your crewmate.\n");
					}
				} if (luck > 60 || result == 0) { // you escape and leave Jessie to die
					System.out.println("You were able to escape the room...\n");
					System.out.println("You close the door and watch as your crewmate is overtaken by the creature...\n");
					System.out.println("\"Jessie....\" You remember her name.\n");
					die(jessie, null, e1);
					System.out.println("The room falls silent.... what have you done?\n");
					enemies.empty();
				}

				// After initial combat scene
				if (jessie.isAlive()) {
					party.addMember(jessie);
					SequenceText.jessieIsAlive();
				} else {
					// Face the reality of what you've done
					TimeUnit.SECONDS.sleep(3);
					for (int i = 0; i < 5; ++i)
						System.out.println();
					SequenceText.youMonster();
					player.incrementCowardice();
				}

				SEQUENCE_TWO:
				while (running) {
					Human save = new Human(player);
					if (party.numMembers() > 1 ) { // Jessie is alive
						for (int i = 0; i < 10; ++i)
							System.out.println();
						Room curRoom = new MedBay();

						SequenceText.sequenceTwoMedBay(player);

						roomExitSequence(curRoom);
					} else { // You let her die
						TimeUnit.SECONDS.sleep(3);
						SequenceText.sequenceTwoAlone(player);
					}
					TimeUnit.SECONDS.sleep(3);
					SequenceText.initialRemarks();

					// Here we go...
					for (int i = 0; i < 20; ++i)
						System.out.println();

					System.out.println("HALLWAY. 7:35\n");
					SequenceText.hallwayOne();
					answer = "";
					validInput = false;
					while (!validInput) {
						SequenceText.narrationText("Do you want to follow the sound? (Y/N)");
						answer = in.nextLine().toLowerCase();
						if (answer.equals("y") || answer.equals("n") || answer.equals("yes") || answer.equals("no")) {
							validInput = true;
							break;
						} else {
							System.out.println("Please choose a valid answer.\n");
						}
					}
					if (answer.equals("y") || answer.equals("yes")) {
						Xeno enemy1 = new Xeno ("Xenomorph 1", 100, 100);
						Xeno enemy2 = new Xeno ("Xenomorph 2", 100, 100);
						Xeno enemy3 = new Xeno ("Xenomorph 3", 100, 100);
						party.removeMember(jessie);
						enemies.addMember(enemy1);
						enemies.addMember(enemy2);
						enemies.addMember(enemy3);
						result = encounter(in);
						if (result < 0) {
							if(endGame(in)) {
								break;
							} else {
								player = save;
								party.addMember(player);
								continue SEQUENCE_TWO;
							}
						} else {
							System.out.println("You made it.\n");
						}
					} else {
						System.out.println("You turn to go the other direction.\n");
					}
				}
				running = false;
				in.close();
				break;

			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			break;
		}
	}

	static boolean endGame(Scanner in) {
		boolean validInput = false;
		String answer = "";
		while (!validInput) {
			System.out.println("Try again? Y/N \n");
			if (in.hasNextLine()) {
				answer = in.nextLine().toLowerCase();
			}
			if (answer.equals("y") || answer.equals("n") || answer.equals("yes") || answer.equals("no")) {
				validInput = true;
			}
		}
		answer = answer.toLowerCase();
		if (answer.equals("y") || answer.equals("yes")) {
			return false;
		} else {
			System.out.println("Thank you for playing...\n");
			System.out.println("Until next time.\n");
			return true;
		}
	}

	// The fight sequence when you encounter an enemy
	// -1. you die. 0. you run. 1 you win.
	public static int encounter(Scanner in) {
		try {
			int playerCounter = 0;
			int enemyCounter = 0;
			while (enemies.numMembers() > 0 && party.numMembers() > 0){
				Enemy e = (Enemy) enemies.getMember(enemyCounter % enemies.size());
				Human h = (Human) party.getMember(playerCounter % party.size());
				//in combat
				//Player's Turn
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.print("\t");
				party.printPartyHealth();
				System.out.print("\t");
				enemies.printPartyHealth();
				System.out.println("What do you want to do?");
				System.out.println("\t 1. Attack");
				System.out.println("\t 2. Use Stimpak");
				System.out.println("\t 3. View Inventory");
				System.out.println("\t 4. Run!");
				boolean validInput = false;
				String action = "";
				while (!validInput) {
					if (in.hasNextLine()) {
						action = in.nextLine();
					}
					if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")){
						validInput = true;
					} else {
						System.out.println("Invalid option! Please choose a valid course of action\n");
					}
				}
				if (action.equals("1")) { //Attack
					h.chooseWeapon(in, e);
					TimeUnit.SECONDS.sleep(3);
				} else if (action.equals("2")) { //Heal up
					h.use_Stimpak();
					TimeUnit.SECONDS.sleep(3);
				} else if (action.equals("3")) { //View inventory
					h.showInventory();
					continue;
				} else if (action.equals("4")) { // Run!
					int luck = rand.nextInt(100);
					if (luck > 60) {
						System.out.println("You were able to escape the " + e.getName() + "...\n");
						TimeUnit.SECONDS.sleep(3);
						return 0;
					} else {
						System.out.println("The " + e.getName() + " has blocked your way! You can't escape!\n");
						TimeUnit.SECONDS.sleep(3);
					}
				}
				System.out.println("...\n");
				System.out.println("...\n");
				System.out.println("...\n");
				TimeUnit.SECONDS.sleep(3);
				// e1's Turn
				if (e.getHealth() < 1) {
					enemies.removeMember(e);
					System.out.println("VICTORY! You have defeated the " + e.getName() + ". Death comes another day.\n");
					TimeUnit.SECONDS.sleep(3);
					return 1;
				}
				// The enemy attacks now
				e.attack(h);
				if (h.getHealth() < 1) {
					die(h, party, e);
				}
				++enemyCounter;
				++playerCounter;
				TimeUnit.SECONDS.sleep(3);
			}
		// the protagonist has fallen
		enemies.empty();
		return -1;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
			return 20;
		}
	}

	// This is where the player will loot or examine the room.
	// You can't loot the room until you've cleared it
	static void roomExitSequence(Room r, Scanner reader) {
		System.out.println("--- Preparing to leave " + r.getName() + " ---\n");
		boolean exit = false;
		while (!exit) {
			System.out.println("What would you like to do?");
			System.out.println("1. Exit Room");
			System.out.println("2. Examine Room");
			System.out.println("3. Loot Room");
			System.out.println("4. Manage Inventory");
			int choice = reader.nextInt();
			reader.nextLine();
			System.out.println();
			while (choice < 1 || choice > 4) {
				System.out.println("Invalid choice. Please try again.\n");
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

 // Remove current human h from the party.
 // e1 will be null if they died due to other causes
 // Blood loss, fatigue, misfortune, age, etc.
	static void die(Human h, Party p, Enemy e1) {
		if (!h.isPlayer() && (e1 != null)) {
			System.out.println(h.getGender() + " slowly sinks to the floor, the " + e1.getName() + " doing what it does best.\n");
	    System.out.println("Another one lost to the world's blighted abominations...\n");
	    System.out.println();
		} else if (!h.isPlayer()) {
			System.out.println("You watch " + h.getName() + " collapse. ");
			System.out.print(h.getGender() + " breathes " + h.getPossessive() + " last breath.\n");
		} else if (h.isPlayer() && (e1 != null) && (p.numMembers() > 1)){
			System.out.print("You collapse and watch as your crewmate");
			if (party.numMembers() > 2) {
				System.out.print("s are ");
			} else {
				System.out.print(" is ");
			}
			System.out.println("overtaken by the creature...\n");
		} else {
			System.out.println("You have died... Death comes and grasps you with its cold, bony claws...\n");
	    System.out.println("...in another time... in another life...\n");
		}
		h.die();
		if (p != null)
			p.removeMember(h);
	}
}
