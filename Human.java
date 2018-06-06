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

  String InventorySimplePrint() {
    return inventory.InventorySimplePrint();
  }

  void ShowInventory() {
    inventory.ShowInventory();
  }

  void setName(String n) {
    name = n;
  }
}
