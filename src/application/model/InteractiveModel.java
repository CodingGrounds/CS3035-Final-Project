package application.model;

public class InteractiveModel {
	
	private static Board currentBoard;

	public static Board getCurrentBoard() {
		return currentBoard;
	}

	public static void setCurrentBoard(Board currentBoard) {
		InteractiveModel.currentBoard = currentBoard;
	}
	
}
