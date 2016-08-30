import java.io.PrintWriter;

public class LinkedList{
	private String name;
	private Node head;
	private Node tail;
	private int size;
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

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

	public boolean searchNode(String label){
		Node currNode = head;
		for(int x=0; x<size; x++){
			if(currNode.getLabel().matches(label)){
				return true;
			}
			currNode = currNode.getNext();
		}
		return false;
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
}