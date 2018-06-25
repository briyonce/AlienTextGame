//Item class. Each Item will have its own special attributes

public class Item {
  public static void main(String[] args) {}

  private String name = "object";
  private String color = "grey";
  protected int weight = 5;      // Used for inventory mechanic. YOU GOTTA CHOOSE!
  boolean regenerative = false; // Used for health/healing objects
  private int regenAmount = 0; // The amount of health regenerated from a Stimpak
  private int quantity = 1;   // Quantity. The amount of THIS the player has.
  private int health = 10;    // Weapons are breakable
  private boolean breakable = false;

  public Item() {}

  public Item(String n) {
    this.name = n;
    if (n.toLowerCase().equals("Stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item(String n, String c) {
    this.name = n;
    this.color = c;
    if (n.toLowerCase().equals("Stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (String n, String c, int w) {
    this.name = n;
    this.color = c;
    this.weight = w;
    if (n.toLowerCase().equals("Stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (String n, int w) {
    this.name = n;
    this.weight = w;
    if (n.toLowerCase().equals("Stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  String getName() {
    return this.name;
  }

  int getWeight() {
    return this.weight;
  }

  String getColor() {
    return this.color;
  }

  int getHealth() {
    return this.health;
  }

  // Simple item description.
  void examine() {
    System.out.print("It's a " + this.name + ". It's " + this.color + " and it "
    + "weighs " + this.weight + ".");
    if (this.breakable)
      System.out.print( + this.health + " health remaining.");
    System.out.println();
  }

  // You got another one. How cool.
  void stack() {
    ++this.quantity;
  }

  // Another one bites the dust. Damn rip.
  void drop() {
    --this.quantity;
  }

  // How many of me are there???
  int getQuantity() {
    return this.quantity;
  }

  // You may or may not. Depends.
  int heal() {
    return this.regenAmount;
  }

  // Can I heeeaals ya? Heelys? No? I tried.
  boolean isRegenerative() {
    return this.regenerative;
  }
}
