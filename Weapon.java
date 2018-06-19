public class Weapon extends Item {
  private int maxDamage = 50; //
  protected int range = 1;    // number of blocks the bullet can travel
  protected int fireRate = 1; //
  private boolean operable = true;
  protected String name = "weapon";

  public Weapon () {}

  boolean isOperable() {
    return this.operable;
  }

  String getName () {
    return this.name;
  }

  void attack () {
    System.out.println("Attacking with " + this.name);
  }
}
