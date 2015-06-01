package com.example.dc.fourcolors;

import java.util.ArrayList;

import models.VersusAIGameState;

import utils.PuzzleBoard;
import android.app.Application;

public class BoardApplication extends Application {
	private PuzzleBoard singlePlayerBoard;
	private VersusAIGameState vsAI;

	public void setSinglePlayerBoard(PuzzleBoard b) {
		singlePlayerBoard = b;
	}

	public void setVSAIBoard(VersusAIGameState b) {
		vsAI = b;
	}

	public PuzzleBoard getSinglePlayerBoard() {
		return singlePlayerBoard;
	}

	public VersusAIGameState getVSAIGameState() {
		return vsAI;
	}

	public boolean ongoingSPGame() {
		return singlePlayerBoard != null;
	}

	public boolean ongoingAIGame() {
		return vsAI != null;
	}

}
