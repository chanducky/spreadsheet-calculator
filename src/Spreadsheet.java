import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

/**
 * @author chandrakumar
 *
 */
public class Spreadsheet {

	public static void main(String[] args) throws IOException {
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    	String line = input.readLine();
	    	
	        int n = Integer.parseInt(line.split(" ")[0]); // columns
	        int m = Integer.parseInt(line.split(" ")[1]); //  rows
	            
	        int r=65;//Character A
	        
	        LinkedHashMap<String,String> sheet = new LinkedHashMap<>();
	        
	        StringBuilder cellNameBuilder=null;
	        for(int i=0;i<m;i++){
	        	for(int j=0;j<n;j++){
	        		// cell name generation
	        		cellNameBuilder = new StringBuilder();
	        		cellNameBuilder.append((char)r); // row name i.e A,B,C .
	        		cellNameBuilder.append(j+1); // col 1,2,3 ..N
	        		
	        		// final cell name set i.e A1,A2,A3 ..An,B1,B2,B3..Bn etc
	        		sheet.put(cellNameBuilder.toString(), input.readLine());
	        	}
	        	//row name increment like 65,66,67.. i.e (A,B,C..Z)
	        	r++;
	        }
	        
	        System.out.print(n); // print no of columns
	        System.out.println(" "+m); // print no of rows
	        
	        
	        PostfixEvaluation pfe = new PostfixEvaluation(sheet);
	        pfe.evaluateSheet();
	        
	    }
}
