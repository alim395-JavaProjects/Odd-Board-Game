
public class Board implements BoardADT {
	
	/**
	 * This class stores information about the tiles placed on the game-board and implements support methods needed by the algorithm that plays the game.
	 * 
	 * @author Ali Tamer Ali Mohamed
	 */

	private char[][] theBoard; //
	private int boardSize; //
	
	/**
	 * Inserts the Layout object referenced by data in the dictionary if the dictionary does not contain any object with the same key attribute as data;
	 *
	 * @param board_size 
     * 
     * @param empty_positions
     * 
     * @param max_levels
     */
	public Board (int board_size, int empty_positions, int max_levels) {
		this.boardSize = board_size;
		this.theBoard = new char[board_size][board_size];
		for(int i = 0; i < board_size; i++) {
			for(int j = 0; j < board_size; j++) {
				theBoard[j][i] = 'e';
			}
		}
	}
	
	/**
	 * Returns an empty Dictionary of the size that you have selected.
	 *
	 * @return Empty dictionary of using size of board.
     */
	
	@Override
	public Dictionary makeDictionary() {
		//Create dictionary of boardSize
		return new Dictionary(boardSize);
	}
	
	/**
	 * This method first represents the content of the 2-dimensional array theBoard as a String s;
	 * Then it checks whether there is a data item in the dictionary referenced by dict with key s;
	 * If there is such a data item, this method returns the associated score;
	 * Otherwise it returns the value -1.
	 *
	 * @param dict Dictionary that will be used to search for object referenced by key.
	 *
	 * @return Score of associated object referenced by key, or -1 if it is not in supplied dictionary.
     */
	@Override
	public int repeatedLayout(Dictionary dict) {
		//Create Board Layout String
		String s = "";
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				s+= this.theBoard[j][i];
			}
		}
		//Look for Board Layout in dict
		if(dict.getScore(s) != -1) {
			return dict.getScore(s);
		}
		return -1;
	}

	/**
	 * This method first represents the content of the 2-dimensional array theBoard as a String s;
	 * Then it creates a Layout object storing s and score and stores this object in dict;
	 *
	 * @param dict Dictionary that will be used to store the new object.
	 * 
	 * @param score The associated score that will be used to create the layout object.
     */
	@Override
	public void storeLayout(Dictionary dict, int score) {
		//Create Board Layout String
		String s = "";
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				s+= this.theBoard[j][i];
			}
		}
		//Create Layout Object
		Layout bL = new Layout(s,score);
		//Add to dictionary
		try {
			dict.put(bL);
		} catch (DictionaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method stores symbol in theBoard[row][col].
	 * 
	 * @param row Row Index
	 * 
	 * @param col Column Index
	 * 
	 * @param symbol Symbol (either 'h' or 'c') to be stored in the specified board position.
	 * 
	 */
	@Override
	public void saveTile(int row, int col, char symbol) {
		theBoard[row][col] = symbol;
		
	}
	
	/**
	 * This method returns true if theBoard[row][col] is ’e’;
	 * Otherwise it returns false.
	 *
	 * @param row Row Index
	 * 
	 * @param col Column Index
	 * 
	 * @return True if position is empty; false if is not.
     */
	@Override
	public boolean positionIsEmpty (int row, int col) {
		if(theBoard[row][col] == 'e') {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if theBoard[row][col] is ’c’;
	 * Otherwise it returns false.
	 *
	 * @param row Row Index
	 * 
	 * @param col Column Index
	 * 
	 * @return True if position is computer symbol('c'); false if is not.
     */
	@Override
	public boolean isComputerTile (int row, int col) {
		if(theBoard[row][col] == 'c') {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if theBoard[row][col] is ’h’;
	 * Otherwise it returns false.
	 *
	 * @param row Row Index
	 * 
	 * @param col Column Index
	 * 
	 * @return True if position is computer symbol('h'); false if is not.
     */
	@Override
	public boolean isHumanTile (int row, int col) {
		if(theBoard[row][col] == 'h') {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if there are n adjacent tiles of type symbol in the same row, column, or diagonal of theBoard, where n is the size of the game-board.
	 *
	 * @param symbol The symbol that represents either the player or computer, that will be checked to see if they have won.
	 *
	 * @return True if the symbol chosen is a winner; false otherwise.
	 */
	@Override
	public boolean winner(char symbol) {
		int adjacentTiles = 0;
		int lastIndex = this.boardSize - 1;
		//Check rows and columns
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(this.theBoard[i][j] == symbol) {
					adjacentTiles++;
				}
			}
			if(adjacentTiles == this.boardSize) {
				return true;
			}
			else {
				adjacentTiles = 0;
			}
		}
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(this.theBoard[j][i] == symbol) {
					adjacentTiles++;
				}
			}
			if(adjacentTiles == this.boardSize) {
				return true;
			}
			else {
				adjacentTiles = 0;
			}
		}
		//Check First Diagonal
		for(int i = 0; i < this.boardSize; i++) {
			if(this.theBoard[i][i] == symbol) {
				adjacentTiles++;
			}
		}
		if(adjacentTiles == this.boardSize) {
			return true;
		}
		else {
			adjacentTiles = 0;
		}
		
		//Check Second Diagonal
		for(int i = 0; i < this.boardSize; i++) {
			if(this.theBoard[i][lastIndex] == symbol) {
				adjacentTiles++;
			}
			lastIndex--;
		}
		if(adjacentTiles == this.boardSize) {
			return true;
		}
		else {
			adjacentTiles = 0;
		}
		
		return false;
	}

	/**
	 * Returns true if the game layout corresponding to theBoard is a draw assuming that the player that must perform the next move uses tiles of the type specified by symbol.
	 * 
	 * @param symbol The symbol used to determine whether or not a draw is present.
	 * 
	 * @param empty_positions The number of positions of the game-board that must remain empty.
	 * 
	 * @return True, if the game is a draw; false otherwise.
	 */
	@Override
	public boolean isDraw(char symbol, int empty_positions) {
		int empty_spaces = 0;
		//Count the spaces on the board
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				if(theBoard[j][i] == 'e') {
					empty_spaces++;
				}
			}
		}
		//Check base case
		if(empty_positions == 0 && empty_spaces == 0) {
			return true;
		}
		//Check alternative cases
		else {
			if(empty_positions > 0 && empty_positions == empty_spaces) {
				//Check adjacent somehow?
				for(int i = 0; i < this.boardSize; i++) {
					for(int j = 0; j < this.boardSize; j++) {
						//Check if it is an empty tile
						if(this.theBoard[i][j] == 'e') {
							//Edges and Corners
							if(i == 0 || j == 0 || i == (this.boardSize - 1) || j == (this.boardSize - 1)) {
								//Top left corner
								if(i == 0 && j == 0) {
									if(!(this.theBoard[i+1][j] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i+1][j+1] == symbol)) {
										return true;
									}
								}
								//Top right corner
								if(i == 0 && j == (this.boardSize - 1)) {
									if(!(this.theBoard[i+1][j] == symbol || this.theBoard[i][j-1] == symbol || this.theBoard[i+1][j-1] == symbol)) {
										return true;
									}
								}
								//Bottom left corner
								if(i == (this.boardSize - 1) && j == 0) {
									if(!(this.theBoard[i-1][j] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i-1][j+1] == symbol)) {
										return true;
									}
								}
								//Bottom right corner
								if(i == (this.boardSize - 1) && j == (this.boardSize - 1)) {
									if(!(this.theBoard[i-1][j] == symbol || this.theBoard[i][j-1] == symbol || this.theBoard[i-1][j-1] == symbol)) {
										return true;
									}
								}
								//Left Edge
								if(i == 0) {
									if(this.theBoard[i][j-1] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i+1][j-1] == symbol || this.theBoard[i+1][j] == symbol || this.theBoard[i+1][j+1] == symbol) {
										return true;
									}
								}
								//Top Edge
								if(j == 0) {
									if(this.theBoard[i-1][j] == symbol || this.theBoard[i+1][j] == symbol || this.theBoard[i-1][j+1] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i+1][j+1] == symbol) {
										return true;
									}
								}
								//Right Edge
								if(i == this.boardSize) {
									if(this.theBoard[i][j-1] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i-1][j-1] == symbol || this.theBoard[i-1][j] == symbol || this.theBoard[i-1][j+1] == symbol) {
										return true;
									}
								}
								//Bottom Edge
								if(j == this.boardSize) {
									if(this.theBoard[i-1][j] == symbol || this.theBoard[i+1][j] == symbol || this.theBoard[i-1][j-1] == symbol || this.theBoard[i][j-1] == symbol || this.theBoard[i+1][j-1] == symbol) {
										return true;
									}
								}
							}
							else {
								//Everything Else
								if(this.theBoard[i-1][j-1] == symbol || this.theBoard[i-1][j] == symbol || this.theBoard[i-1][j+1] == symbol || this.theBoard[i][j-1] == symbol || this.theBoard[i][j+1] == symbol || this.theBoard[i+1][j-1] == symbol || this.theBoard[i+1][j] == symbol || this.theBoard[i+1][j+1] == symbol) {
									return true;
								}
							}
							
							
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method evaluates the board and return a value between 0-3, to indicate the status of the game.
	 * 
	 * @param symbol Used to determine if game is a draw. (see isdraw)
	 * 
	 * @param empty_positions Number of positions that must remain empty
	 * 	
	 * @return 0 if human has won, 1 if it is still ongoing, 2 if it is a draw, and 3 if the computer has won.
	 * 
	 */
	@Override
	public int evaluate(char symbol, int empty_positions) {
		//Did human win?
		if(this.winner('h')) {
			return 0;
		}
		//Did computer win?
		if(this.winner('c')) {
			return 3;
		}
		//Is it a draw?
		if(this.isDraw(symbol, empty_positions)) {
			return 2;
		}
		return 1;
	}

}
