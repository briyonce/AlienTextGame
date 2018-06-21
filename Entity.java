import java.util.Random;

public class Entity {
  public static void main(String [] args) {}
  protected String name = "entity";
  protected int health = 100;
  protected int maxDamage = 50;
  static int MAX_DAMAGE = 50;
  private boolean isStunned = false;

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

  // This occurs when the enemy's attack connects
  // with the target
  void landAttack(Entity e) {
    Random r = new Random();
    int damage = r.nextInt(maxDamage);
    while (damage < 2) {
      damage = r.nextInt(maxDamage);
    }
    if (damage > e.getHealth()) {
      damage = e.getHealth();
    }
    if (this instanceof Enemy) {
      if (e instanceof Human) { // Enemy to Human
        Human h = (Human) e;
        if (this.name.equals("Facehugger")) {
          String noun = "";
          if (h.isPlayer()) {
            noun = "you ";
          } else {
            noun = e.getName();
          }
          System.out.println("\"GAH!!!!\" The Facehugger slashes at " + noun + "with its long tail!");
          System.out.println("It scurries away before " + noun + " can retaliate.\n");
        }
        if (h.isPlayer()) {
          System.out.println("You take " + damage + " damage!\n");
        } else {
          System.out.println(this.name + " takes " + damage + " damage!\n");
        }
      }
    } else { // Human to Enemy
      String[] human_attack_sounds = {"You lunge for the " + e.getName() + "! Slash! Bam!",
        "Your bare fists meet the flesh of your enemy... It squeals in pain and scurries away before you can deal any more damage."};
      int atk_choice = r.nextInt(human_attack_sounds.length);
      System.out.println(human_attack_sounds[atk_choice] +"\n");
      Human h = (Human) this;
      if (h.isPlayer()) {
        System.out.print("You ");
      } else {
        System.out.print(this.getName() + " ");
      }
      System.out.println(" dealt " + damage + " damage to the horrid beast. \n");
    }
    e.takeDamage(damage);
  }

  // Returns a value for the amount of damage
  // dealt to the target
  void attack(Entity e) {
    if (!this.isStunned()) {
      Random r = new Random();
      int luck = r.nextInt(100);

      if (this instanceof Enemy) { // enemy to human
        if (luck > 30) {
          this.landAttack(e);
          if (luck > 90) {
            if (e instanceof Human) {
              Human h = (Human) e;
              if (h.isPlayer()) {
                System.out.println("You have become stunned. Get your guard up.\n");
              } else {
                System.out.println(this.name + " has become stunned. It's looking serious.\n");
              }
            }
            e.stun();
          }
        } else {
          Enemy en = (Enemy) this;
          en.missAttack();
        }
      } else { // human to enemy
        if (luck > 25) {
          this.landAttack(e);
          if (luck > 80) {
            System.out.println("The " + e.getName() + " has become stunned.");
            e.stun();
          }
        } else {
          Human h = (Human) this;
          if (h.isPlayer()) {
            System.out.println("You missed your attack. Do better next time. If you get one. \n");
          } else {
            System.out.println(this.getName() + " missed their attack, allowing your team to suffer furthermore.\n");
          }
        }
      }
    } else { // Used for the stun mechanic
      if (this instanceof Human) {
        Human h = (Human) this;
        if (h.isPlayer()) {
          System.out.println("You are stunned. Sorry.\n");
        } else {
          System.out.println(this.getName() + " is stunned for this turn.\n");
        }
      } else { // Enemy in question
        System.out.println("The " + this.getName() + " is frozen where it stands. Your attack really did a number on it. Good for you.\n");
      }
      this.unStun();
    }
  }

  // Pretty self explanitory
  void setName(String n) {
    this.name = n;
  }

  // Used for combat
  boolean isStunned() {
    return this.isStunned;
  }

  // When an Human/Enemy becomes stunned due to an attack.
  void stun() {
    this.isStunned = true;
  }

  // Entities return to normal after 1 turn.
  void unStun() {
    this.isStunned = false;
  }

}
