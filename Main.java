
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static int num_stimpaks = 4;
	static int stimpak_regen = 40; // The amount of health regenerated from a stimpak
	static int stimpak_drop_chance = 60;

	static int health = 100;
	static int player_atkdmg = 15;
	static Random rand = new Random();
	static Scanner in = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		
		
		//Game Variables
		String[] enemies = {"Facehugger", "Chest-Burster", "Xenomorph", "Bloodburster", "Neomorph"};
		String[] xeno_castes = {"Queen", "Praetorian", "Predalien", "Spitter", "Lurker", "Runner", "Warrior"};
		int max_enemy_health = 300;
		int max_enemy_atkdmg = 50;
		
		//Player Variables


		
		boolean running = true; 
		
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
			
			int fh_health = 30;
			String enemy = "Facehugger";
			for (int i = 0; i < 2; ++i) {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("....");
			}
			System.out.println("Your vision clears.");
			TimeUnit.SECONDS.sleep(2);
			//Very first encounter
			System.out.println("QUICK!!!");
			System.out.println("Your crewmate is being attacked by a " + enemy + "!!!!");
			boolean valid_input = false;
			String action = "";
			while (!valid_input) {
				System.out.println("What do you do? Run or attack?");
				if (in.hasNextLine()) {
					action = in.nextLine();
				}
				if ((action.toLowerCase().equals("run")) || (action.toLowerCase().equals("attack"))) {
					valid_input = true;
				}
			}
			action = action.toLowerCase();
			System.out.println("You decide to " + action + ".");
			int luck = rand.nextInt(100);
			int result = 2;
			if (action.equals("run")) {
				if (luck > 60) {
					System.out.println("You were able to escape the " + enemy + "...");
					System.out.println("This time.");
				} else {
					System.out.println("The " + enemy + " has blocked your way! You can't escape!");
				}
			} 
			
			if (luck <= 60 || action.equals("attack")) {
				//engage combat
				result = encounter(enemy, fh_health, 20); // -1: death, 0: escape, 1: victory
				if (result < 0) { // death case
					valid_input = false;
					String answer = "";
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
					break;
				}
			}
			
			if (luck > 60 || result == 0) {
				System.out.println("You were able to escape the room...");
				System.out.println("You close the door and watch as your crewmate is overtaken by the creature...");
				System.out.println("\"Jessie....\" You remember her name.");
				System.out.println("She slowly sinks to the floor, the Facehugger doing what it does best.");
				System.out.println("The room falls silent.... what have you done?");
				
			}
			
			
				
			
			
			
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			break;
		}
	}
	
	public static int encounter(String enemy, int enemy_health, int enemy_dmg) {
		String[] attack_sounds = {"You lunge for the " + enemy + "! Slash! Bam!", "Your bare fists meet the flesh of your enemy... It squeals in pain and scurries away before you can deal any more damage."};
		while (enemy_health > 0 && health > 0){
			//in combat
			//Player's Turn
			System.out.println("-------------------------------------");
			System.out.println("\t Current Health: " + health);
			System.out.println("\t " + enemy + " HP: " + enemy_health + "\n");
			System.out.println("What do you want to do?");
			System.out.println("\t 1. Attack");
			System.out.println("\t 2. Use Stimpak");
			System.out.println("\t 3. Run!");
			boolean valid_input = false;
			String action = "";
			while (!valid_input) {
				if (in.hasNextLine()) {
					action = in.nextLine();
				}
				if (action.equals("1") || action.equals("2") || action.equals("3")){
					valid_input = true;
				} else {
					System.out.println("Invalid option! Please choose a valid course of action\n");
				}
			}
			if (action.equals("1")) { //Attack
				int damage_dealt = rand.nextInt(player_atkdmg);
				int atk_choice = rand.nextInt(attack_sounds.length);
				String attack_sequence = attack_sounds[atk_choice];
				System.out.println(attack_sequence +"\n");
				enemy_health -= damage_dealt;
				System.out.println("You dealt " + damage_dealt + " damage to the horrid beast. \n");
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
			} else if (action.equals("3")) { // Run!
				int luck = rand.nextInt(100);
				if (luck > 60) {
					System.out.println("You were able to escape the " + enemy + "...");
					return 0; 
				} else {
					System.out.println("The " + enemy + " has blocked your way! You can't escape!");
				}
			}
			// Enemy's Turn
			int luck = rand.nextInt(100);
			if (luck > 60) {
				// the attack lands
				int enemy_dmg_dealt = rand.nextInt(enemy_dmg);
				if (enemy.equals("Facehugger")) {
					System.out.println("\"GAH!!!!\" You wail! The Facehugger slashes at you with its long tail!");
					System.out.println("It scurries away before you can retaliate.\n");
				}
				System.out.println("You take " + enemy_dmg_dealt + " damage!");
				health -= enemy_dmg_dealt;
			} else {
				if (enemy.equals("Facehugger")) {
					System.out.println("The tiny abomination lunges to attack you but you back away just in time.");
					System.out.println("It scurries away before you can retaliate.\n");
					System.out.println("You've escaped your inevitable death for another brief moment.");
					System.out.println("Time to gamble yet again.");
				}
			}
		}
		// Either the protagonist or the enemy has fallen
		if (enemy_health < 1) {
			System.out.println("VICTORY! You have defeated the " + enemy + ". Death comes another day.");
			return 1;
		} else {
			System.out.println("You have died... Death comes and grasps you with its cold, bony claws...");
			System.out.println("...in another time... in another life...\n");
			return -1;
		}
	}
}
