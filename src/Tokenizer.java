import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Tokenizer {
	static HashMap<String,Integer> token_map = new HashMap<String,Integer>();
	static HashMap<String,Integer> stem_map = new HashMap<String,Integer>();
	//static String[] stopwords= {"an","all","a","at","and","any","are","as","be","can","been","but","by","few","for","have","he","her","here","him","his","how","i","in","is","it","its","many","me","my","none","of","on","or","our","she","some","the","their","them","there","they","that","this","us","was","what","when","where","which","who","why","will","with","you","your"};
	static String[] stopwords={"a","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"};
	Stemmer stem = new Stemmer();
	int n=stopwords.length;

	
	public void tokenize(Cranfield c) {
		
		StringTokenizer st1 = new StringTokenizer(c.docid,",;+=&#:()!?[]@$%^*  ");
		token_count(st1);
		
		StringTokenizer st2 = new StringTokenizer(c.title,",;+=&#:()!?[]@$%^*  ");
		token_count(st2);
		
		StringTokenizer st3 = new StringTokenizer(c.author,",;+=&#:()!?[]@$%^*  ");
		token_count(st3);
		
		StringTokenizer st4 = new StringTokenizer(c.biblio,",;+=&#:()!?[]@$%^*  ");
		token_count(st4);
		
		StringTokenizer st5 = new StringTokenizer(c.text,",;+=&#:()!?[]@$%^*  ");
		token_count(st5);	
	}
	
	
	void token_count(StringTokenizer st1)
	{

		while (st1.hasMoreTokens())
		{
           //System.out.println("String"+ st1 );
			
			String st2 = st1.nextToken().toLowerCase();
			st2 = st2.replaceAll("[^A-Za-z]","");

			
	           	stem.add(st2.toCharArray(), st2.length());
	           	stem.stem();
//	        System.out.println(st2);
			String after_stem = stem.toString();

			
				if(st2.length()!=0)
				{
					boolean isStopWord=false;
					//System.out.println("Str : "+st2);
					for(int i=0;i<stopwords.length;i++)
					{
					if(st2.equalsIgnoreCase(stopwords[i]))
					{
						//System.out.println(" in if Str : "+st2);
						isStopWord=true;
						break;
						}
					}
					
					if(!isStopWord)
					{
						//System.out.println(" in else Str : "+st2);
						if(token_map.get(st2)==null)
						{
							int n =1;
							token_map.put(st2, n);
							Main.tokens++;
						}
						else
						{
				           int n = token_map.get(st2) + 1;
				           token_map.remove(st2);
				   
				           token_map.put(st2, n);
				           Main.tokens++;                                                       
						}
					}	
				}//st2
				
				
				if(after_stem.length()!=0)
				{
					boolean isStopWordStem=false;
					//System.out.println("Str : "+st2);
					for(int i=0;i<stopwords.length;i++)
					{
					if(after_stem.equalsIgnoreCase(stopwords[i]))
					{
						//System.out.println(" in if Str : "+st2);
						isStopWordStem=true;
						break;
						}
					}
					
					if(!isStopWordStem)
					{
						if(stem_map.get(after_stem)==null)
					
					{
						int n =1;
						stem_map.put(after_stem, n);
						Main.stems++;
					}
					else
					{
						int n = stem_map.get(after_stem) + 1;
						stem_map.remove(after_stem);
						stem_map.put(after_stem, n);
						Main.stems++;                                                       
					}
			
					}
					}//after
		
	}//while
}//tken_count
	}//tokenixer
	
