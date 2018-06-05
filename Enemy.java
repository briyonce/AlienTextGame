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

    String getAttack() {
      return "";
    }

    String getName() {
      return this.name;
    }

    int getHealth() {
      return this.health;
    }
}
