import org.apache.commons.lang3.StringUtils;

import java.util.regex.*;

public class Rules {
	
	
	
	
	public static  String remove_2_quote (String str) {
		
		
		
		return str.replace('"', ' ') ;
	}
	
public static  String remove_2_quote (String str,char c , char c1 ) {
		
		
		
		return str.replace(c, c1) ;
	}
	
	
	public static Integer count_parenthesis(String str) {
		Pattern pattern = Pattern.compile("\\(|\\)");
		
		Matcher matcher = pattern.matcher(str);
	    //.find() checks for all occurran
	    Integer count = 0;
	    
	    while (matcher.find()) {
	      count++;
	    }
		
		return count ;
	}
	
	public static String deaccent(String s)
	{
		s = s.replaceAll("[\u00E8\u00E9\u00EA\u00EB]", "e");
		s = s.replaceAll("[\u00FA\u00FB\u00FC\u00F9]", "u");
		s = s.replaceAll("[\u00EC\u00ED\u00EE\u00EF]", "i");
		s = s.replaceAll("[\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5]", "a");
		s = s.replaceAll("[\u00F2\u00F3\u00F4\u00F5\u00F6]", "o");
		s = s.replaceAll("\u00E7", "c");

		s = s.replaceAll("[\u00C8\u00C9\u00CA\u00CB]", "E");
		s = s.replaceAll("[\u00DA\u00DB\u00DC\u00D9]", "U");
		s = s.replaceAll("[\u00CC\u00CD\u00CE\u00CF]", "I");
		s = s.replaceAll("[\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5]", "A");
		s = s.replaceAll("[\u00D2\u00D3\u00D4\u00D5\u00D6]", "O");

		return s;
	}
	
	public static String remove_underscore (String s){

		
		
		s= s.replace("_", " ") ;
		
		return s;
		
	}
	
	public static String remove_code_a_point (String str){
		
		if (str.indexOf(".") < 5){
			
			String [] s = str.split(".");
			
			return s[1] ;
		}
		
		
		return str;
	} 
	
	 public static String clean(String toClean)
		{
			if(toClean == null) return null;
			toClean = toClean.replaceAll("<[^>]+>", "");
			//toClean = toClean.replaceAll("<(^>)+>", "");
			toClean = toClean.replaceAll("[^a-zA-Z\0-9\u00E8\u00E9\u00EA\u00EB\u00FA\u00FB\u00FC\u00F9\u00EC\u00ED\u00EE\u00EF\u00E0\u00E1\u00E2\u00E3\u00E4\u00E5\u00F2\u00F3\u00F4\u00F5\u00F6\u00E7\u00C8\u00C9\u00CA\u00CB\u00DA\u00DB\u00DC\u00D9\u00CC\u00CD\u00CE\u00CF\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00D2\u00D3\u00D4\u00D5\u00D6]", " ");
			return toClean;
		}
	
	
	public static String remove_code (String s){

		
		
		String c[] = s.split("_-_");
		s = c[1] ;
		
		return s; 
		
	}
	
	public static String [] quash_slash(String s) {
		
		
		String ss[] = s.split("/");
		
		
		//Strips whitespace from the start and end of every String in an array.
		
		ss= StringUtils.stripAll(ss);
				
				
		return ss ;
		
		
	}
	

	 //suppression de caractere non alpha numerique 
    
    public static String nettoyageAlphaNumerique( String value)
	{
		String retour = new String();
		for (int i=0;i<value.length();i++)
		{
			if  ( ((value.charAt(i) >= 'a') && (value.charAt(i) <= 'z')) || ((value.charAt(i) >= 'A') && (value.charAt(i) <= 'Z')) ) retour += value.charAt(i);
		}
		return retour;
	}
	
    
    
		
		
	

}
