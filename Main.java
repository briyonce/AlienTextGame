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

		System.out.println("Good Morning, private.");

		GAME_START_SEQUENCE_ONE:
		while (running) {
			try {
				System.out.println("-------------------------------------");
				System.out.println("...you open your eyes.");
				System.out.println("\"Computer?\"");
				System.out.println("....");
				for (int i = 0; i < 3; ++i) {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("....");
				}
				System.out.println("...you hear something rustling about the room.");
				System.out.println("\"COMPUTER?\"");
				Xeno enemy = new Xeno("Facehugger", 30, 20);
				for (int i = 0; i < 2; ++i) {
					TimeUnit.SECONDS.sleep(2);
					System.out.println("....");
				}
				System.out.println("Your vision clears.");
				TimeUnit.SECONDS.sleep(2);

				// Very first encounter. Jessie is the player's roommate and
				// is currently under attack.
				Human jessie = new Human("Jessie", "she", 70);
				party.addMember(jessie);

				System.out.println("QUICK!!!");
				System.out.println("Your crewmate is being attacked by a " + enemy.getName() + "!!!!");
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
						System.out.println("You were able to escape the " + enemy.getName() + "...");
						System.out.println("This time.");
					} else {
						System.out.println("The " + enemy.getName() + " has blocked your way! You can't escape!\n");
					}
				}

				if (luck <= 60 || answer.equals("n")) {
					//engage combat
					result = encounter(enemy, in); // -1: death, 0: escape, 1: victory
					if (result < 0) { // death case
						System.out.println("\"Jessie....\" You remember her name.");
						die(player, enemy);
						die(jessie, enemy);
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
							System.out.println("Thank you for playing...");
							System.out.println("Until next time.");
							break;
						}
					} else if (result == 1) {
						System.out.println("Congratulations! You are able to save your crewmate.");
					}

				} if (luck > 60 || result == 0) {
					System.out.println("You were able to escape the room...");
					System.out.println("You close the door and watch as your crewmate is overtaken by the creature...");
					System.out.println("\"Jessie....\" You remember her name.");
					die(jessie, enemy);
					System.out.println("The room falls silent.... what have you done?\n");

				}

				// After initial combat scene
				if (jessie.isAlive()) {
					System.out.println("JESSIE! THAT'S HER NAME!");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("She looks at you - eyes wide with fear.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("\"Wh..Wha... What WAS THAT THING????\" She screams. \n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("\"You: I... I don't know, Jessie. Let's get out of here. There may be more.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("J: \"Okay...\"\n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("She is obviously very shaken by the whole experience. The salt from her tears stain her cheeks.\n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("The two of you exit the room. Never to return.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("You: We need to get you to Med Bay ASAP.");
				} else {
					// Face the reality of what you've done
					TimeUnit.MILLISECONDS.sleep(1500);
					for (int i = 0; i < 5; ++i)
						System.out.println();
					System.out.println("You monster.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("Brushing away your thoughts of cowardice, you decide to address the situation at hand.");
					System.out.println("\"Okay... so we're not alone on this ship...\"\n");
					System.out.println("\"I need to find the captain.\"\n");
					player.incrementCowardice();
				}

				SEQUENCE_TWO:
					if (party.numMembers() > 1 ) { // Jessie is alive
						for (int i = 0; i < 10; ++i)
							System.out.println("MED BAY. 7:00");
						TimeUnit.MILLISECONDS.sleep(1000);
						System.out.println("You: \"So... Jessie. Do you know what happened?\"\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("J: \"Not really... I only remember up to the party last night.\"\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("J: \"I woke up to some strange sounds in the room... then that thing.\"\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("You place some gauze over her wound. It looks serious.\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("J: \"Thank you so much for saving me... I really owe you one.\" She winces.\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("\"You: Of course!\" \"I just wish I could've acted sooner...\" You think to yourself.\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("You look up at Jessie. The blood is already soaking through from the massive gash on her forehead.\n");
						System.out.println("She looks faint.");
						TimeUnit.MILLISECONDS.sleep(800);
					} else { // You let her die
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("You're parched, famished, dazed. You deserve it.\n");
						TimeUnit.MILLISECONDS.sleep(800);
						System.out.println("\"What the hell is going on here?\"\n");
					}
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.print("Looking out into the hall you can see the main power is out.");
					System.out.println(" The walls are tinted a faint red from the reserve lights.\n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("\"Why do they always choose red....\"\n");

				in.close();
				break;

			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			break;
		}
	}

	// The fight sequence when you encounter an enemy
	public static int encounter(Enemy enemy, Scanner in) {
		try {
			while (enemy.getHealth() > 0 && player.getHealth() > 0){
				//in combat
				//Player's Turn
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.println("\t Current Health: " + player.getHealth());
				System.out.println("\t " + enemy.getName() + " HP: " + enemy.getHealth() + "\n");
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
					int damage_dealt = player.attack(enemy.getName());
					enemy.takeDamage(damage_dealt);
					System.out.println("You dealt " + damage_dealt + " damage to the horrid beast. \n");
					TimeUnit.MILLISECONDS.sleep(800);
				} else if (action.equals("2")) { //Heal up
					player.use_stimpak();
					TimeUnit.MILLISECONDS.sleep(800);
				} else if (action.equals("3")) { //View inventory
					player.ShowInventory();
					continue;
				} else if (action.equals("4")) { // Run!
					int luck = rand.nextInt(100);
					if (luck > 60) {
						System.out.println("You were able to escape the " + enemy.getName() + "...");
						TimeUnit.MILLISECONDS.sleep(800);
						return 0;
					} else {
						System.out.println("The " + enemy.getName() + " has blocked your way! You can't escape!\n");
						TimeUnit.MILLISECONDS.sleep(800);
					}
				}
				System.out.println("...");
				System.out.println("...");
				System.out.println("...");
				TimeUnit.MILLISECONDS.sleep(800);
				// Enemy's Turn
				if (enemy.getHealth() < 1) {
					System.out.println("VICTORY! You have defeated the " + enemy.getName() + ". Death comes another day.");
					TimeUnit.MILLISECONDS.sleep(800);
					return 1;
				}
				int luck = rand.nextInt(100);
				if (luck > 20) {
					// the attack lands
					int enemy_dmg_dealt = enemy.attack();
					while (enemy_dmg_dealt < 1){
						enemy_dmg_dealt = enemy.attack();
					}
					enemy.landAttack();
					System.out.println("You take " + enemy_dmg_dealt + " damage!\n");
					player.loseHealth(enemy_dmg_dealt);
					if (enemy_dmg_dealt < 10) {
						System.out.println("Just a scratch....");
					} else {
						System.out.println("It'll heal... ");
					}
				} else {
					enemy.missAttack();
				}
				TimeUnit.MILLISECONDS.sleep(800);
			}
		// the protagonist has fallen
		return -1;
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
			return 20;
		}
	}

 // Remove current human h from the party.
 // Enemy will be null if they died due to other causes
 // Blood loss, fatigue, misfortune, age, etc.
	static void die(Human h, Enemy enemy) {
		if (!h.isPlayer() && (enemy != null)) {
			System.out.println(h.getGender() + " slowly sinks to the floor, the " + enemy.getName() +" doing what it does best.");
	    System.out.println("Another one lost to the world's blighted abominations...\n");
	    System.out.println();
		} else if (!h.isPlayer()) {
			System.out.println("You watch " + h.getName() + " collapse. ");
			System.out.print(h.getGender() + " breathes " + h.getPossessive() + " last breath.");
		} else if (h.isPlayer() && (enemy != null)){
			System.out.print("You collapse and watch as your crewmate");
			if (party.numMembers() > 2) {
				System.out.print("s are ");
			} else {
				System.out.print(" is ");
			}
			System.out.println("overtaken by the creature...");
		} else {
			System.out.println("You have died... Death comes and grasps you with its cold, bony claws...");
	    System.out.println("...in another time... in another life...\n");
		}
		h.die();
		party.removeMember(h);
	}
}
