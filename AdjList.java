

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
	
    //Contructs empty graph.	
    public AdjList() {
    	vertCount = 0;
		verts = new LinkedList[vertCount];
    } // end of AdjList()
	
	// START FUNCTIONS
    
    private boolean searchVert(String vertLabel){
    	for(int x=0; x<verts.length; x++){
    		if(verts[x].getName().matches(vertLabel)){
    			return true;
    		}
    	}
    	return false;
    }
    
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
        					}
        			}
        		}
        	}
        }
    } // end of addEdge()
	

    //prints backwards, tried to sort out a workaround by grabbing tail and making
    //a pointer that points <- (in THAT direction) but it shit itself im going to bed
    //fuck coding.
    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
 
        for(int x=0; x<verts.length; x++){
        	if(verts[x].getName().equals((String)vertLabel))
        	{
        		verts[x].printNodes();
        	}
        }
        
        return neighbours;
    } // end of neighbours()
    
    
    
    
    
    
    

    //Need to clean this up pretty badly, but it'll do for now
    public void removeVertex(T vertLabel) {
    	LinkedList[] newList = new LinkedList[vertCount-1];
    	LinkedList temp;
    	
    	for(int x=0; x<vertCount; x++){
    		if(verts[x].getName().matches((String) vertLabel)){
    			
    			temp=verts[x];
    			verts[x] = verts[vertCount-1];
    			verts[vertCount-1] = temp;
    			
//    			for(x=0;x<vertCount;x++){
//    				removeEdge();
//    			}
    			
    			for(x=0;x<vertCount-1;x++){
    				if(verts[x].searchNode((String)vertLabel)==true){
    					verts[x].removeNode(temp.getName());
    				}
    				newList[x]=verts[x];
    			}
    			verts = newList;
    			vertCount--;
    		}
    	}
    } // end of removeVertex()
    
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
        					}
        			}
        		}
        	}
        }
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
    	if(verts.length != 0){
    		for(int x=0; x<verts.length; x++){
        	os.println("Vertice "+(x+1)+" "+verts[x].getName());
    		}
    	}
    	else{
    		os.println("No vertices to print.");
    	}
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
    	for(int x=0;x<verts.length;x++){
    		verts[x].printNodes(os);
    	}
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjList
