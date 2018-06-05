import java.util.ArrayList;

public class Party{
  public static void main(String[] args) {};

  private ArrayList<Human> members = new ArrayList<Human>();
  private int partySize;

  public Party () {
    partySize = 0;
  }

  public Party (ArrayList<Human> p) {
    members = p;
  }

  void addMember(Human h) {
    members.add(h);
  }

  void printParty() {
    for (Human h : members) {
      System.out.println(h.getName() + ": " + h.getHealth() + " - " + h.InventorySimplePrint());
    }
  }
}
