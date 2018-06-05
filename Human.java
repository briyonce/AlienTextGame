import java.util.Random;
import java.util.ArrayList;

public class Human {
  public static void main(String[] args){}

  static int MAX_HEALTH = 100;
  static int MAX_DAMAGE = 50;

  protected int health;
  private int maxDamage;
  protected String name;

  public ArrayList<String> inventory = new ArrayList<String>();

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
    inventory.add(item);
  }

  String InventorySimplePrint() {
    if (inventory.size() == 0) {
      return "nothing";
    } else {
      StringBuilder sb = new StringBuilder();
      for (String item : inventory) {
        sb.append(item + " ");
      }
      return sb.toString();
    }
  }

  void ShowInventory() {
    if (inventory.size() == 0) {
      System.out.println("You have nothing.");
    } else if (inventory.size() == 1) {
      System.out.print("You have ");
      String curItem = inventory.get(0);
      if (isVowel(curItem.charAt(0))) {
        System.out.println("an " + curItem + ".");
      } else {
        System.out.println("a " + curItem + ".");
      }
    } else {
      System.out.print("You have ");
      for (int i = 0; i < inventory.size() - 1; ++i) {
        String curItem = inventory.get(i);
        if (isVowel(curItem.charAt(0))) {
          System.out.print("an " + curItem + ", ");
        } else {
          System.out.print("a " + curItem + ", ");
        }
      }
      String curItem = inventory.get(inventory.size() - 1);
      if (isVowel(curItem.charAt(0))) {
        System.out.println("and an " + curItem + ".");
      } else {
        System.out.println("and a " + curItem + ".");
      }
    }
  }

  boolean isVowel (char c) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    for (char v : vowels) {
      if (c == v) {
          return true;
      }
    }
    return false;
  }

  void setName(String n) {
    name = n;
  }
}
