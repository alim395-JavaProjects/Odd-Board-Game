
public class LinkedList<T> {
	
	private LinearNode<T> head;
	private LinearNode<T> tail;
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	public LinearNode<T> first() {
		return head;
	}
	
	public LinearNode<T> last() {
		return tail;
	}
	
	public void addNode(T value) {
		
		LinearNode<T> newNode = new LinearNode<T>(value);
		
		if(head == null) {
			head = tail = newNode;
			head.setPrev(null);
			tail.setNext(null);
		}
		else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
	}
	
	public void delNode(T value){
		
		LinearNode<T> current = head;
		
		 while (current != null && current.getDataItem() != value) {
			 current = current.getNext();
		 }
		 
		 if (current != null) {
			 if(current.getPrev() != null) {
				 current.getPrev().setNext(current.getNext());
			 }
			 else {
				head = current.getNext();
			 }
			 if(current.getNext() != null) {
				 current.getNext().setPrev(current.getPrev());
			 }
			 else {
				 tail = current.getPrev();
			 }
		 }
	}
	
	public int search(T value)
    {
        if (head == null) {
            return -1;
        }
        
        int index = 0;
        LinearNode<T> temp = head;
  
        while (temp != null) {
            if (temp.getDataItem() == value) {
                return index;
            }
            index++;
            temp = temp.getNext();
        }
        
        return -1;
    }
	
}
