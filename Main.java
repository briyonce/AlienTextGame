import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int stimpak_drop_chance = 60;
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

		boolean valid_input = false; // Checks for appropriate user input when the user is prompted.

		// Gender is only used for pronouns. It may be used later
		// on for discrimination purposes once the player enters
		// the wasteland. Just a thought.
		while (!valid_input) {
			System.out.println("Are you male or female?");
			if (in.hasNextLine()) {
				playerGender = in.nextLine();
			}
			if (playerGender.toLowerCase().equals("male") || playerGender.toLowerCase().equals("m") || playerGender.toLowerCase().equals("female") || playerGender.toLowerCase().equals("f")){
				valid_input = true;
				break;
			} else {
				System.out.println("Invalid option! Please choose from the available options\n");
			}
		}

		// Initialize the player's attributes
		player.setName(playerName);
		player.setGender(playerGender);

		boolean running = true; // Keeps track of game state

		for (int i = 0; i < 15; ++i)
			System.out.println();

		System.out.println("Good Morning, private.\n");

		GAME_START_SEQUENCE_ONE:
		while (running) {
			try {
				System.out.println("-------------------------------------");
				System.out.println("...you open your eyes.\n");
				System.out.println("\"Computer?\"");
				System.out.println("....\n");
				for (int i = 0; i < 3; ++i) {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("....\n");
				}
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
				party.addMember(jessie);

				System.out.println("QUICK!!!");
				System.out.println("Your crewmate is being attacked by a " + e1.getName() + "!!!!");
				valid_input = false;
				String answer = "";
				while (!valid_input) {
					System.out.println("Will you run? Y/N");
					if (in.hasNextLine()) {
						answer = in.nextLine();
					}
					if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
						valid_input = true;
					}
				}

				answer = answer.toLowerCase();
				int luck = 0;
				int result = 2;
				if (answer.equals("y")) {
					luck = rand.nextInt(100);
					if (luck > 60) {
						System.out.println("You were able to escape the " + e1.getName() + "...\n");
						System.out.println("This time.\n");
					} else {
						System.out.println("The " + e1.getName() + " has blocked your way! You can't escape!\n");
					}
				}

				if (luck <= 60 || answer.equals("n")) {
					//engage combat
					result = encounter(e1, in); // -1: death, 0: escape, 1: victory
					if (result < 0) { // death case
						System.out.println("\"Jessie....\" You remember her name.\n");
						die(player, party, e1);
						die(jessie, party, e1);
						valid_input = false;
						answer = "";
						while (!valid_input) {
							System.out.println("Try again? Y/N \n");
							if (in.hasNextLine()) {
								answer = in.nextLine();
							}
							if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n") || answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("no")) {
								valid_input = true;
							}
						}
						answer = answer.toLowerCase();
						if (answer.equals("y") || answer.equals("yes")) {
							continue GAME_START_SEQUENCE_ONE;
						} else {
							System.out.println("Thank you for playing...\n");
							System.out.println("Until next time.\n");
							break;
						}
					} else if (result == 1) {
						System.out.println("You are able to save your crewmate.\n");
					}

				} if (luck > 60 || result == 0) {
					System.out.println("You were able to escape the room...\n");
					System.out.println("You close the door and watch as your crewmate is overtaken by the creature...\n");
					System.out.println("\"Jessie....\" You remember her name.\n");
					die(jessie, party, e1);
					System.out.println("The room falls silent.... what have you done?\n");

				}

				// After initial combat scene
				if (jessie.isAlive()) {
					System.out.println("JESSIE! THAT'S HER NAME!\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("She looks at you - eyes wide with fear.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("\"Wh..Wha... What WAS THAT THING????\" She screams. \n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("\"You: I... I don't know, Jessie. Let's get out of here. There may be more.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("J: \"Okay...\"\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("She is obviously very shaken by the whole experience. The salt from her tears stain her cheeks.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("The two of you exit the room. Never to return.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("You: We need to get you to Med Bay ASAP.\n");
				} else {
					// Face the reality of what you've done
					TimeUnit.SECONDS.sleep(3);
					for (int i = 0; i < 5; ++i)
						System.out.println();
					System.out.println("You monster.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("Brushing away your thoughts of cowardice, you decide to address the situation at hand.\n");
					System.out.println("\"Okay... so we're not alone on this ship...\"\n");
					System.out.println("\"I need to find the captain.\"\n");
					player.incrementCowardice();
				}

				SEQUENCE_TWO:
					if (party.numMembers() > 1 ) { // Jessie is alive
						for (int i = 0; i < 10; ++i)
							System.out.println();
						Room curRoom = new Room("Med Bay");
						System.out.println("MED BAY. 7:00\n");
						TimeUnit.SECONDS.sleep(2);
						System.out.println("You: \"So... Jessie. Do you know what happened?\"\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("J: \"Not really... I only remember up to the party last night.\"\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("J: \"I woke up to some strange sounds in the room... then that thing.\"\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("You place some gauze over her wound. It looks serious.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("J: \"Thank you so much for saving me... I really owe you one.\" She winces.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("\"You: Of course!\" \"I just wish I could've acted sooner...\" You think to yourself.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("You look up at Jessie. The blood is already soaking through from the massive gash on her forehead.\n");
						System.out.println("She looks faint.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.print("You: Hey, Jessie. Rest up a bit. I'm going to see if I can find the captain...");
						System.out.println(" Maybe she knows what's going on.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("You look over at her again. It didn't take long.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("You turn to leave the room.\n");
						roomExitSequence(curRoom);
						//curRoom.lootRoom(player);
					} else { // You let her die
						TimeUnit.SECONDS.sleep(3);
						System.out.println("You're parched, famished, dazed. You deserve it.\n");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("\"What the hell is going on here?\"\n");
					}
					TimeUnit.SECONDS.sleep(3);
					System.out.print("Looking out into the hall you can see the main power is out.");
					System.out.println(" The walls are tinted a faint red from the reserve lights.\n");
					TimeUnit.SECONDS.sleep(3);
					System.out.println("\"Why do they always choose red....\"\n");

					// Here we go...

				in.close();
				break;

			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			break;
		}
	}

	// The fight sequence when you encounter an e1
	public static int encounter(Enemy e1, Scanner in) {
		try {
			while (e1.getHealth() > 0 && player.getHealth() > 0){
				//in combat
				//Player's Turn
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.println("\t Current Health: " + player.getHealth());
				System.out.println("\t " + e1.getName() + " HP: " + e1.getHealth() + "\n");
				System.out.println("What do you want to do?");
				System.out.println("\t 1. Attack");
				System.out.println("\t 2. Use Stimpak");
				System.out.println("\t 3. View Inventory");
				System.out.println("\t 4. Run!");
				boolean valid_input = false;
				String action = "";
				while (!valid_input) {
					if (in.hasNextLine()) {
						action = in.nextLine();
					}
					if (action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")){
						valid_input = true;
					} else {
						System.out.println("Invalid option! Please choose a valid course of action\n");
					}
				}
				if (action.equals("1")) { //Attack
					int damage_dealt = player.attack(e1.getName());
					if (damage_dealt > e1.getHealth()) {
						damage_dealt = e1.getHealth();
					}
					e1.takeDamage(damage_dealt);
					System.out.println("You dealt " + damage_dealt + " damage to the horrid beast. \n");
					TimeUnit.SECONDS.sleep(3);
				} else if (action.equals("2")) { //Heal up
					player.use_stimpak();
					TimeUnit.SECONDS.sleep(3);
				} else if (action.equals("3")) { //View inventory
					player.showInventory();
					continue;
				} else if (action.equals("4")) { // Run!
					int luck = rand.nextInt(100);
					if (luck > 60) {
						System.out.println("You were able to escape the " + e1.getName() + "...\n");
						TimeUnit.SECONDS.sleep(3);
						return 0;
					} else {
						System.out.println("The " + e1.getName() + " has blocked your way! You can't escape!\n");
						TimeUnit.SECONDS.sleep(3);
					}
				}
				System.out.println("...\n");
				System.out.println("...\n");
				System.out.println("...\n");
				TimeUnit.SECONDS.sleep(3);
				// e1's Turn
				if (e1.getHealth() < 1) {
					System.out.println("VICTORY! You have defeated the " + e1.getName() + ". Death comes another day.\n");
					TimeUnit.SECONDS.sleep(3);
					return 1;
				}
				int luck = rand.nextInt(100);
				if (luck > 20) {
					// the attack lands
					int e1_dmg_dealt = e1.attack();
					while (e1_dmg_dealt < 1){
						e1_dmg_dealt = e1.attack();
					}
					e1.landAttack();
					System.out.println("You take " + e1_dmg_dealt + " damage!\n");
					player.takeDamage(e1_dmg_dealt);
					if (e1_dmg_dealt < 10) {
						System.out.println("Just a scratch....\n");
					} else {
						System.out.println("It'll heal... ");
					}
				} else {
					e1.missAttack();
				}
				TimeUnit.SECONDS.sleep(3);
			}
		// the protagonist has fallen
		return -1;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
			return 20;
		}
	}

	//
	static void roomExitSequence(Room r) {
		System.out.println("--- Preparing to leave " + r.getName() + " ---\n");
		boolean exit = false;
		Scanner reader = new Scanner(System.in);
		while (!exit) {
			System.out.println("What would you like to do?");
			System.out.println("1. Exit");
			System.out.println("2. Examine Room");
			System.out.println("3. Loot Room");
			int choice = reader.nextInt();
			reader.nextLine();
			System.out.println();
			while (choice < 1 || choice > 3) {
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
			}
		}
		reader.close();
	}

 // Remove current human h from the party.
 // e1 will be null if they died due to other causes
 // Blood loss, fatigue, misfortune, age, etc.
	static void die(Human h, Party p, Enemy e1) {
		if (!h.isPlayer() && (e1 != null)) {
			System.out.println(h.getGender() + " slowly sinks to the floor, the " + e1.getName() +" doing what it does best.\n");
	    System.out.println("Another one lost to the world's blighted abominations...\n");
	    System.out.println();
		} else if (!h.isPlayer()) {
			System.out.println("You watch " + h.getName() + " collapse. ");
			System.out.print(h.getGender() + " breathes " + h.getPossessive() + " last breath.\n");
		} else if (h.isPlayer() && (e1 != null)){
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
		party.removeMember(h);
	}
}
