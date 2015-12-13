import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class KeyValueComparator implements Comparator {

	 Map base;
     
     public KeyValueComparator(HashMap<String, Integer> map) {
		
    	 this.base = map;
	}

	public int compare(Object a,Object b) {

       if((Integer)base.get(a) < (Integer)base.get(b)) {
         return 1;
       } else if((Integer)base.get(a) == (Integer)base.get(b)) {
         return 0;
       } else {
         return -1;
       }
     }
     
}
