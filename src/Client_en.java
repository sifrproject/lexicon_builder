
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.opencsv.CSVWriter;

import java.util.List;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Client_en {

    static final String REST_URL = "http://data.bioontology.org/ontologies";
    static final String API_KEY = "0651c2ef-1193-436d-95c1-09901daa5b79";
    static final ObjectMapper mapper = new ObjectMapper();
    static HashMap<String , Terme>Termes = new HashMap<String, Terme>();
    static Logger logRoot = Logger.getLogger(Client_en.class);
   
   
    static int nombre = 0 ;
   
    static long start;
    static long starta;
   
   
 //Delimiter used in CSV file
 //	private static final String COMMA_DELIMITER = ",";
 	private static final String NEW_LINE_SEPARATOR = "\n";
 	

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        
    	
    	starta = System.nanoTime();
    	 

    	

    	// Get the available resources
        String resourcesString = get(REST_URL );
        JsonNode resources = jsonToNode(resourcesString);
        
         List<String> ontologies = new ArrayList<String>();  
         
     System.out.println(resources.size());  
         try {
        	 
        	// Iterate looking for ontology in SIFR group 
        	 
        	 for (JsonNode jsonNode : resources) {
        			
        			
        				ontologies.add(jsonNode.findValue("@id").asText());
        				
        			}
        		
			
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
         

         //  
         logRoot.info("Le nombre d'ontologies: " + ontologies.size());
         
   
        
        
         try {
        	 
        	 String[] stf = null ;
        	 
        	 
			 
        	 for (int u = 0; u <= ontologies.size(); u++) 
        		 
        		 
        	 {
        		 String url = ontologies.get(u) ;
        		 
        		 stf = ontologies.get(u).split("/") ;
        		 
        		 System.out.println(stf[stf.length-1]);
        		 
        		 logRoot.info("###############################*********|" + u + "|*****#######################################") ;
        		 logRoot.info(url);
				
        		 start = System.nanoTime();
   			  
   			 
   				 
   				 
   				 String cc =get( url+"/classes?page=1&pagesize=100&include_context=false&include_links=false");
   		       // System.out.println(cc);
   				 
   		    // logRoot.info(cc);
   		        
   		        JsonNode rsc = jsonToNode(cc);
   		        
   		        if(rsc!=null){
   		        	     Integer pagecount = rsc.get("pageCount").asInt();
   		      
   			  logRoot.info("le nombre de page  : " +pagecount);
   		        for (int i = 1; i < pagecount/2; i++) {
   		        	
   		        	try {
   		        		
   		        		
   		        	  // logRoot.info("la page: " +i);
   	   		        	
   	   		        	
   	   		        	String resourcesString1 = get(url+"/classes?page="+i+"&pagesize=100&include_context=false&include_links=false");
   	   		        	
   	   		        	//String resourcesString1 = get(url+"/classes?page="+i);
   	   		        	
   	   		        
   	   		        	
   	   		        	JsonNode resources1 = jsonToNode(resourcesString1);
   	                
   	                
   	                if (resources1 != null){
   	                	
   	                	
						Iterator<JsonNode> link1 = resources1.get("collection").elements();
   	   	            	
   	   	                
   	   		        	while(link1.hasNext()){
   	               	 
   	               	 
   	   		        		JsonNode jsnd =link1.next() ; 
   	               	   
   	   		        		charger(jsnd);
   	                	
   	                	
   	                }
   	   		
   	   		        	//stf = resources1.get("collection").get(0).get("links").get("ontology").asText().split("/"); 
   	   		        	


   	   		        	//System.out.println(stf[stf.length-1]);
   	   		        
   	                }
						
					} catch (Exception e) {
						
						logRoot.info(e.getMessage());
						logRoot.info(e.getStackTrace().toString());
						System.out.println("|||||||"  );
						e.printStackTrace() ;
						// TODO: handle exception
					}
   				  
   				
   		        	
   		      
           }
   		       
   		     logRoot.info("le nombre de Termes " + Termes.size() + " l'ontologie " + url);
   		        
   		       // System.out.println(stf[stf.length-1]);
   		       writecsv(stf[stf.length-1]);   
   		       
   		       
   		       Termes.clear();
   		        	
   		        }
   		        
   		        else {
   		        	
   		         logRoot.info("******####******probleme avec : "+ url);
   		        }
   		        
   		   
   			 }
   		         
   			  
   			 
   			  
   		     
   	
		} catch (Exception e) {
			
			logRoot.info(e.getMessage());
			logRoot.info(e.getStackTrace().toString());
			
			e.printStackTrace();
		
			
			// TODO: handle exception
		}
         
			
         	long duree = System.nanoTime() - starta;
			double seconds = (double)duree / 1000000000.0;
			logRoot.info("Temps Total"+ seconds/60);  
		}
        

     private static void writecsv( String oname) throws IOException{
    
    	 nombre = 0 ;
		 
    	 FileOutputStream fos = new FileOutputStream(".\\Dicos\\"+ oname +".csv");
    	
    	 byte[] enc = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF }; 
    	 fos.write(enc);
    	 
    	 OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
    	 
			
			
				try {
					
				
					//Add a new line separator after the header
					//writer.append(NEW_LINE_SEPARATOR);
					
					//System.out.println(Termes.size());
					
					Iterator<String> keySetIterator = Termes.keySet().iterator();
					
					
					while(keySetIterator.hasNext()){
						  String key = keySetIterator.next();
						   Terme st =Termes.get(key);
					
			        	 
						// logRoot.info(st.getPreflabel()+"//"+st.getIds());

						   writer.append(String.valueOf(st.getPreflabel()  + ";" + StringUtils.join(st.getIds(),",")  ));
						//fileWriter.append(COMMA_DELIMITER);
						
						
						   writer.append(NEW_LINE_SEPARATOR);
						   
						   for (String syn : st.getSynonymes()) {
							   
							   writer.append(String.valueOf(syn  + ";" + StringUtils.join(st.getIds(),",")  ));
							   writer.append(NEW_LINE_SEPARATOR);
						}
						   
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
		
				logRoot.info("CSV file was created successfully !!!" + oname);
				long duree = System.nanoTime() - start;
				double seconds = (double)duree / 1000000000.0;
				logRoot.info("Le Temps pour  recuperer l'ontologie : " +  oname + " est de "+ seconds +" secondes");
     }
   
    
    
   
    
    private static  void charger (JsonNode j) {
    		
    			
			 Terme st = new Terme() ;
			 
			 
			 String t = j.get("prefLabel").asText() ;
			 /*
			 t = t.replace("^", " ") ;
		
			 t= t.replace("\"" ," ") ; 
			 
			 t= Rules.deaccent(t);
			 */
			 
			 t= t.replace('"', ' ') ; 
			 t= t.replace("\"" ," ") ; 
			 t = t.trim() ; 
			 
			// System.out.println(t);
    		 st.setPreflabel(t);
    		 st.addIds(j.get("@id").asText());
    		 
    
    		/* String[] stf = j.get("links").get("ontology").asText().split("/");
    		
    		 st.setOntologie(stf[stf.length-1]);
    		 */
    		 
    		 Iterator<JsonNode> li = j.get("synonym").elements();
         	
            
             while(li.hasNext()){
            	 
            	 
            	 JsonNode jsnd1 =li.next() ; 
            	 
            	 if (!jsnd1.asText().matches("C\\d*")){
            		 
            		 
            		 String tt = jsnd1.asText() ;
        			 /*
        			 tt = tt.replace("^", " ") ;
        			 
        			 tt= tt.replace("\"" ," ") ; 
        			
        			 tt= Rules.deaccent(tt);*/
            		 tt= tt.replace("\"" ," ") ; 
            		  tt= tt.replace('"', ' ') ; 
            		 tt = tt.trim() ; 
            		// System.out.println(tt);
            		 st.addSynonymes(StringUtils.normalizeSpace(tt));
            	 }
            	   
            	 
            
                 
             }
    		 
     	
    		 
    		 if (Termes.keySet().contains(st.getPreflabel())){
    			     			 
    			 Termes.get(st.getPreflabel()).addIds(j.get("@id").asText());
    			 
    			 
    		 }
    		 else {
    			 Termes.put(st.getPreflabel(),st) ;
    		 }
    		 
    		 
    	}
    		 
 
    
    private static JsonNode jsonToNode(String json) {
        JsonNode root = null;
        try {
            root = mapper.readTree(json);
        } catch (JsonProcessingException e) {
        	return root;
            //e.printStackTrace();
        } catch (IOException e) {
        	return root;
          //  e.printStackTrace();
        }
        return root;
    }

    private static String get(String urlToGet) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToGet);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "apikey token=" + API_KEY);
            conn.setRequestProperty("Accept", "application/json");
            
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
           
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
        	return result;
           // e.printStackTrace();
           
        }
        return result;
    }
}
