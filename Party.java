import java.util.ArrayList;

public class Party{
  public static void main(String[] args) {};

  private ArrayList<Human> members = new ArrayList<Human>();
  private int partySize;

  public Party () {
    partySize = 0;
  }

  public Party (Human h) {
    members.add(h);
  }

  public Party (ArrayList<Human> p) {
    members = p;
  }

  void addMember(Human h) {
    members.add(h);
  }

  void removeMember(Human h) {
    members.remove(h);
  }

  int numMembers() {
    return members.size();
  }

  void printParty() {
    for (Human h : members) {
      if (h.isAlive())
        System.out.println(h.getName() + ": " + h.getHealth() + " - " + h.InventorySimplePrint());
    }
  }

  int size() {
    return this.partySize;
  }
}
