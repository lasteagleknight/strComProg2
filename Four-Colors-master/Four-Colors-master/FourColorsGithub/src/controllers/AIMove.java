package controllers;

import java.util.ArrayList;
import java.util.Collections;

import com.example.dc.fourcolors.R;

import utils.PuzzleBoard;

import models.Point;
import models.Region;
import android.content.Context;
import android.graphics.Color;
import android.test.IsolatedContext;

public class AIMove {
	private PuzzleBoard board;
	private int color;

	private int currentColorIndex;

	// Used to enhance picking
	private int PLAYER_COLOR1;
	private int PLAYER_COLOR2;
	private int COMP_COLOR1;
	private int COMP_COLOR2;
	private int[] order;

	private static int BACKGROUND_COLOR;
	private static int EDGE_COLOR;

	public AIMove(PuzzleBoard board, int[] colors, int currentColor, Context c) {
		BACKGROUND_COLOR = c.getResources().getInteger(
				R.integer.BACKGROUND_COLOR);

		EDGE_COLOR = c.getResources().getInteger(R.integer.EDGE_COLOR);

		this.board = board;
		currentColorIndex = currentColor;
		this.color = colors[currentColor];
		PLAYER_COLOR1 = colors[0];
		PLAYER_COLOR2 = colors[2];
		COMP_COLOR1 = colors[1];
		COMP_COLOR2 = colors[3];
		order = colors;

	}

	public Point makeMove() {
		ArrayList<Region> validMoves = getValidMoves();

		if (validMoves.size() == 0)
			return null;
		ArrayList<Region> ours = getSafeRegions(validMoves);
		ArrayList<Region> bigSafes = new ArrayList<Region>();
		Collections.sort(validMoves);
		for (int i = validMoves.size() - 1; i >= 0; i--) {
			Region r = validMoves.get(i);
			if (ours.contains(r)) {
				if (!board.isValid(r.getAnchor(),
						order[(currentColorIndex + 2) % 4])) {
					// If this color can only be colored with our color, be
					// careful not to block it.
					bigSafes.add(r);
				}
				continue;
			}
			for (Region r2 : board.getAdjacents(r)) {
				if (bigSafes.contains(r2))
					// Can't block a safe region from bigSafes.
					continue;
			}
			return r.getAnchor();
		}
		// If we are still here, no good moves. Pick largest.
		return validMoves.get(validMoves.size() - 1).getAnchor();

	}

	/**
	 * A safe region is one that can be taken and has the enemies 2 colors on
	 * adjacent Regions.
	 * 
	 * @return All the safe Regions
	 */
	private ArrayList<Region> getSafeRegions(ArrayList<Region> possibles) {
		ArrayList<Region> safes = new ArrayList<Region>();
		for (Region r : possibles) {
			if (r.getColor() != BACKGROUND_COLOR) {
				continue;
			}
			boolean color1 = false, color2 = false, color3 = false, color4 = false;
			for (Region r2 : board.getAdjacents(r)) {
				if (r2.getColor() == PLAYER_COLOR1)
					color1 = true;
				else if (r2.getColor() == PLAYER_COLOR2)
					color2 = true;
				else if (r2.getColor() == COMP_COLOR1)
					color3 = true;
				else if (r2.getColor() == COMP_COLOR2)
					color4 = true;
			}
			if (color1 == true && color2 == true
					&& (color3 == false || color4 == false))
				safes.add(r);
		}
		return safes;
	}

	private Region getLargest(ArrayList<Region> regions) {
		if (regions == null || regions.size() == 0)
			return null;

		Region largest = regions.get(0);
		int maxSize = largest.getSize();

		for (int i = 1; i < regions.size(); i++) {
			if (regions.get(i).getSize() > maxSize) {
				largest = regions.get(i);
				maxSize = largest.getSize();
			}
		}

		return largest;
	}

	private ArrayList<Region> getValidMoves() {
		ArrayList<Region> validMoves = new ArrayList<Region>();

		// Somehow I got an invalid move here...
		// Possibly anchor points on edge?
		for (Region r : board.getRegions()) {
			if (board.isValidEnd(r.getAnchor(), color)
					&& r.getColor() == BACKGROUND_COLOR)
				validMoves.add(r);
		}

		return validMoves;
	}
}
