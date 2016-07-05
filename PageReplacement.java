/* Submitted by: ISHAAN CHHABRA || ic887 || N18826877 */
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PageReplacement {
	public static void main(String[] args) {		
			List<Integer> refValue = new ArrayList<>();
	       String fileName="/Users/ishaanchhabra7/Desktop/Process.txt";// Please change the path and filename of the file with randomly generated numbers, according to your system. 
	       try{
	          FileReader inputFile = new FileReader(fileName);
	          BufferedReader bufferReader = new BufferedReader(inputFile);
	          String line;

	          while ((line = bufferReader.readLine()) != null)   {
	            refValue.add(Integer.parseInt(line));
	          }
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }

	    System.out.println("1) PFF Algorithm:");
		HashMap<Integer,Integer> resSet=new HashMap<Integer,Integer>();  
		int T = 0;
		int pf = 0;
		int f = 3; //Keep changing 'f' value for different Page Fault values.
		for(int p = 1; p < refValue.size(); p++)
		{
			if(resSet.containsKey(refValue.get(p))){ 
				T++;
				resSet.put(refValue.get(p), 1);
			}
			
			else
			{
				pf++;
				if(T < f) 
				{
					resSet.put(refValue.get(p), 1);
				}
				else
				{
					Iterator it = resSet.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pair = (Map.Entry)it.next();
				        if((pair.getValue()).equals(0))
				        {
				        	it.remove();
				        }
				        if((pair.getValue()).equals(1))
				        {
				        	pair.setValue(0);
				        }
				        
				    }
				    resSet.put(refValue.get(p), 1);
				}
				T = 0;
			}
			
		}
		System.out.println("Number of page faults for f="+f+" : "+pf+"\n");
		// For PFF as f value increases, number of page faults decrease (become constant after a particular f value). 
		System.out.println("2) VSWS Algorithm:");
		int M = 6;
		int L = 21;
		int Q = 8;
		//Keep changing M,L,Q values for different Page Fault values.  
		T = 0;
		pf = 0;
		for(int p = 1; p < refValue.size(); p++)
		{
			T++;
			if(T >= L)
			{
				Iterator it = resSet.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        if((pair.getValue()).equals(0))
			        {
			        	it.remove();
			        }
			        if((pair.getValue()).equals(1))
			        {
			        	pair.setValue(0);
			        }
			        
			    }
			    resSet.put(refValue.get(p), 1);
			    T = 0;
			    
			}
			else
			{
				if(!(resSet.containsKey(refValue.get(p)))){ 
					pf++;
				}
				if(T >= M)
				{
					if(pf >= Q)
					{
						Iterator it = resSet.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pair = (Map.Entry)it.next();
					        if((pair.getValue()).equals(0))
					        {
					        	it.remove();
					        }
					        if((pair.getValue()).equals(1))
					        {
					        	pair.setValue(0);
					        }
					        
					    }
					    resSet.put(refValue.get(p), 1);
						T = 0;
					}
					
				}
					resSet.put(refValue.get(p), 1);
				
			}
		}
	System.out.println("Total page faults occurred:" +pf);
	// As we keep changing values{M,L,Q}, the total page faults reaches an optimal solution and does not surpass.  
	}
}
