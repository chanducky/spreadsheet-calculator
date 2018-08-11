import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author chandrakumar
 *
 */
public final class DetectCyclicCall {

	LinkedHashMap<String,String> sheet=null;
	HashMap<String,Boolean> visited = null;

	/**
	 * @param sheet
	 */
	private DetectCyclicCall(LinkedHashMap<String, String> sheet) {
		super();
		this.sheet = sheet;
		this.visited =new HashMap<>();;
	}
	/**
	 * @param token
	 * @param visited
	 * @return boolean
	 */
	private boolean isCyclicCall(String token, HashMap<String,Boolean> visited) 
	{

		if (visited.containsKey(token) && visited.get(token)){
			return true;
		}

		visited.put(token, true) ;

		String children = null;
		if(sheet.containsKey(token)){
			children=sheet.get(token);

			String[] childrens = children.split(" ");
			for(String child:childrens){
				if(sheet.containsKey(child)){
					return isCyclicCall(child, visited);
				}
			}
		}

		return false;
	}
	public static boolean isCyclic(LinkedHashMap<String,String> sheet,String token) 
	{
		DetectCyclicCall dcc = new DetectCyclicCall(sheet);

		if (dcc.isCyclicCall(token,dcc.visited)){
			return true;
		}

		return false;
	}
}
