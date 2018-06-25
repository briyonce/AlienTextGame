public class Weapon extends Item {
  private int maxDamage = 50; //
  protected int range = 1;    // number of blocks the bullet can travel
  protected int fireRate = 1; //
  private boolean operable = true;

  public Weapon () {}

  public Weapon (String n) {
    super(n);
  }
  
  public Weapon (String n, int w) {
    super(n, w);
  }

  boolean isOperable() {
    return this.operable;
  }

  void attack () {
    System.out.println("Attacking with " + super.getName());
  }
}
