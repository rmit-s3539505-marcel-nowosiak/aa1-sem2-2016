

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
    public void addVertex(T vertLabel) {
		vertCount++;
		LinkedList[] tempList = new LinkedList[vertCount];
		LinkedList newVert = new LinkedList((String)vertLabel);
		
		if(verts.length==0){
			tempList[0] = newVert;
		}
		
		else{
			for(int x=0; x<verts.length; x++){
					tempList[x] = verts[x];
			}
			tempList[verts.length] = newVert;
		}
	verts = tempList;
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
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        // Implement me!
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
    		for(int x=0; x<vertCount; x++){
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
