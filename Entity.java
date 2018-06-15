import java.util.Random;

public class Entity {
  public static void main(String [] args) {}
  protected String name = "entity";
  protected int health = 100;
  protected int maxDamage = 50;
  static int MAX_DAMAGE = 50;

  public Entity() {}

  String getName() {
    return this.name;
  }

  int getHealth() {
    return this.health;
  }

  boolean isAlive() {
    return this.health > 0;
  }

  void takeDamage(int d) {
    this.health -= d;
  }

  // Returns a value for the amount of damage
  // dealt to the target
  int attack() {
    Random r = new Random();
    return r.nextInt(maxDamage);
  }

  void setName(String n) {
    this.name = n;
  }

}
