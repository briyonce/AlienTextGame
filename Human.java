import java.util.Random;


public class Human {
  public static void main(String[] args){}

  static int MAX_HEALTH = 100;
  static int MAX_DAMAGE = 50;

  protected int health = MAX_HEALTH;
  private int maxDamage;
  protected String name = "human";
  private String gender = "he";
  private String possesive = "his";
  private boolean isPlayer = false;
  private Random r = new Random();
  private Inventory inventory = new Inventory();
  private int STARTING_STIMPAKS = 4;

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

  void acquire(String item, boolean initial) {
    inventory.acquire(item);
  }

  void acquire(String item) {
    boolean success = inventory.acquire(item);
    if (!success) {
      System.out.println("INVENTORY FULL: " + inventory.getUsage() + ": " + new Item(item).getWeight());
      System.out.println(this.name + " needs to drop something.");
    } else {
      if (this.isPlayer) {
        System.out.println("You pick up the " + item);
      } else {
        System.out.println(this.name + " picks up the " + item);
      }
    }
  }

  void drop(String item) {
    boolean success = inventory.drop(item);
    if (!success) {
      System.out.println(this.name + " didn't even have this to begin with...");
    }
  }

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

  void heal(Item i) {
    this.health += i.heal();
    System.out.print("Ahhhh.... that feels better.");
    if (this.health > 100) {
      this.health = 100;
    }
    System.out.println(" Health: " + this.health);
  }

  String InventorySimplePrint() {
    return inventory.InventorySimplePrint();
  }

  void ShowInventory() {
    inventory.ShowInventory();
  }

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

  void loseHealth(int d) {
    this.health -= d;
  }

  void give (Item i, Human h) {
    boolean hasItem = inventory.drop(i.getName());
    if (hasItem) {
      System.out.println("You give " + h.getName() + " the " + i.getName() + ".");
      h.acquire(i.getName());
    } else {

    }
  }

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
  void die() {
    this.health = -1;
  }
}
