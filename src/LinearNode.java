
public class LinearNode<T> {
	
	private LinearNode <T> next;
	private LinearNode <T> prev;
	private T dataItem;
	
	public LinearNode() {
		next = null;
		prev = null;
		dataItem = null;
	}
	
	public LinearNode (T value) {
		next = null;
		prev = null;
		dataItem = value;
	}
	
	public LinearNode<T> getNext (){
		return next;
	}
	
	public void setNext(LinearNode<T> node){
		next = node;
	}

	public LinearNode <T> getPrev(){
		return prev;
	}

	public void setPrev(LinearNode<T> node){
		prev = node;
	}

	public T getDataItem () {
		return dataItem;
	}

	public void setDataItem (T value) {
		dataItem = value;
	}
	
}
