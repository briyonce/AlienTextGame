// Base class for all enemy types
import java.util.Random;

public class Enemy extends Entity {
    public static void main(String[] args){}

    static int MAX_HEALTH = 300;
     // The maximum amount of damage ANY enemy can do

    public Enemy () {
      Random r = new Random();
      super.health = r.nextInt(MAX_HEALTH);
      super.maxDamage = r.nextInt(MAX_DAMAGE);
      super.name = "new";
    }

    public Enemy(String n){
      Random r = new Random();
      super.name = n;
      super.health = r.nextInt(MAX_HEALTH);
      super.maxDamage = r.nextInt(MAX_DAMAGE);
    }

    public Enemy (String n, int h) {
      Random r = new Random();
      super.name = n;
      super.health = h;
      super.maxDamage = r.nextInt(MAX_DAMAGE);
    }

    public Enemy (String n, int h, int d) {
      super.name = n;
      super.health = h;
      super.maxDamage = d;
    }

    // This occurs when the enemy's attack connects
    // with the target
    void landAttack() {
      if (this.name.equals("Facehugger")) {
        System.out.println("\"GAH!!!!\" You wail! The Facehugger slashes at you with its long tail!");
        System.out.println("It scurries away before you can retaliate.\n");
      }
    }

    // The enemy misses its attack
    void missAttack() {
      if (this.name.equals("Facehugger")) {
        System.out.println("The tiny abomination lunges to attack you but you back away just in time.\n");
        System.out.println("It scurries away before you can retaliate.\n");
        System.out.println("You've escaped your inevitable death for another brief moment.\n");
        System.out.println("Time to gamble yet again.\n");
      }
    }
}
