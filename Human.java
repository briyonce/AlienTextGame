// Base class for all humans in the game.
// There may one day be different tiers or classes of humans

import java.util.Random;

public class Human {
  public static void main(String[] args){}

  static int MAX_HEALTH = 100;
  static int MAX_DAMAGE = 50;
  static int MAX_COWARDICE = 100;   // I wonder what'll happen if they hit 100...

  protected int health = MAX_HEALTH;
  private int maxDamage;
  protected String name = "human";
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
    this.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String g) {
    // Random r = new Random();
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    this.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (boolean p) {
    for (int i = 0; i <= STARTING_STIMPAKS; ++i)
      acquire("stimpak", true);
    this.isPlayer = true;
    this.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g) {
    // Random r = new Random();
    this.name = n;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    this.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  public Human (String n, String g, int h) {
    // Random r = new Random();
    this.name = n;
    this.health = h;
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
    this.maxDamage = r.nextInt(MAX_DAMAGE - (MAX_DAMAGE / 2)) + (MAX_DAMAGE / 2);
  }

  String getName() {
    return this.name;
  }

  int getHealth() {
    return this.health;
  }

  String getGender() {
    return this.gender;
  }

  String getPossessive() {
    return this.possesive;
  }

  boolean isAlive() {
    return this.health > 0;
  }

  boolean isPlayer() {
    return this.isPlayer;
  }

  boolean hasFlashlight() {
    return this.hasFlashlight;
  }

  void acquire(String item, boolean initial) {
    inventory.acquire(item);
  }

  // Add an item to your inventory
  boolean acquire(String item) {
    boolean success = inventory.acquire(item);
    if (!success) {
      System.out.println("INVENTORY FULL: " + inventory.getUsage() + ": " + new Item(item).getWeight());
      System.out.println(this.name + " needs to drop something.");
      return false;
    } else {
      if (this.isPlayer) {
        System.out.println("You pick up the " + item);
      } else {
        System.out.println(this.name + " picks up the " + item);
      }
      if (item.toLowerCase().equals("flashlight")) {
        this.hasFlashlight = true;
      }
      return true;
    }
  }

  // Remove an item from your inventory
  void drop(String item) {
    boolean success = inventory.drop(item);
    if (success) {
      this.hasFlashlight = false;
    } else {
      System.out.println(this.name + " didn't even have this to begin with...");
    }
  }

  // Returns a value for the amount of damage
  // dealt to the enemy
  int attack(String enemy) {
    String[] attack_sounds = {"You lunge for the " + enemy + "! Slash! Bam!",
                              "Your bare fists meet the flesh of your enemy... It squeals in pain and scurries away before you can deal any more damage."};
    int atk_choice = r.nextInt(attack_sounds.length);
    System.out.println(attack_sounds[atk_choice] +"\n");
    int damage = r.nextInt(this.maxDamage);
    while (damage < 2) {
      damage = r.nextInt(maxDamage);
    }
    return damage;
  }

  // Heal yo' self
  void heal(Item i) {
    this.health += i.heal();
    System.out.print("Ahhhh.... that feels better.");
    if (this.health > 100) {
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

  void setName(String n) {
    this.name = n;
  }

  void setGender(String g) {
    this.gender = g;
    if (g.equals("she")) {
      this.possesive = "her";
    }
  }

  // The character has taken damage.
  void loseHealth(int d) {
    this.health -= d;
  }

  // Move an item from this character's inventory
  // to another character's inventory
  void give (Item i, Human h) {
    boolean hasItem = inventory.drop(i.getName());
    if (hasItem) {
      System.out.println("You give " + h.getName() + " the " + i.getName() + ".");
      h.acquire(i.getName());
    } else {
      System.out.println("You don't even have that item to give...");
    }
  }

  // Used for the fututre cowardice/karma mechanic
  void incrementCowardice() {
    cowardice += 10;
  }

  // Used in conjunction with heal()... I think.
  void use_stimpak() {
    if (num_stimpaks() > 0) {
      if (this.health == 100) {
        System.out.println("Health already full.... Stimpak wasted.");
        drop("stimpak");
      } else {
        heal(new Item("stimpak"));
        drop("stimpak");
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
