

import java.io.*;
import java.util.Map.Entry;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	/**
	 * Contructs empty graph.
	 */
	private int size;
	private boolean[][] aM;
	private Map<String,Integer> m;
	
	//Set size to empty, create new map & matrix
	public AdjMatrix() {
    	size = 0;
    	m = new HashMap<String,Integer>();
    	aM = new boolean[size][size];
    } // end of AdjMatrix()
    
	//Updates the matrix's size after a vertex is removed or added
    private boolean[][] updateMatrix(boolean[][] m){
    	boolean[][] newMatrix = new boolean[size][size];
    	for(int x=0; x<size-1; x++){
    		for(int y=0; y<size-1; y++){
    			newMatrix[x][y] = m[x][y];
    		}
    	}
    	return newMatrix;
    }
	
    //Each time a vertex is added, increment matrix's size & update it to match
	public void addVertex(T vertLabel) {
    	if(size == 0){
	        m.put((String)vertLabel, size);
	        size++;
	    	aM = new boolean[size][size];
	    	
    	}
    	else if(m.containsKey((String)vertLabel) == false){
    		m.put((String)vertLabel, size);
    		size++;
    		aM = updateMatrix(aM);
    	}
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	int tar = (int)m.get((String)tarLabel),
    		src = (int)m.get((String)srcLabel);
    	
    	if(aM[src][tar] != true){
    		if(srcLabel == tarLabel){
	    		aM[src][tar] = true;
	    	}
	    	else{
				aM[src][tar] = true;
				aM[tar][src] = true;
	    	}
    	}
    } // end of addEdge()
    
	@SuppressWarnings("unchecked")
	private T searchKey(int value){
    	for(Entry<String, Integer> e : m.entrySet()){
    		if(value == (int)e.getValue()){
    			return (T)e.getKey();
    		}
    	}
    	return null;
    }
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        int vert = m.get((String)vertLabel);

        for(int x=0; x<size; x++){
        	if(aM[vert][x] == true){
        		neighbours.add(searchKey(x));
        	}
        }
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        for(Map.Entry<String,Integer> e : m.entrySet()){
        	
        	String key = (String)e.getKey();
        	
        	if(key.matches((String)vertLabel)){
        		int index = (int)e.getValue();
        		
        		for(int x=index-1; x>0; x--){
        			for(int y=index-1; y>0; y--){
        				aM[x][y] = aM[x+1][y+1];
        			}
        		}
        		size--;
        		aM = updateMatrix(aM);
        		m.remove(key);
        		
        		//Update each entry in map's key values after one is removed
        		//If one is removed all those above have their index's shifted down
        		for(Map.Entry<String,Integer> entry : m.entrySet()){
        			if(entry.getValue() > index){
        				m.put(entry.getKey(), entry.getValue()-1);
        			}
        		}
            	break;
        	}
        }
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
    	int tar = (int)m.get((String)tarLabel),
    		src = (int)m.get((String)srcLabel);
    	
    	if(aM[src][tar] != false){
    		
	    	if(srcLabel == tarLabel){
	    		aM[src][tar] = false;
	    	}
	    	else{
				aM[src][tar] = false;
				aM[tar][src] = false;
	    	}
    	}

    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	for(Map.Entry<String,Integer> entry : m.entrySet()){
    		os.print(entry.getKey()+" ");
    	}
    	os.println();
    } // end of printVertices()
	
	public void printEdges(PrintWriter os) {
		int x, y;
		
		for(Map.Entry<String,Integer> entry: m.entrySet()){
			x = (int)entry.getValue();
			os.print(entry.getKey()+" ");
			
			for(Map.Entry<String,Integer> entry2: m.entrySet()){
				y = (int)entry2.getValue();
				
				if(aM[x][y] == true && aM[y][x] == true){
					os.print(entry2.getKey()+" ");
				}
			}
			os.println();
		}
    } // end of printEdges()
    
    //Compute shortest path via Breadth First Search.
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	Stack<Integer> stack = new Stack<Integer>();
    	int count = 0;
    	boolean[] visited = new boolean[size];
    	int start = m.get((String)vertLabel1);
    	int end = m.get((String)vertLabel2);
    	
    	visited[start] = true;
    	
    	stack.push(start);
    	
    	//If targ & src node are the same node, check if connected to self
   		if(start == end && aM[start][end] == true){
   			return 1;
   		}
   		
    	while(stack.isEmpty() == false){
    		//Get current top element in stack
    		int element = (int)stack.pop();	
    			
    		//If object on top of the stack == end we have reached the destination node
    		//and can return the current count/distance, & return the number of nodes traveled 
    		if(element == end && element != start){
    			return count;
    		}
    		count++;
    		int temp = 0;
    		while(temp<size){
    			if(visited[temp] == false && aM[element][temp] == true){
    				stack.push(temp);
    				visited[temp] = true;
    			}
    			temp++;
    		}
    	}
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjMatrix









