import java.util.Random;


public class Human {
  public static void main(String[] args){}

  static int MAX_HEALTH = 100;
  static int MAX_DAMAGE = 50;

  protected int health;
  private int maxDamage;
  protected String name;

  private Inventory inventory = new Inventory();

  public Human () {
    Random r = new Random();
    name = "human";
    health = MAX_HEALTH;
    maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (String n) {
    Random r = new Random();
    name = n;
    health = MAX_HEALTH;
    maxDamage = r.nextInt(MAX_DAMAGE);
  }

  public Human (int h) {
    Random r = new Random();
    name = "human";
    health = h;
    maxDamage = r.nextInt(MAX_DAMAGE);
  }

  String getName() {
    return name;
  }

  int getHealth() {
    return health;
  }

  boolean isAlive() {
    return health > 0;
  }

  void acquire(String item) {
    inventory.acquire(item);
  }

  int attack(String enemy) {
    String[] attack_sounds = {"You lunge for the " + enemy + "! Slash! Bam!",
                              "Your bare fists meet the flesh of your enemy... It squeals in pain and scurries away before you can deal any more damage."};
    Random r = new Random();
    return r.nextInt(maxDamage);
    int atk_choice = rand.nextInt(attack_sounds.length);
    System.out.println(attack_sounds[atk_choice] +"\n");
  }

  String InventorySimplePrint() {
    return inventory.InventorySimplePrint();
  }

  void ShowInventory() {
    inventory.ShowInventory();
  }

  void setName(String n) {
    name = n;
  }

  void loseHealth(int d) {
    this.health -= d;
  }
}
