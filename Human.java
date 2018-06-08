import java.util.Random;


public class Human {
  public static void main(String[] args){}

  static int MAX_HEALTH = 100;
  static int MAX_DAMAGE = 50;

  protected int health = MAX_HEALTH;
  private int maxDamage;
  protected String name = "human";
  private String gender = "he";
  private boolean isPlayer = false;
  private Random r = new Random();
  private Inventory inventory = new Inventory();

  public Human () {
    maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (String g) {
    // Random r = new Random();
    this.gender = g;
    this.maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (boolean p) {
    this.isPlayer = true;
    this.maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (String n, String g) {
    // Random r = new Random();
    this.name = n;
    this.gender = g;
    this.maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (String n, String g, int h) {
    // Random r = new Random();
    this.name = n;
    this.health = h;
    this.gender = g;
    this.maxDamage = r.nextInt(MAX_DAMAGE);
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

  boolean isAlive() {
    return health > 0;
  }

  void acquire(String item) {
    boolean success = inventory.acquire(item);
    if (!success) {
      System.out.println("INVENTORY FULL: " + inventory.getUsage() + ": " + new Item(item).getWeight());
      System.out.println(this.name + " needs to drop something.");
    } else {

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
    System.out.println(maxDamage);
    return r.nextInt(maxDamage);
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
  void die() {
    System.out.println("You have died... Death comes and grasps you with its cold, bony claws...");
    System.out.println("...in another time... in another life...\n");
  }
}
