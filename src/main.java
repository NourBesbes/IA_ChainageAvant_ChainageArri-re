import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		// Liste des rÃ©les 
		List<Regle> regles;
		regles = parsingFile();
		 
		List<String> baseDesFaits = new ArrayList<String>();
		baseDesFaits.add("rhizone");
		baseDesFaits.add("fleur");
		baseDesFaits.add("1-cotylédone");
		baseDesFaits.add("graine");
		System.out.println("Saisir le but à chercher : ");
	      Scanner sc = new Scanner(System.in);
	      String but=sc.next();
	      
	      System.out.println("Choisir le type de chainage: \n 1. Chainage arrière \n 2. Chainage avant");
	      Scanner s = new Scanner(System.in);
	      int indice=sc.nextInt();
	      switch (indice) {
		case 1:
			if(	chainage_arriere(baseDesFaits,regles,but))   {
				System.out.println("La base des faits finale est: \n"+baseDesFaits.toString());
				System.out.println("Le but'"+but+"' est atteint.");}
			break;

		case 2:
			if(chainage_avant(baseDesFaits, regles,but)) {
				
				System.out.println("Le but '"+but+"' est atteint.");
				System.out.println("La base des faits finale est: \n"+baseDesFaits.toString());}
						else 
							System.out.println("Le but n'existe pas dans la base des faits finale");				
			break;
		default:System.out.println("Saisir un nombre valide.");
			break;
		}
	      


				}

		
	


	public static Boolean chainage_arriere(List<String> baseDesFaits, List<Regle> regles, String but)
	{	boolean butAtteint = false;	 
	boolean verif=false;;	
	Regle r = new Regle();
		if (baseDesFaits.contains(but.trim())) {
			System.out.print("Le fait '"+but+"' existe déjà dans la base des faits \n");
			butAtteint=true; return (butAtteint);
		}
		else {	
			verif=true;
			List<Regle> BRF =butInRegle(regles,but);
			if (BRF.isEmpty()) {
				System.out.println("Le '"+but+"' est impossible a prouver!!"); return false;}
			while(verif && !(BRF.isEmpty())){
				  verif = true ;
	                r = BRF.get(0);
	                System.out.println("Le but '"+but+"' sera cherché par la regle "+r.getId() );
	                BRF.remove(r);
	                for(int i=0 ; i<r.getPremisses().size() ; i++){
	      verif = (verif && chainage_arriere(baseDesFaits, regles, r.getPremisses().get(i).valeur));
	                }}}
		
	    if(verif){ 
	    	butAtteint= true;
	        for(int i = 0 ;  i<r.getConclusions().size() ; i++){	  
            baseDesFaits.add(r.getConclusions().get(i).valeur);
	        }
	        System.out.println("Le but '"+but+"' est atteint par la regle "+r.getId() );
	        }
	  
	             return butAtteint;   }
	                
	public static List<Regle> butInRegle (List<Regle> regles, String but)
		 {List<Regle> r = new ArrayList<Regle> ();
		for(Regle regle:regles)
			{					
				for (Conclusion cl: regle.getConclusions())				
					{	if (cl.valeur.trim().equals(but.trim()))
						{r.add(regle);			
						}					
					}
		}	return r;}
	
	
	public static boolean chainage_avant (	List<String> baseDesFaits, List<Regle> regles,String but)
	{ boolean butAtteint = false;
			if (baseDesFaits.contains(but)){
				System.out.print("Le fait '"+but+"' existe déjà dans la base des faits \n");
				butAtteint=true; return (butAtteint);
			}
			else {		
			
			for(Regle regle:regles){			
				int nbPremissesDeclanche = 0 ;		
				for(Premisse pr : regle.getPremisses()) {
					if( baseDesFaits.contains(pr.valeur.trim())) {
						nbPremissesDeclanche++;
						}
					}	
				if ( nbPremissesDeclanche == regle.getPremisses().size()) 
				{
					for (Conclusion cl: regle.getConclusions())
						
					{String conc = cl.valeur.trim();
						baseDesFaits.add(conc);
					}
					//regle.setDeclanche(true);
					System.out.print("La regle '"+regle.getId()+"' est déclanchée \n");				
				}
				
				if (baseDesFaits.contains(but.trim()))
				{	
					butAtteint = true ;
				}
			}} return butAtteint;
				}
	
	
	public static List<Regle> parsingFile(){
		 // Parsing file 
		List<Regle> listDesRegles = new  ArrayList<Regle>();
		File text = new File("./connaissance.txt");
		try {
			Scanner sc = new Scanner(text);
			int i = 1;
			while (sc.hasNext()){
				String ligne = sc.nextLine();
				//System.out.println(ligne);
				Regle rg = new Regle(); 
				rg.setId(i);
				i++;
				String chaine = ligne.split(":")[1];
				String premisses = chaine.split("->")[0];
				String conclusions = chaine.split("->")[1];
				rg.setPremisses(premisses);
				rg.setConclusions(conclusions);
				
				listDesRegles.add(rg);
			
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listDesRegles;
		
	}
	
}
