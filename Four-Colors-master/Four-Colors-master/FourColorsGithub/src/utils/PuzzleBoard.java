package utils;

import java.util.ArrayList;

import com.example.dc.fourcolors.R;

import models.Point;
import models.Region;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class PuzzleBoard {
	private int numRegions;
	private int width;
	private int height;
	private int[][] board;
	private int[][] edgeBoard;
	private int[][] adjacency;
	private ArrayList<Region> regions;
	private ArrayList<Point> anchorPoints;

	private int[] colors;

	private static int BACKGROUND_COLOR;
	private static int EDGE_COLOR;

	public PuzzleBoard(int numRegions, int width, int height, Context c) {
		SharedPreferences prefs = c.getSharedPreferences("FourColors",
				Context.MODE_PRIVATE);

		colors = new int[4];
		colors[0] = prefs.getInt("color1", Color.RED);
		colors[1] = prefs.getInt("color2", Color.GREEN);
		colors[2] = prefs.getInt("color3", Color.BLUE);
		colors[3] = prefs.getInt("color4", Color.YELLOW);

		this.numRegions = numRegions;
		this.width = width;
		this.height = height;

		BACKGROUND_COLOR = c.getResources().getInteger(
				R.integer.BACKGROUND_COLOR);

		EDGE_COLOR = c.getResources().getInteger(R.integer.EDGE_COLOR);

		anchorPoints = PuzzleMaker.getAnchorPoints(numRegions, width, height);
		// this.board = PuzzleMaker.generateBoard(numRegions, width, height,
		// anchorPoints);
		this.board = new int[height][width];
		ArrayList<Point> corners = new ArrayList<Point>();
		corners.add(new Point(0, 0));
		corners.add(new Point(width - 1, 0));
		corners.add(new Point(width - 1, height - 1));
		corners.add(new Point(0, height - 1));

		long now = System.nanoTime();
		PuzzleMaker.buildBoard(board, anchorPoints, corners);
		Log.v("buildBoard", System.nanoTime() - now + "");

		ArrayList<int[][]> matrices = PuzzleMaker.generateMatrices(board,
				numRegions);
		this.edgeBoard = matrices.get(1);
		this.adjacency = matrices.get(0);

		regions = new ArrayList<Region>();

		populateRegions();
	}

	public int[] getColors() {
		return colors;
	}

	private void populateRegions() {
		for (Point p : anchorPoints) {
			regions.add(new Region(p));
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Point cur = new Point(x, y);

				if (edgeBoard[y][x] != 1)
					regions.get(board[y][x]).addInteriorPoint(cur);
			}
		}
	}

	public int getNumRegions() {
		return numRegions;
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getBoard() {
		return board;
	}

	public int[][] getEdgeBoard() {
		return edgeBoard;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacency;
	}

	public Bitmap toBitmap() {
		int[] colors = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int position = y * width + x;

				switch (edgeBoard[y][x]) {
				case 0:
					colors[position] = BACKGROUND_COLOR;
					break;
				case 1:
					colors[position] = EDGE_COLOR;
					break;
				default:
					colors[position] = edgeBoard[y][x];
					break;
				}
			}
		}
		Bitmap bmp = Bitmap.createBitmap(colors, width, height,
				Bitmap.Config.ARGB_8888);
		return bmp;
	}

	public int fillRegion(Point p, int color) {
		int newColor = color;
		int regionNum = board[p.getY()][p.getX()];

		Region thisRegion = regions.get(regionNum);

		if (newColor == thisRegion.getColor())
			newColor = BACKGROUND_COLOR;

		for (Point point : thisRegion.getInteriorPoints()) {
			edgeBoard[point.getY()][point.getX()] = newColor;
		}

		thisRegion.setColor(newColor);

		return thisRegion.getSize();
	}

	public ArrayList<Region> getAdjacents(Region r) {
		int index = regions.indexOf(r);
		ArrayList<Region> adjacents = new ArrayList<Region>();
		for (int i = 0; i < regions.size(); i++) {
			if (i == index)
				continue;
			if (adjacency[i][index] == 1) {
				adjacents.add(regions.get(i));
			}
		}
		return adjacents;
	}

	// Checks if a coloring is valid, allows "recoloring" over a region with the
	// same color. Used in ColorIt.
	public boolean isValid(Point p, int color) {
		if (color != BACKGROUND_COLOR) {

			if (regions.get(board[p.getY()][p.getX()]).getColor() == color)
				return true;

			if (regions.get(board[p.getY()][p.getX()]).getColor() != BACKGROUND_COLOR)
				return false;

			int[] adjacencies = adjacency[board[p.getY()][p.getX()]];

			for (int i = 0; i < adjacencies.length; i++) {
				if (adjacencies[i] == 1) {
					if (regions.get(i).getColor() == color)
						return false;
				}
			}
		}

		return true;
	}

	// Used to check if the board is in its end state. Used in VSAI
	public boolean isValidEnd(Point p, int color) {
		if (color != BACKGROUND_COLOR) {

			if (regions.get(board[p.getY()][p.getX()]).getColor() != BACKGROUND_COLOR)
				return false;

			int[] adjacencies = adjacency[board[p.getY()][p.getX()]];

			for (int i = 0; i < adjacencies.length; i++) {
				if (adjacencies[i] == 1) {
					if (regions.get(i).getColor() == color)
						return false;
				}
			}
		}

		return true;
	}

	public boolean isComplete() {
		for (Region r : regions) {
			if (r.getColor() == BACKGROUND_COLOR)
				return false;
		}

		return true;
	}

	// True if there are no more valid moves, false otherwise
	public boolean noMoreMoves(int color1, int color2, int color3, int color4) {
		for (Region r : regions) {
			if (r.getColor() == BACKGROUND_COLOR)
				if (isValidEnd(r.getAnchor(), color1)
						|| isValidEnd(r.getAnchor(), color2)
						|| isValidEnd(r.getAnchor(), color3)
						|| isValidEnd(r.getAnchor(), color4))
					return false;
		}

		return true;
	}

	public boolean noMoreMoves(int color) {
		for (Region r : regions) {
			if (r.getColor() == BACKGROUND_COLOR)
				if (isValidEnd(r.getAnchor(), color))
					return false;
		}

		return true;
	}
}
