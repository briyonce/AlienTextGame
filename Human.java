// Base class for all humans in the game.
// There may one day be different tiers or classes of humans
import java.util.Scanner;
import java.util.Random;

public class Human extends Entity {
  public static void main(String[] args){}
  static int MAX_COWARDICE = 100;   // I wonder what'll happen if they hit 100...

  private String gender = "he";     // He/she rather than male/female
  private String possesive = "his"; // His/hers. Used for grammatical purposes
  private boolean isPlayer = false;
  private Random r = new Random();
  private Inventory inventory = new Inventory();
  private int STARTING_STIMPAKS = 4;
  private int cowardice = 0;        // Cowardice/karma mechanic. To be implemented
                                    // later on.
  private boolean hasFlashlight = false;

  public Human () {
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String g) {
    // Random r = new Random();
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (boolean p) {
    for (int i = 0; i < STARTING_STIMPAKS; ++i)
      acquire(inventory.stimpak, true);
    this.isPlayer = true;
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g) {
    // Random r = new Random();
    super.name = n;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g, int h) {
    // Random r = new Random();
    super.name = n;
    super.health = h;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  String getGender() {
    return this.gender;
  }

  String getPossessive() {
    return this.possesive;
  }

  boolean isPlayer() {
    return this.isPlayer;
  }

  // Used for examining your environment
  boolean hasFlashlight() {
    return this.hasFlashlight;
  }

  // Used to give the player an initial amount of items
  // without displaying the "acquire text".
  boolean acquire(Item item, boolean initial) {
    return inventory.acquire(item);
  }

  // Add an item to your inventory
  boolean acquire(Item item) {
    boolean success = inventory.acquire(item);
    if (!success) {
      System.out.println("INVENTORY FULL: " + inventory.getUsage() + ": " + item.getWeight());
      System.out.println(this.name + " needs to drop something.\n");
      return false;
    } else {
      if (this.isPlayer) {
        System.out.println("You pick up the " + item.getName());
      } else {
        System.out.println(this.name + " picks up the " + item.getName());
      }
      if (item.getName().toLowerCase().equals("flashlight")) {
        this.hasFlashlight = true;
      }
      return true;
    }
  }

  // Remove an item from your inventory
  void drop(Item item) {
    boolean success = inventory.drop(item, false);
    if (success) {
      this.hasFlashlight = false;
    } else {
      System.out.println(this.name + " didn't even have this to begin with...\n");
    }
  }

  Weapon chooseWeapon (Scanner reader, Enemy e) {
    int goBack = 0;
    System.out.println("What type of weapon?");
    System.out.print("\t 1. Shootable: ");
    inventory.displayShootables();
    System.out.print("\t 2. Melee: ");
    inventory.displayMelee();
    System.out.print("\t 3. Ranged: ");
    inventory.displayRanged();
    int choice = reader.nextInt();
    reader.nextLine();
    while (choice < 1 || choice > 3) {
      System.out.println("Invalid choice. Choose a valid option.");
      choice = reader.nextInt();
      reader.nextLine();
    }
    if (choice == 1) {
      goBack = inventory.chooseShootables(reader);
    } else if (choice == 2) { // Fist
      goBack = inventory.chooseMelee(reader);
    } else { // Ranged weapons
      goBack = inventory.chooseRanged(reader);
    }
    if (goBack == -1) { // -1: Go back, 0: Fists, 1: Use weapon (done.)
      chooseWeapon(reader, e); // Hopefully this won't call an infinite loop
    } else if (goBack == 0) {
      this.attack(e);
    }
    return null;
  }
  // Heal yo' self
  void heal(Item i) {
    this.health += i.heal();
    System.out.print("Ahhhh.... that feels better.\n");
    if (this.health > 100) { // Cap health at 100
      this.health = 100;
    }
    System.out.println(" Health: " + this.health);
  }

  // Quick inventory printout for party view
  String inventorySimplePrint() {
    return inventory.inventorySimplePrint();
  }

  // More detailed inventory printout for combat
  void showInventory() {
    inventory.showInventory();
  }

  // The number of stimpaks the character has.
  // Will be changed later as more health items
  // are introduced into the game.
  int num_stimpaks() {
    return inventory.numStimpaks();
  }

  // For grammatical purposes.
  void setGender(String g) {
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
  }

  // Move an item from this character's inventory
  // to another character's inventory
  void give (Item i, Human h) {
    boolean hasItem = inventory.drop(i, false);
    if (hasItem) {
      System.out.println("You give " + h.getName() + " the " + i.getName() + ".\n");
      h.acquire(i);
    } else {
      System.out.println("You don't even have that item to give...\n");
    }
  }

  // Used for the fututre cowardice/karma mechanic
  void incrementCowardice() {
    cowardice += 10;
  }

  // Used in conjunction with heal()... I think. I hope.
  void use_stimpak() {
    if (num_stimpaks() > 0) {
      if (this.health == 100) {
        System.out.println("Health already full.... Stimpak wasted.\n");
        drop(inventory.stimpak);
      } else {
        heal(inventory.stimpak);
        drop(inventory.stimpak);
      }
    } else {
      System.out.println("Sorry... no stimpaks available. Good luck! You've got this!\n");
    }
  }

  // Null out value. RIP!
  void die() {
    this.health = -1;
  }
}
