import java.util.ArrayList;
import java.util.List;

public class Regle {
	int id ;
	List<Premisse> premisses  = new ArrayList<Premisse>();
	List<Conclusion> conclusions = new ArrayList<Conclusion>() ;

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Premisse> getPremisses() {
		return premisses;
	}
	public void setPremisses(List<Premisse> premisses) {
		this.premisses = premisses;
	}
	public List<Conclusion> getConclusions() {
		return conclusions;
	}
	public void setConclusions(List<Conclusion> conclusions) {
		this.conclusions = conclusions;
	}
	
	public void setPremisses(String chaine){
		int i ; 
		for (i = 0 ; i < chaine.split("ET").length ; i++) {
			Premisse pr  = new Premisse();
			pr.setValeur(chaine.split("ET")[i]);
		
			premisses.add(pr);
		}
		
	}
	
	public void setConclusions(String chaine){
		int i ; 
		for (i = 0 ; i < chaine.split("ET").length ; i++) {
			Conclusion cl  = new Conclusion();
			cl.setValeur(chaine.split("ET")[i]);
			conclusions.add(cl);
		}
 
	}
	@Override
	public String toString() {
		System.out.println("No Regle = "+id);
		for(Premisse pr : premisses){
			System.out.print(pr);
		}
		for(Conclusion cl: conclusions){
			System.out.print(cl);
		}
		return "";
	}

	
	
	
	
}
