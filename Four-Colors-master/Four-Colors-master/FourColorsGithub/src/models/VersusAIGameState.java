package models;

import utils.PuzzleBoard;

public class VersusAIGameState {

	private PuzzleBoard board;

	private int aiScore;
	private int playerScore;

	private int currentColor;

	public VersusAIGameState(PuzzleBoard board) {
		this.board = board;

		aiScore = 0;
		playerScore = 0;

		currentColor = 0;
	}

	public void setCurrentColor(int currentColor) {
		this.currentColor = currentColor;
	}

	public void setAIScore(int aiScore) {
		this.aiScore = aiScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public void setBoard(PuzzleBoard board) {
		this.board = board;
	}

	public int getCurrentColor() {
		return currentColor;
	}

	public int getAIScore() {
		return aiScore;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public PuzzleBoard getBoard() {
		return board;
	}
}
