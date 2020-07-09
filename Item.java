//Item class. Each Item will have its own special attributes

public class Item implements Cloneable {
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
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item(String n, String c) {
    this.name = n;
    this.color = c;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (String n, String c, int w) {
    this.name = n;
    this.color = c;
    this.weight = w;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (String n, int w) {
    this.name = n;
    this.weight = w;
    if (n.toLowerCase().equals("stimpak")) {
      this.regenerative = true;
      this.regenAmount = 40;
      this.weight = 1;
    }
  }

  public Item (Item i) {
    this.name  = new String(i.name);
    this.color = new String(i.color);
    this.regenerative = i.regenerative;
    this.regenAmount = i.regenAmount;
    this.weight = i.weight;
    this.quantity = i.quantity;
    this.health = i.health;
    this.breakable = i.breakable;
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
    if (this.name.toLowerCase().equals("stimpak")) {
      System.out.print("incrementing stimpak: \n\n");
    }
    System.out.print(this.quantity + " to ");
    ++this.quantity;
    System.out.println(this.quantity);
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
