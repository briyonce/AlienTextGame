//Item class. Each Item will have its own special attributes

public class Item {
  public static void main(String[] args) {}

  String name;
  String color;
  int weight;

  public Item() {
    this.name = "object";
    this.color = "grey";
    this.weight = 5;
  }

  public Item(String n) {
    this.name = n;
    this.color = "grey";
    this.weight = 5;
  }

  public Item(String n, String c) {
    this.name = n;
    this.color = c;
    this.weight = 5;
  }

  public Item (String n, String c, int w) {
    this.name = n;
    this.color = c;
    this.weight = w;
  }

  String getName() {
    return this.name;
  }
}
