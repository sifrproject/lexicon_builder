import java.util.ArrayList;
import java.util.List;


public class Terme {

	private String ontologie;
	private String preflabel ;
	List<String> ids = new ArrayList<String>() ;
	
	List<String> synonymes = new ArrayList<String>() ;
	
	public Terme (){
	
	}
	
	public String getOntologie() {
		return ontologie;
	}
	public void setOntologie(String ontologie) {
		this.ontologie = ontologie;
	}
	public String getPreflabel() {
		return preflabel;
	}
	public void setPreflabel(String preflabel) {
		this.preflabel = preflabel;
	}
	public List<String > getIds() {
		return ids;
	}
	public void addIds(String id) {
		ids.add(id);
	}
	public List<String >  getSynonymes() {
		return synonymes;
	}
	public void addSynonymes(String synonyme) {
		synonymes.add(synonyme);
	}
	

}
