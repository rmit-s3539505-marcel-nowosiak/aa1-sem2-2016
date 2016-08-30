

import java.io.*;
import java.util.*;


/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{   
	
	private LinkedList[] verts;
	private int vertCount;
	
    // Constructs empty graph.	
    public AdjList() {
    	vertCount = 0;
		verts = new LinkedList[vertCount];
    } // end of AdjList()
	
	
    
    //------------------------------------------------------------
    //-----------------ADJACENCY FUNCTIONS------------------------
    //------------------------------------------------------------

    //------------------------------------------------------------
    // Finds vertex
    private boolean searchVert(String vertLabel){
    	for(int x=0; x<verts.length; x++){
    		if(verts[x].getName().matches(vertLabel)){
    			return true;
    		}
    	}
    	return false;
    }
    //------------------------------------------------------------
    
    private int getIndex(T vertLabel){
    	for(int x=0; x<verts.length; x++){
    		if(verts[x].getName().matches((String)vertLabel)){
    			return x;
    		}
    	}
    	return -1;
    }
    
    
    
    //------------------------------------------------------------
    // Adds vertex to adjacency list
    public void addVertex(T vertLabel) {
		vertCount++;
		LinkedList[] tempList = new LinkedList[vertCount];
		LinkedList newVert = new LinkedList((String)vertLabel);
		
		
		if(verts.length==0){
			tempList[0] = newVert;
			verts = tempList;
		}
		
		else if(searchVert((String)vertLabel) == false){
			for(int x=0; x<verts.length; x++){
					tempList[x] = verts[x];
			}
			tempList[verts.length] = newVert;
			verts = tempList;
		}
		else{
			System.out.println("Vert already exists");
	}
    } 
    // end of addVertex()
    //------------------------------------------------------------
    
    
    
    
    //------------------------------------------------------------
    // Links two vertices
    public void addEdge(T srcLabel, T tarLabel) {
    	
        for(int x=0; x<verts.length; x++){
        	if(verts[x].getName().equals((String)srcLabel)){
    
        		for(int y=0; y<verts.length; y++){
        			if(verts[y].getName().matches((String)tarLabel)){
        				verts[x].addNode((String)tarLabel);
        				
        					if(((String)tarLabel).matches((String) srcLabel)){
        						break;
        					}
        					else{
        						verts[y].addNode((String)srcLabel);
    }}}}}
    } // end of addEdge()
    //------------------------------------------------------------
    
    
    
    //------------------------------------------------------------
    // Prints all neighbors of requested vertex
   @SuppressWarnings("unchecked")
   public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
 
        for(int x=0; x<verts.length; x++){
        	if(verts[x].getName().equals((String)vertLabel)){
        		Node currNode = verts[x].getHead();
        		while(currNode != null){
        			neighbours.add((T)currNode.getLabel());
        			currNode = currNode.getNext();
        		}
        	}
        }
          return neighbours;
    } // end of neighbours()
   //------------------------------------------------------------
    
    
    

    //------------------------------------------------------------
    // Removes vertex from adjacency list
    public void removeVertex(T vertLabel) {
    	LinkedList[] newList = new LinkedList[vertCount-1];
    	LinkedList temp;
    	
    	for(int x=0; x<vertCount; x++){
    		if(verts[x].getName().matches((String) vertLabel)){
    			
    			temp=verts[x];
    			verts[x] = verts[vertCount-1];
    			verts[vertCount-1] = temp;
    			
    			for(x=0;x<vertCount-1;x++){
    				if(verts[x].searchNode((String)vertLabel)==true){
    					verts[x].removeNode(temp.getName());
    				}
    				newList[x]=verts[x];
    			}
    			verts = newList;
    			vertCount--;
    }}
    } // end of removeVertex()
    //------------------------------------------------------------
    
    
    
    
    
    //------------------------------------------------------------
    // Removes a requested link between two vertices
    public void removeEdge(T srcLabel, T tarLabel) {
    	
        for(int x=0; x<verts.length; x++){
        	if(verts[x].getName().equals((String)srcLabel)){
    
        		for(int y=0; y<verts.length; y++){
        			if(verts[y].getName().matches((String)tarLabel)){
        				verts[x].removeNode((String) tarLabel);
        				
        					if(((String)tarLabel).matches((String) srcLabel)){
        						break;
        					}
        					else{
        						verts[y].removeNode((String)srcLabel);
    }}}}}
    } // end of removeEdges()
    //------------------------------------------------------------
    
    
    
    
    //------------------------------------------------------------
    // Prints a list of all vertices
    public void printVertices(PrintWriter os) {
    	if(verts.length != 0){
    		for(int x=0; x<verts.length; x++){
        	os.print(verts[x].getName()+" ");
    		}
    		os.println();
    	}
    	else{
    		os.println("No vertices to print.");
    	}
    } // end of printVertices()
    //------------------------------------------------------------
    
    
    
    
	
    //------------------------------------------------------------
    // Prints all edges for all vertices
    public void printEdges(PrintWriter os) {
    	if(verts.length != 0){
    	Node currNode;
	    	for(int x=0; x<verts.length; x++){
	
	    		currNode = verts[x].getHead();
	    		os.print(verts[x].getName());
	    		while(currNode != null){
	    			os.print(" "+currNode.getLabel());
	    			currNode = currNode.getNext();
	    		}
	    		os.println();
	    	}
    	}
    } // end of printEdges()
    //------------------------------------------------------------
    
    
    
    
    //------------------------------------------------------------
    // Calculation of shortestPathDistance
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	Stack<T> stack = new Stack<T>();
    	int count = 0;
    	boolean[] visited = new boolean[verts.length];
    	ArrayList<T> n;
    	int start = getIndex(vertLabel1);
    	stack.push(vertLabel1);
    	visited[start] = true;
	
    	while(stack.isEmpty() == false){
    		T element = stack.pop();
    		n = neighbours(element);
    		
    		count++;
    		
    		if(n.contains(vertLabel2)){
    			return count;
    		}

    		for(int x=0; x<n.size(); x++){
    			if(visited[getIndex(n.get(x))] == false) {
    				stack.push(n.get(x));
    				visited[x] = true;
    			}
    		}
    	}
    	
        // if we reach this point, source and target are disconnected*/
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    //------------------------------------------------------------
        
    
} // end of class AdjList
