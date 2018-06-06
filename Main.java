import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int num_stimpaks = 4;
	static int stimpak_regen = 40; // The amount of health regenerated from a stimpak
	static int stimpak_drop_chance = 60;

	static int health = 100;
	static int player_atkdmg = 15;
	static Random rand = new Random();
	static Scanner in = new Scanner(System.in);
	static ArrayList<String> inventory = new ArrayList<String>();
	static int max_inv_size = 10;
	static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	static Human player = new Human();

	public static void main (String[] args) {



		//Game Variables
		boolean jessie_alive = true;


		//Player Variables
		String playerName = "";



		System.out.println("Welcome to *GAME TITLE*");
		System.out.println("What is your name?");
		if (in.hasNextLine()) {
			playerName = in.nextLine();
		}

		player.setName(playerName);

		player.acquire("stimpak");
		player.acquire("stimpak");
		player.acquire("stimpak");
		player.acquire("stimpak");

		boolean running = true;

		for (int i = 0; i < 15; ++i)
			System.out.println();

		System.out.println("Good Morning, private.");

		GAME:
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
				//Very first encounter
				System.out.println("QUICK!!!");
				System.out.println("Your crewmate is being attacked by a " + enemy.getName() + "!!!!");
				boolean valid_input = false;
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
					result = encounter(enemy); // -1: death, 0: escape, 1: victory
					if (result < 0) { // death case
						System.out.println("You fall to the floor and watch as your crewmate is overtaken by the creature...");
						System.out.println("\"Jessie....\" You remember her name.");
						System.out.println("She slowly sinks to the floor, the Facehugger doing what it does best.");
						System.out.println("The room falls silent as you breathe your last breath...\n");
						System.out.println();
						System.out.println("You have died... Death comes and grasps you with its cold, bony claws...");
						System.out.println("...in another time... in another life...\n");
						jessie_alive = false;
						valid_input = false;
						answer = "";
						while (!valid_input) {
							System.out.println("Try again? Y/N \n");
							if (in.hasNextLine()) {
								answer = in.nextLine();
							}
							if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
								valid_input = true;
							}
						}
						answer = answer.toLowerCase();
						if (answer.equals("y")) {
							continue GAME;
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
					System.out.println("She slowly sinks to the floor, the Facehugger doing what it does best.");
					System.out.println("The room falls silent.... what have you done?\n");
					jessie_alive = false;
				}

				// After initial combat scene
				if (jessie_alive) {
					System.out.println("JESSIE!");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("She looks at you - eyes wide with fear.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("\"Wh..Wha... What WAS THAT THING????\" She screams. \n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("\"I... I don't know, Jessie. Let's get out of here. There may be more.");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("J: \"Okay...\"\n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("She is obviously very shaken by the whole experience. The salt from her tears stain her cheeks.\n");
					TimeUnit.MILLISECONDS.sleep(800);
					System.out.println("The two of you exit the room. Never to return.");
					TimeUnit.MILLISECONDS.sleep(800);
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
				}
				break;

			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			break;
		}
	}

	public static int encounter(Enemy enemy) {
		try {

			while (enemy.getHealth() > 0 && health > 0){
				//in combat
				//Player's Turn
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.println("\t Current Health: " + health);
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
					if (num_stimpaks > 1) {
						if (health == 100) {
							System.out.println("Health already full.... Stimpak wasted.");
							--num_stimpaks;
						} else if ((health + stimpak_regen) > 100) {
							System.out.println("Ahhhh.... that feels better.");
							--num_stimpaks;
							health = 100;
						} else {
							System.out.println("Ahhhh.... that feels better.");
							--num_stimpaks;
							health += stimpak_regen;
						}
					} else {
						System.out.println("Sorry... no stimpaks available. Good luck! You've got this!\n");
					}
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
				if (luck > 60) {
					// the attack lands
					int enemy_dmg_dealt = enemy.attack();
					while (enemy_dmg_dealt < 1){
						enemy_dmg_dealt = enemy.attack();
					}
					enemy.landAttack();
					System.out.println("You take " + enemy_dmg_dealt + " damage!\n");
					player.loseHealth(enemy_dmg_dealt);;
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
}
