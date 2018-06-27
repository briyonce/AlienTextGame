// Base class for all humans in the game.
// There may one day be different tiers or classes of humans
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Human extends Entity {
  public static void main(String[] args){}
  static int MAX_COWARDICE = 100;   // I wonder what'll happen if they hit 100...

  private String gender = "he";     // He/she rather than male/female
  private String possesive = "his"; // His/hers. Used for grammatical purposes
  private String spouseName = "Claire";
  private boolean isPlayer = false;
  private Random r = new Random();
  private Inventory inventory = new Inventory();
  private int STARTING_STIMPAKS = 4;
  private int cowardice = 0;        // Cowardice/karma mechanic. To be implemented
                                    // later on.
  private boolean hasFlashlight = false;

  public Human () {
    acquire((Item) inventory.fist, true);
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (Human h) {
    this.name = h.name;
    this.possesive = h.possesive;
    this.spouseName = h.spouseName;
    this.isPlayer = h.isPlayer;
    System.out.println(inventory.isEmpty());
    this.inventory.empty();
    System.out.println(inventory.numItems());
    this.inventory.transfer(h.inventory);
    this.cowardice = h.cowardice;
    System.out.println(inventory.numItems());
  }

  public Human (String g) {
    // Random r = new Random();
    acquire((Item) inventory.fist, true);
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
      this.spouseName = "Richard";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (boolean p) {
    acquire((Item) inventory.fist, true);
    for (int i = 0; i < STARTING_STIMPAKS; ++i)
      acquire(inventory.Stimpak, true);
    this.isPlayer = true;
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g) {
    // Random r = new Random();
    acquire((Item) inventory.fist, true);
    super.name = n;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
      this.spouseName = "Richard";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g, int h) {
    // Random r = new Random();
    acquire((Item) inventory.fist, true);
    super.name = n;
    super.health = h;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
      this.spouseName = "Richard";
    }
    super.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  String getGender() {
    return this.gender;
  }

  String getPossessive() {
    return this.possesive;
  }

  String getSpouse() {
    return this.spouseName;
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

  // Used in combat so the player can choose their weapon
  void chooseWeapon (Scanner reader, Enemy e) {
    Weapon weapon = null;
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
    if (choice >= 1 || choice <= 3) {
      weapon = (Weapon) inventory.chooseItem(choice + 1, reader, false);
      if (weapon == null) { // -1: Go back, 0: Fists, 1: Use weapon (done.)
        chooseWeapon(reader, e); // Hopefully this won't call an infinite loop
      } else if (choice == 2){
        if (weapon.getName().equals("Fist")) {
          this.attack(e);
        }
      } else {
        weapon.attack();
      }
    }
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

  // Used for debugging
  void listInventory() {
    inventory.listInventory();
  }

  // Used to drop inventory. Will edit further to add item analysis.
  ArrayList<Item> manageInventory(Scanner reader) {
    ArrayList<Item> dropped = new ArrayList<Item>();
    boolean validInput = false;
    int choice = -1;
    Item droppable = null;
    boolean running = true;
    MANAGE:
    while (running) {
      while (!validInput) {
        System.out.println("\t --- INVENTORY --- \n");
        inventory.listInventory();
        System.out.println("5. Back.");
        System.out.println();
        System.out.println("What type of item would you like to get rid of?");
        if (reader.hasNextInt())
          choice = reader.nextInt();
        reader.nextLine();
        if (choice >= 0 && choice < 6) {
          validInput = true;
          break;
        } else {
          System.out.println("Invalid option. Please choose from one of the available categories.");
        }
        System.out.println();
      }
      if (choice == 5) {
        // Do nothing and let the method return.
        running = false;
      } else {
        droppable = inventory.chooseItem(choice, reader, true);
        if (droppable == null) { // -1: Go back, 0: Fists, 1: Use weapon (done.)
          validInput = false;
          continue MANAGE;
        } else {
          dropped.add(droppable);
          this.drop(droppable);
        }
        String decision = "";
        validInput = false;
        while (!validInput) {
          System.out.println("Are you done? Y/N");
          decision = reader.nextLine().toLowerCase();
          if (decision.equals("yes") || decision.equals("no") || decision.equals("y") || decision.equals("n")) {
            validInput = true;
          }
        }
        if (decision.equals("no") || decision.equals("n")) {
          validInput = false;
          continue MANAGE;
        } else {
          running = false;
        }
      }
    }

    return dropped;
  }

  // The number of Stimpaks the character has.
  // Will be changed later as more health items
  // are introduced into the game.
  int num_Stimpaks() {
    return inventory.numStimpaks();
  }

  // For grammatical purposes.
  void setGender(String g) {
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
      this.spouseName = "Richard";
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
  void use_Stimpak() {
    if (num_Stimpaks() > 0) {
      if (this.health == 100) {
        System.out.println("Health already full.... Stimpak wasted.\n");
        drop(inventory.Stimpak);
      } else {
        heal(inventory.Stimpak);
        drop(inventory.Stimpak);
      }
    } else {
      System.out.println("Sorry... no Stimpaks available. Good luck! You've got this!\n");
    }
  }

  // Null out value. RIP!
  void die() {
    this.health = -1;
  }
}
