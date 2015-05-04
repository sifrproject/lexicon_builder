import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.*;


public class Merge {
	 //Delimiter used in CSV file
 	//private static final String COMMA_DELIMITER = ",";
 	private static final String NEW_LINE_SEPARATOR = "\n";
	
	 static HashMap<String , Terme_r>Termes = new HashMap<String, Terme_r>();
	
	public static void main(String[] args) throws IOException {
	
	//Build reader instance
    //Read data.csv
    //Default seperator is comma
		
		
		Integer i = 0 ;
		
		Integer j = 0 ;
		String dp = "E:\\lab1" ; 
		
		
		String [] f = filesname(dp);
		
		
		if (f != null) {
			
			for (String ontologie : f) {
		
				CSVReader reader = new CSVReader(new FileReader(dp+"\\"+ontologie), ';' );
				
				
				ontologie= ontologie.replace(".csv", "");

				//Read CSV line by line and use the string array as you want
				String[] nextLine ;
				
				while ((nextLine = reader.readNext()) != null ) {
					
					String t =nextLine[0].trim() ;
					
					//t = t.replace("^", " ") ;
					//t= Rules.deaccent(t) ;
					
					//t= t.replace("\"" , " ") ;
					
					  if (Termes.containsKey(t) == true ){
						  
						  i = i +1 ;
						  
						  List<String> URIS = new ArrayList<String>();
						  try {
							   	for (String str : nextLine[1].split(",")) {
							       	
							       	URIS.add(str) ;
							       	
							      			}
							       
							       
							    
								} catch (Exception e) {
									// TODO: handle exception
									
								} 
							   
						   
						   
						   
						  Terme_r SS = Termes.get(t) ;
						 // System.out.println(ontologie.replace(".csv",""));
						   
						   
						  
						  SS.uris.put(ontologie, URIS );
						  
						 
						  
					   
					 
					   }else {
						   
						   j = j +1 ;
						   Terme_r st1 = new Terme_r();
						   
						   st1.setTerme(t);
						   List<String> URIS = new ArrayList<String>();
						   
						   try {
						   	for (String str : nextLine[1].split(",")) {
						       	
						       	URIS.add(str) ;
						       	
						      			}
						       
						       
						    
							} catch (Exception e) {
								// TODO: handle exception
								
							} 
						   
						   
						   
						   st1.uris.put(ontologie,  URIS ) ;
							
						   
						   Termes.put(t, st1) ; 
						
						  
						   
						  
					   }
					
			

				}

				reader.close();
				
						
					}
					}
			
		System.out.println(Termes.size() +  "i" + i + "j" + j);
		
		writecsv ();
			}
			
			
		
	private static void writecsv () throws IOException	 {
		
		
		 FileOutputStream fos = new FileOutputStream("E:\\BDico.csv");
	    	
    	 byte[] enc = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF }; 
    	 fos.write(enc);
    	 
    	 OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
		
    	 try {
				
				
    		 
    		
				  
				 // Structure2 stTT =Termes.get("périorbitaire"); 
				 // System.out.println("Hello genitale " + " les size"+stTT.uris.keySet().toString()); 
				  
				  
			
				//Add a new line separator after the header
				//writer.append(NEW_LINE_SEPARATOR);
				
				//System.out.println(Termes.size());
				
				Iterator<String> keySetIterator = Termes.keySet().iterator();
				System.out.println(Termes.keySet().size());
				
				
				while(keySetIterator.hasNext()){
					
					
					  String key = keySetIterator.next();
					  
					 
					  Terme_r st2 =Termes.get(key);
				
		        	 
					   Iterator<String> UkeySetIterator = st2.uris.keySet().iterator();
					   
					   
					   List<String> sources = new ArrayList<String>() ;
					   
					   List<String> uris = new ArrayList<String>();
					   
					   
					   while(UkeySetIterator.hasNext()){
						   String Ukey = UkeySetIterator.next();
						   
						   
						
						   
						   
						
						   
						   
						  for (String str : st2.uris.get(Ukey) ){
							   
							   
							   uris.add(str);
							   //System.out.println(Ukey.replace(".csv", "")); 
							  sources.add(Ukey.replace(".csv", ""))  ;
							  
							 
							  
						}
						  
						  
					   }
					   
					   
					   
					  
					  
					   
					   
					   writer.append(String.valueOf(key  + ";" +StringUtils.join(uris,",") +";"+StringUtils.join(sources,",")  ));
					
					
					
					  writer.append(NEW_LINE_SEPARATOR);
					   
					  
					   
				}
				
				
				
				
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					writer.flush();;
					writer.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}
	
		/*	logRoot.info("CSV file was created successfully !!!" + oname);
			long duree = System.nanoTime() - start;
			double seconds = (double)duree / 1000000000.0;
			logRoot.info("Temps pour  recuperer l'ontologie : " +  oname + " est de "+ seconds);*/
		
	}
		
		
		
		
		
		
		

		
	
    
		
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
			//String message = "Le répertoire '"+directoryPath+"' contient "+ subfiles.length+" fichier"+(subfiles.length>1?"s":"");
			//System.out.println(message);
			
			
			
			 f_names  = new String [subfiles.length]; 
			
			for(int i=0 ; i<subfiles.length; i++){
				
				//System.out.println(subfiles[i].getName());
				
				f_names[i] = subfiles[i].getName();
				
			}
			
			
			 
		}
		return f_names ;
			
		}
				
    
		
		
		
		
		
		
		
		
    
     
   
    
}
