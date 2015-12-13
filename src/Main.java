/*
 * File : Main.java
 * Code written by : Aditya Shibrady
 * Author : Aditya Shibrady - AXS142431
 * 
 *  Program to tokenize the Cranfield Collection and calculate various parameters.
 * 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Main {
	
	static int tokens =0, average_tokens = 0, num_unique_token = 0, num_once_token = 0, stems = 0, num_unique_stem = 0, num_once_stem = 0;
	static KeyValueComparator kvc =  new KeyValueComparator(Tokenizer.token_map);
	static KeyValueComparator kvc1 =  new KeyValueComparator(Tokenizer.stem_map);
	
	static TreeMap<String,Integer> sort_map = new TreeMap(kvc);
	static TreeMap<String,Integer> stem_map_sort = new TreeMap(kvc1);
	
	//Please change the  input files and tokenize accordingly
	static String filepath = "Cranfield/";

	static String[] stopwords= {"and","any","are","as","be","been","but","by","few","for","have","he","her","here","him","his","how","i","in","is","it","its","many","me","my","none","of","on","or","our","she","some","the","their","them","there","they","that","this","us","was","what","when","where","which","who","why","will","with","you","your"};
	
	public static void main(String[] args) throws Exception {
		
		
		final File folder = new File("Cranfield/");
		listFilesForFolder(folder);
		/*System.out.println ("--------------------------------------------------------------");
		System.out.println ("Problem 1:");
		System.out.println ("--------------------------------------------------------------");
		System.out.println ("\n");*/
		calculate_token_statistics();
		display_token_statistics();
	//	calculate_stem_statistics();
		/*System.out.println ("\n");
		System.out.println ("\n");
		System.out.println ("--------------------------------------------------------------");
		System.out.println ("Problem 2:");
		System.out.println ("--------------------------------------------------------------");
		System.out.println ("\n");*/
		//display_stem_statistics();
		
		
	}
	
	public static void listFilesForFolder(final File folder) throws Exception {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        }
        else {      
            
            String path = filepath + "/" + fileEntry.getName();
            
            Parse p1 = new Parse();
     		Cranfield c = p1.perform_parse(path);
     		
     		Tokenizer t = new Tokenizer();
     		t.tokenize(c);      
            
        }
    }
}
	
	static void calculate_token_statistics()
	{
		
		Set s = Tokenizer.token_map.entrySet();
		Iterator i = s.iterator();
		
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			
			
			Main.num_unique_token++;
			
			if(me.getValue().equals(1))
			{
				Main.num_once_token++;
			}
	
		} 	
	}
	
	static void calculate_stem_statistics()
	{

		System.out.println("in calculate_stem_statistics ");
		Set s1 = Tokenizer.stem_map.entrySet();
		Iterator i1 = s1.iterator();
		
		while(i1.hasNext())
		{
			Map.Entry me = (Map.Entry)i1.next();
	
			System.out.println(me.getValue());
						Main.num_unique_stem++;
			
			if(me.getValue().equals(1))
			{
				Main.num_once_stem++;
			}
			}
			}
			
        
	
	
	static void display_token_statistics()
	
	{
		sort_map.putAll(Tokenizer.token_map);
		String outputFile="./output.csv"; //Change  name  of output file.. if needed
		File opfile = new File(outputFile);

		// if file doesn't exists, then create it
		if (!opfile.exists())
			try {
				opfile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		

		String COMMA_DELIMITER = ",";
		 String NEW_LINE_SEPARATOR = "\n";
		 //String FILE_HEADER = "Interest,Level";
        Set set1 = sort_map.entrySet();
        int rating=0;
        Iterator i1 = set1.iterator();
        
        try{
        	FileWriter fileWriter = new FileWriter(opfile.getAbsoluteFile());
    		
        	 //fileWriter = new FileWriter(fileName);
             //fileWriter.append(FILE_HEADER.toString());

        System.out.println();
		//String userid="Please fill upthe userid"; // Please  fill up the user id..as simle tweet file didn't have  any id.
		for(int x=0;x<20;x++)
	        {
	        Map.Entry m1 = (Map.Entry)i1.next();
	       // fileWriter.append(String.valueOf(userid));     
	        //fileWriter.append(COMMA_DELIMITER);
	        fileWriter.append(String.valueOf(m1.getKey()));
	        fileWriter.append(COMMA_DELIMITER);
	        int r1=(int) m1.getValue();
	        //System.out.println(r1);
	        //Change range of ratings..as required/needed..I didn't have whole data
	        if(x>=0 && x<=3)
	        	rating=5;
	        if(x>=4 && x<=8)
	        	rating=4; 
	        if(x>=9 && x<=13)
	        	rating=3;
	        if(x>=14 && x<=19)
	        	rating=2; 
	        if(x>20)
	        	rating=1;
	        fileWriter.append(String.valueOf(rating));
	        fileWriter.append(NEW_LINE_SEPARATOR);
	        System.out.println("   " + (x+1)+". "+m1.getKey()+" --- "+m1.getValue());
	        }
		
		//System.out.println();
        
		 fileWriter.close();
}
        catch(Exception e)
        {
        	System.out.println("Exception: "+e);
        }
	//	System.out.println("5. The average number of tokens in each document: " + Main.tokens/1400);
		 
		
	}
	
	static void display_stem_statistics()
	{
		System.out.println("1. The number of distinct stems in Cranfield text collection: " + Main.stems);
		System.out.println("2. The number of unique stems in Cranfield Collection: " + Main.num_unique_stem);
		System.out.println("3. The number of stems that occur only once: " + Main.num_once_stem);
		System.out.println("4. 30 most frequent word stems in Cranfield text collection : " );
		  
		stem_map_sort.putAll(Tokenizer.stem_map);
        
        Set set2 = stem_map_sort.entrySet();
        Iterator i2 = set2.iterator();
        
        System.out.println();
		
		for(int x=0;x<30;x++)
	        {
	        Map.Entry m2 = (Map.Entry)i2.next();
	             
	        System.out.println("   " + (x+1)+". "+m2.getKey()+" --- "+m2.getValue());
	        }
		
		System.out.println();
		  
		System.out.println("5. The average number of stems in each document: " + Main.stems/1400);
	}
}
