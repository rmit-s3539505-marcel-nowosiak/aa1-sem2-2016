import java.io.PrintWriter;

public class LinkedList{
	private String name;
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList(String n){
		name = n;
		head = null;
		tail = null;
		size = 0;
	}
	
	public void addNode(String label){
		Node newNode = new Node(label);
		
		if(head == null){
			head = newNode;
			tail = newNode;
		}
		else{
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}
	
	public boolean removeNode(String label){
		Node currNode = head;
		
		if(currNode.getLabel().matches(label)){
			if(size == 1){
				head = tail = null;
			}
			else{
				head = currNode.getNext();
				head.setPrev(null);
				currNode = null;
			}
			
			size--;
			return true;
		}
		
		else{
			currNode = currNode.getNext();
			
			while(currNode != null){
				if(currNode.getLabel().matches(label)){
					Node prevNode = currNode.getPrev();
					prevNode.setNext(currNode.getNext());
					
					if(currNode.getNext() != null){
						currNode.getNext().setPrev(prevNode);
					}
					else {
						tail = prevNode;
					}
					currNode = null;
					size--;
					return true;
				}
				
				currNode = currNode.getNext();
			}
		}
		return false;
	}
	
	public String getName(){
		return name;
	}
	
	public void printNodes(PrintWriter os){
		Node currNode = head;
		String s = name+"->";
		while(currNode != null){
			s=s+currNode.getLabel()+" ";
			currNode = currNode.getNext();
		}
		os.println(s);
	}
	
	
	
public static class Node {
	private String nlabel;
	private Node nextNode;
	private Node prevNode;
	
	public Node(String label){
		nlabel = label;
		nextNode = null;
		prevNode = null;
	}
	
	public String getLabel(){
		return nlabel;
	}
	
	public void setLabel(String s){
		nlabel = s;
	}
	
	public Node getNext(){
		return nextNode;
	}
	
	public Node getPrev(){
		return prevNode;
	}
	
	public void setNext(Node n){
		nextNode = n;
	}
	
	public void setPrev(Node n){
		prevNode = n;
	}
}

}