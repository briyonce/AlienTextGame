// Base class for all enemy types
import java.util.Random;

public class Enemy {
    public static void main(String[] args){}

    static int MAX_HEALTH = 300;
    static int MAX_DAMAGE = 50;

    protected int health;
    private int maxDamage;
    protected String name;



    public Enemy () {
      Random r = new Random();
      this.health = r.nextInt(MAX_HEALTH);
      this.maxDamage = r.nextInt(MAX_DAMAGE);
      this.name = "new";
    }

    public Enemy(String n){
      Random r = new Random();
      this.name = n;
      this.health = r.nextInt(MAX_HEALTH);
      this.maxDamage = r.nextInt(MAX_DAMAGE);
    }

    public Enemy (String n, int h) {
      Random r = new Random();
      this.name = n;
      this.health = h;
      this.maxDamage = r.nextInt(MAX_DAMAGE);
    }

    public Enemy (String n, int h, int d) {
      this.name = n;
      this.health = h;
      this.maxDamage = d;
    }

    int attack() {
      Random r = new Random();
      return r.nextInt(maxDamage);
    }

    void landAttack() {
      if (this.name.equals("Facehugger")) {
        System.out.println("\"GAH!!!!\" You wail! The Facehugger slashes at you with its long tail!");
        System.out.println("It scurries away before you can retaliate.\n");
      }
    }

    void missAttack() {
      if (this.name.equals("Facehugger")) {
        System.out.println("The tiny abomination lunges to attack you but you back away just in time.");
        System.out.println("It scurries away before you can retaliate.\n");
        System.out.println("You've escaped your inevitable death for another brief moment.");
        System.out.println("Time to gamble yet again.");
      }
    }

    String getName() {
      return this.name;
    }

    int getHealth() {
      return this.health;
    }

    void takeDamage(int d) {
      health -= d;
    }

    protected void alterName(String n) {
      name = n;
    }
}
