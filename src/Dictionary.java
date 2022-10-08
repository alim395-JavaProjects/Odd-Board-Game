
public class Dictionary implements DictionaryADT {
	
	/**
	 * @author Ali Tamer Ali Mohamed
	 */
	
	LinkedList<Layout>[] theArray; //Array of Linked List, Container for Hash Map
	int arraySize; //Size of above array
	int itemsInArray = 0; //Number of items in the array
	
	/**
     * This initializes a dictionary with an empty hash table of the specified size.
     *
     * @param size The size of the the new dictionary.
     */
	public Dictionary(int size) {
		this.arraySize = size;
		this.theArray = new LinkedList[size];
	}
	
	/**
	 *Inserts the Layout object referenced by data in the dictionary if the dictionary does not contain any object with the same key attribute as data;
	 *Otherwise this method must throw a DictionaryException.
	 *
	 * @param data The board layout object that will be put into the Layout array
     * 
     * @return 1 if a collision occurred, 0 otherwise.
     */
	public int put(Layout data) throws DictionaryException{
		int i = 0;
		int collisionFlag = 0;
		
		//Use has function to create the index
		int index = hashFunction(data.getBoardLayout());
		
		if(theArray[index] != null && theArray[index].first() != null) {
			collisionFlag = 1;
			
			LinearNode<Layout> current  = theArray[index].first();
			
			//Check the first node
			if(current.getDataItem().getBoardLayout().equals(data.getBoardLayout())) {
				throw new DictionaryException("Board Layout already exists.");
			}
			
			//Check all the remaining nodes in the list and delete if found
			else {
				while(!current.getDataItem().getBoardLayout().equals(data.getBoardLayout()) && current.getNext() != null) {
					current = current.getNext();
					if(current.getDataItem().getBoardLayout().equals(data.getBoardLayout())) {
						throw new DictionaryException("Board Layout already exists.");
					}
				}
			}
			
			//By now we know it hasn't been found in the Linked List so we can simply add the node
			theArray[index].addNode(data);
			
		}
		else {
			theArray[index] = new LinkedList<Layout>();
			theArray[index].addNode(data);
		}
				
		return collisionFlag;
	}
	
	/**
     * Removes the object with key boardLayout from the dictionary;
     * Otherwise throw a DictionaryException if there is no data item in the dictionary with this key.
     *
     * @param boardLayout The board layout that will be removed from the dictionary.
     */
	public void remove(String boardLayout) throws DictionaryException{
		int i = 0;
		int index = hashFunction(boardLayout);
		int presenceFlag = 0;
		
		if(theArray[index] == null) {
			throw new DictionaryException("Board Layout not found.");
		}
		
		LinearNode<Layout> current  = theArray[index].first();
		
		//Check the first node
		if(current.getDataItem().getBoardLayout().equals(boardLayout)) {
			presenceFlag = 1;
			theArray[index].delNode(current.getDataItem());
		}
		
		//Check all the remaining nodes in the list and delete if found
		else {
			while(!current.getDataItem().getBoardLayout().equals(boardLayout) && current.getNext() != null) {
				current = current.getNext();
				if(current.getDataItem().getBoardLayout().equals(boardLayout)) {
					presenceFlag = 1;
					theArray[index].delNode(current.getDataItem());
				}
			}
		}
		
		//If the presence of the board Layout has not been detected, throw exception.
		if(presenceFlag == 0) {
			throw new DictionaryException("Board Layout not found.");
		}
	}
	
	/**
	 * A method which returns the score stored in the object in the dictionary with key boardLayout
	 * Otherwise this method returns -1 if there no object in the dictionary with that key.
	 *
	 * @param boardLayout The key which is referenced to get the score.
     * 
     * @return scored of matching stored object, or -1 if not found.
     */
	public int getScore(String boardLayout) {
		int i = 0;
		int index = hashFunction(boardLayout);
		
		if(theArray[index] == null) {
			return -1;
		}
		
		LinearNode<Layout> current  = theArray[index].first();
		
		//Check the first node
		if(current.getDataItem().getBoardLayout().equals(boardLayout)) {
			return current.getDataItem().getScore();
		}
		//Check all the remaining nodes in the list and return score if found
		else {
			while(!current.getDataItem().getBoardLayout().equals(boardLayout) && current.getNext() != null) {
				current = current.getNext();
				if(current.getDataItem().getBoardLayout().equals(boardLayout)) {
					return current.getDataItem().getScore();
				}
			}
		}
		return -1;
	}
	
	/**
	 * A hash function which uses the characters of the key bL, an arbitrary prime number and the size of the dictionary.
	 *
	 * @param bL The key which is used to generate a hashed index for the hash table.
     * 
     * @return Hashed integer which becomes the index of hash table when used.
     */
	private int hashFunction(String bL) {
		int result = 0;
		int primeModifier = 47;
		int p = 1;

		for(int n = 0; n < bL.length(); n++) {
			result = (result + (bL.charAt(n) - '1' + 1) * p) % arraySize;
			p = (primeModifier * p) % arraySize;
		}
		return result;
	}
	
}
