import java.util.*;

public class AddressBook {
	public ArrayList<Buddyinfo> bl;
	
	public AddressBook(){
		bl = new ArrayList<Buddyinfo>();
	}
	
	public void main (String[] args){
		System.out.println("AddressBook");
		Buddyinfo b = new Buddyinfo();
		AddressBook ab = new AddressBook();
		ab.addBuddy(b);
		ab.removeBuddy(b);
	}
	
	public void addBuddy(Buddyinfo b){
		bl.add(b);
	}
	
	public Buddyinfo getBuddy(String name){
		for (Buddyinfo b : bl){
			System.out.println(b.getName() + name);
			if (b.getName() == name) return b;
		}
		return null;
	}
	
	public String[] getAllBuddies(){
		String[] all = new String[bl.size()];
		int i = 0;
		for(Buddyinfo b : bl){
			all[i] = b.getName();
			i++;
		}
		return all;
	}
	
	public void removeBuddy(Buddyinfo b){
		bl.remove(b);
	}
}
