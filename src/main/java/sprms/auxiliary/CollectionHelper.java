package sprms.auxiliary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sprms.model.ExtraRisk;
import sprms.model.Risk;
import sprms.model.RiskType;

public class CollectionHelper {
	
	private CollectionHelper(){
		
	}
	
	public static List<ExtraRisk> parseMap(Map<Risk,Integer> map){
		List<ExtraRisk> list = new ArrayList<ExtraRisk>();
		
		Set<Risk> set = map.keySet();
		
		Iterator<Risk> iter = set.iterator();
		
		while(iter.hasNext()){
			Risk key = iter.next();
			int count = map.get(key);
			ExtraRisk risk = new ExtraRisk();
			risk.setRisk(key);
			risk.setCount(count);
			list.add(risk);
		}
		
		return list;
	}
	
	public static int[] arrayParse(String str){
		String temp[] = str.split(",");
		int len = temp.length;
		int[] ret = new int[len];
		for(int i=0;i<len;i++){
			ret[i] = Integer.parseInt(temp[i]);
		}
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	public static int getLength(Iterable iter){
		int count = 0;
		Iterator it = iter.iterator();
		while(it.hasNext()){
			count++;
			it.next();
		}
		return count;
	}
	
}
