	public class Node {
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
