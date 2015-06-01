package utils;

import java.util.ArrayList;
import java.util.Arrays;

import models.AnchorPoint;
import models.Point;

import android.graphics.Bitmap;
import android.util.Log;

public class PuzzleMaker {
	public static int[][] generateBoard(int numRegions, int width, int height,
			ArrayList<Point> anchorPoints) {

		int[][] board = getBoard(anchorPoints, width, height);

		return board;
	}

	/*
	 * Generates edge and adjacency matrixes.
	 */
	public static ArrayList<int[][]> generateMatrices(int[][] board,
			int numRegions) {
		long now = System.nanoTime();
		ArrayList<int[][]> matrices = new ArrayList<int[][]>();
		int[][] adjacencyMatrix = new int[numRegions][numRegions];
		int[][] edgeBoard = new int[board.length][board[0].length];
		int height = board.length;
		int width = board[0].length;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				ArrayList<Integer> values = adjacencies(board, y, x);
				int value = board[y][x];
				for (int i = 0; i < values.size(); i++) {
					adjacencyMatrix[value][values.get(i)] = 1;
					adjacencyMatrix[values.get(i)][value] = 1;
				}

				if (values.size() == 0)
					edgeBoard[y][x] = 0;
				else
					edgeBoard[y][x] = 1;

			}
		}
		Log.v("generateAdjacencyMatrix", System.nanoTime() - now + "");
		matrices.add(adjacencyMatrix);
		matrices.add(edgeBoard);
		return matrices;
	}

	public static boolean isEdge(int[][] board, int row, int col) {

		if (row - 1 > 0 && col - 1 > 0) {
			if (board[row - 1][col - 1] != board[row][col])
				return true;
		}

		if (row - 1 > 0) {
			if (board[row - 1][col] != board[row][col])
				return true;
		}

		if (row - 1 > 0 && col + 1 < board[0].length) {
			if (board[row - 1][col + 1] != board[row][col])
				return true;
		}

		if (row + 1 < board.length) {
			if (board[row + 1][col] != board[row][col])
				return true;
		}

		if (row + 1 < board.length && col - 1 > 0) {
			if (board[row + 1][col - 1] != board[row][col])
				return true;
		}

		if (row + 1 < board.length && col + 1 < board[0].length) {
			if (board[row + 1][col + 1] != board[row][col])
				return true;
		}

		if (col - 1 > 0) {
			if (board[row][col - 1] != board[row][col])
				return true;
		}

		if (col + 1 < board[0].length) {
			if (board[row][col + 1] != board[row][col])
				return true;
		}

		return false;
	}

	public static ArrayList<Integer> adjacencies(int[][] board, int row, int col) {

		ArrayList<Integer> values = new ArrayList<Integer>();

		if (row - 1 > 0 && col - 1 > 0) {
			if (board[row - 1][col - 1] != board[row][col])
				values.add(board[row - 1][col - 1]);
		}

		if (row - 1 > 0) {
			if (board[row - 1][col] != board[row][col])
				values.add(board[row - 1][col]);
		}

		if (row - 1 > 0 && col + 1 < board[0].length) {
			if (board[row - 1][col + 1] != board[row][col])
				values.add(board[row - 1][col + 1]);
		}

		if (row + 1 < board.length) {
			if (board[row + 1][col] != board[row][col])
				values.add(board[row + 1][col]);
		}

		if (row + 1 < board.length && col - 1 > 0) {
			if (board[row + 1][col - 1] != board[row][col])
				values.add(board[row + 1][col - 1]);
		}

		if (row + 1 < board.length && col + 1 < board[0].length) {
			if (board[row + 1][col + 1] != board[row][col])
				values.add(board[row + 1][col + 1]);
		}

		if (col - 1 > 0) {
			if (board[row][col - 1] != board[row][col])
				values.add(board[row][col - 1]);
		}

		if (col + 1 < board[0].length) {
			if (board[row][col + 1] != board[row][col])
				values.add(board[row][col + 1]);
		}

		return values;
	}

	public static int[][] getBoard(ArrayList<Point> anchors, int width,
			int height) {

		long now = System.nanoTime();
		int[][] board = new int[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Point curPoint = new Point(x, y);

				Point closest = getClosest(anchors, curPoint);

				board[y][x] = ((AnchorPoint) closest).getValue();
			}
		}

		Log.v("getBoard", System.nanoTime() - now + "");

		return board;
	}

	// Corners will always have 4 elements. The points start from the top left
	// corner of the rect
	// and go clockwise.
	public static void buildBoard(int[][] board, ArrayList<Point> anchors,
			ArrayList<Point> corners) {

		// If all the points are the same, we're almost done
		if (corners.get(0).equals(corners.get(1))
				|| corners.get(1).equals(corners.get(2))) {

			// Get its value and record it
			for (int i = 0; i < corners.size(); i++) {
				Point closest = getClosest(anchors, corners.get(i));
				board[corners.get(i).getY()][corners.get(i).getX()] = ((AnchorPoint) closest)
						.getValue();
			}

			return;
		} else {
			// If they're different points, find the values for each point
			for (Point p : corners) {

				// Record those values in the boards
				Point closest = getClosest(anchors, p);
				board[p.getY()][p.getX()] = ((AnchorPoint) closest).getValue();
			}

			// Get the values from the board
			int r0 = board[corners.get(0).getY()][corners.get(0).getX()];
			int r1 = board[corners.get(1).getY()][corners.get(1).getX()];
			int r2 = board[corners.get(2).getY()][corners.get(2).getX()];
			int r3 = board[corners.get(3).getY()][corners.get(3).getX()];

			// If the values are all the same
			if (r0 == r1 && r1 == r2 && r2 == r3) {

				// Shade in the remainder of this rectangle
				for (int y = corners.get(0).getY(); y <= corners.get(2).getY(); y++) {
					for (int x = corners.get(0).getX(); x <= corners.get(2)
							.getX(); x++) {
						board[y][x] = r0;
					}
				}
			} else {
				// If they're not the same, split the rectangles
				splitFour(board, anchors, corners);
			}
		}
	}

	public static void splitFour(int[][] board, ArrayList<Point> anchors,
			ArrayList<Point> corners) {
		int topLeftX = corners.get(0).getX();
		int topLeftY = corners.get(0).getY();
		int topRightX = corners.get(1).getX();
		int topRightY = corners.get(1).getY();
		int bottomRightX = corners.get(2).getX();
		int bottomRightY = corners.get(2).getY();
		int bottomLeftX = corners.get(3).getX();
		int bottomLeftY = corners.get(3).getY();

		ArrayList<Point> set1 = new ArrayList<Point>();
		set1.add(new Point(topLeftX, topLeftY));
		set1.add(new Point((topLeftX + topRightX) / 2, topLeftY));
		set1.add(new Point((topLeftX + bottomRightX) / 2,
				(topLeftY + bottomRightY) / 2));
		set1.add(new Point(topLeftX, (topLeftY + bottomLeftY) / 2));

		ArrayList<Point> set2 = new ArrayList<Point>();
		set2.add(new Point((topLeftX + topRightX) / 2 + 1, topLeftY));
		set2.add(new Point(topRightX, topRightY));
		set2.add(new Point(topRightX, (topRightY + bottomRightY) / 2));
		set2.add(new Point((topLeftX + bottomRightX) / 2 + 1,
				(topLeftY + bottomRightY) / 2));

		ArrayList<Point> set3 = new ArrayList<Point>();
		set3.add(new Point((topLeftX + bottomRightX) / 2 + 1,
				(topLeftY + bottomRightY) / 2 + 1));
		set3.add(new Point(bottomRightX, (topRightY + bottomRightY) / 2 + 1));
		set3.add(new Point(bottomRightX, bottomRightY));
		set3.add(new Point((bottomLeftX + bottomRightX) / 2 + 1, bottomRightY));

		ArrayList<Point> set4 = new ArrayList<Point>();
		set4.add(new Point(bottomLeftX, (bottomLeftY + topLeftY) / 2 + 1));
		set4.add(new Point((bottomLeftX + bottomRightX) / 2,
				(bottomLeftY + topLeftY) / 2 + 1));
		set4.add(new Point((bottomLeftX + bottomRightX) / 2, bottomLeftY));
		set4.add(new Point(bottomLeftX, bottomLeftY));

		buildBoard(board, anchors, set1);
		buildBoard(board, anchors, set2);
		buildBoard(board, anchors, set3);
		buildBoard(board, anchors, set4);
	}

	public static Point getClosest(ArrayList<Point> points, Point point) {
		if (points.size() == 0)
			return null;

		Point closest = points.get(0);
		double closestDist = closest.dist(point);

		for (int i = 1; i < points.size(); i++) {
			double newDist = point.dist(points.get(i));
			if (newDist < closestDist) {
				closest = points.get(i);
				closestDist = newDist;
			}
		}

		return closest;
	}

	public static ArrayList<Point> getAnchorPoints(int numPoints, int width,
			int height) {

		long now = System.nanoTime();
		ArrayList<Point> anchors = new ArrayList<Point>(numPoints);

		double threshold = Math.sqrt(Math.pow(((double) width) / numPoints, 2)
				+ Math.pow(((double) height) / numPoints, 2));

		int count = 0;

		while (count < numPoints) {
			int xPos = (int) (Math.random() * width);
			int yPos = (int) (Math.random() * height);

			Point newAnchorPoint = new AnchorPoint(xPos, yPos, anchors.size());

			Point closest = PuzzleMaker.getClosest(anchors, newAnchorPoint);

			if (closest == null) {
				anchors.add(newAnchorPoint);
				count++;
			} else {
				double closestDist = closest.dist(newAnchorPoint);

				if (closestDist < threshold) {
					continue;
				} else {
					anchors.add(newAnchorPoint);
					count++;
				}
			}

		}

		Log.v("getAnchorPoints", System.nanoTime() - now + "");
		return anchors;
	}
}
