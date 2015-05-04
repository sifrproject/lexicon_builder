import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;


public class Statistique {
	static Logger logRoot = Logger.getRootLogger();

	public static void main(String[] args) throws IOException {
		
		String dp = "E:\\lab1" ; 
		

		JsonFactory jfactory = new JsonFactory();
		/*** write to file ***/
		JsonGenerator jGenerator = jfactory.createJsonGenerator(new File(
				"e:\\flare.json"), JsonEncoding.UTF8);
		jGenerator.writeStartObject(); // {
		jGenerator.writeStringField("name", "SIFR");
		jGenerator.writeFieldName("children"); // "children" :
		jGenerator.writeStartArray(); // [
		
		String [] f = filesname(dp);
		if (f != null) {
	
			for (String ontologie : f) {
		
				CSVReader reader = new CSVReader(new FileReader(dp+"\\"+ontologie), ';' );
				ontologie= ontologie.replace(".csv", "");
				logRoot.info("Les statistiques pour L'ontologie  " + ontologie);
				
				jGenerator.writeStartObject(); // {
				jGenerator.writeStringField("name", ontologie);
				jGenerator.writeFieldName("children"); // "children" :
				jGenerator.writeStartArray(); // [
				
				String[] nextLine ;
				
				  HashMap<Integer, Integer> stat = new HashMap<Integer, Integer>();
				  
				  //initialisation 
				  for (int i = 33; i < 192; i++) {
					  if ((i >= 33 & i< 48) || (i >= 58 & i< 64) || (i >= 91 & i< 97)||(i >= 123 & i < 127)|| (i >= 161 & i < 192)) {
						  
						  stat.put(i, 0);
						  
					  } 
					  
					  
				  }
				  
				  //Read CSV line by line and use the string array as you want
				
				while ((nextLine = reader.readNext()) != null ) {
					
					String t =nextLine[0].trim() ;
					
					for (int i = 33; i < 192; i++) {
						   if ((i >= 33 & i< 48) || (i >= 58 & i< 64) || (i >= 91 & i< 97)||(i >= 123 & i < 127)|| (i >= 161 & i < 192)){
						
						String c = Character.toString((char) i) ;
						
						
						Integer k = stat.get(i) + StringUtils.countMatches(t,  c);
						stat.put(i, k) ;
						
							 
							   }
					}
					
					}
				
				
				reader.close();
				
				// je boucle sur la hashmap
				 for (int i = 33; i < 192; i++) {
					 if ((i >= 33 & i< 48) || (i >= 58 & i< 64) || (i >= 91 & i< 97)||(i >= 123 & i < 127)|| (i >= 161 & i < 192)){
					  
					 String c = Character.toString((char) i) ;
						
						
					 logRoot.info("Le caracte : " +  c + "   apparait " + stat.get(i) );
					 
					 jGenerator.writeStartObject(); // {
					 jGenerator.writeStringField("name", c +":"+ stat.get(i)  ); 
					 jGenerator.writeNumberField("size", stat.get(i));
					 jGenerator.writeEndObject(); // }
						
						
						
					 }
				  }
					
				 jGenerator.writeEndArray(); // ]
				 
					jGenerator.writeEndObject(); // }
			
				 logRoot.info("--------------------------***************************--------------------------------- ");
				
				
						
					}
			
			jGenerator.writeEndArray(); // ]
			 
			jGenerator.writeEndObject(); // }
			jGenerator.close();
			
			
			  }
		
					}
			
		
		
		
		
		
		
		
		

	
// get a directory's files

@SuppressWarnings("null")
private static  String [] filesname (String directoryPath)  {
	
	String [] f_names = null ;
		
File directory = new File(directoryPath);
if(!directory.exists()){
	System.out.println("Le fichier/répertoire '"+directoryPath+"' n'existe pas");
}else if(!directory.isDirectory()){
	System.out.println("Le chemin '"+directoryPath+"' correspond à un fichier et non à un répertoire");
}else{
	File[] subfiles = directory.listFiles();
	
	
	 f_names  = new String [subfiles.length]; 
	
	for(int i=0 ; i<subfiles.length; i++){
		
		//System.out.println(subfiles[i].getName());
		
		f_names[i] = subfiles[i].getName();
		
	}
	
	
	 
}
return f_names ;
	
}
}